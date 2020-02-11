/*
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 2.0, or
 * GNU General Public License version 2, or
 * GNU Lesser General Public License version 2.1.
 */

package org.truffleruby.core.array.library;

import com.oracle.truffle.api.library.GenerateLibrary;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.library.GenerateLibrary.Abstract;
import com.oracle.truffle.api.library.GenerateLibrary.DefaultExport;

import org.truffleruby.core.array.DelegatedArrayStorage;

/** Library for accessing and manipulating the storage used for representing arrays. This includes reading, modifying,
 * and copy the storage. */
@GenerateLibrary
@DefaultExport(IntegerArrayStore.class)
@DefaultExport(LongArrayStore.class)
@DefaultExport(DoubleArrayStore.class)
@DefaultExport(ObjectArrayStore.class)
public abstract class ArrayStoreLibrary extends Library {

    /** An initial immutable empty array store. This is what should be assigned initially to an array of zero size. */
    public static final Object INITIAL_STORE = ZeroLengthArrayStore.ZERO_LENGTH_STORE;

    private static final LibraryFactory<ArrayStoreLibrary> FACTORY = LibraryFactory.resolve(ArrayStoreLibrary.class);

    public static LibraryFactory<ArrayStoreLibrary> getFactory() {
        return FACTORY;
    }

    /** Read the value from {@code index} of {@code store}. */
    public abstract Object read(Object store, int index);

    /** Return whether {@code store} can accept this {@code value}. */
    @Abstract(ifExported = { "write", "acceptsAllValues", "isMutable", "sort" })
    public boolean acceptsValue(Object store, Object value) {
        return false;
    }

    /** Return whether {@code store} can accept all values that could be held in {@code otherStore}. */
    @Abstract(ifExported = { "write", "acceptsValue", "isMutable", "sort" })
    public boolean acceptsAllValues(Object store, Object otherStore) {
        return false;
    }

    /** Return whether {@code store} can be mutated. If not, then an allocator must be used to create a mutable version
     * of the store and contents copied in order to make modifications. */
    @Abstract(ifExported = { "write", "acceptsValue", "acceptsAllValues", "sort" })
    public boolean isMutable(Object store) {
        return false;
    }

    /** Return whether the {@code store} is native, i.e. can it be accessed via native pointers from C? */
    public boolean isNative(Object store) {
        return false;
    }

    /** Return whether the {@code store} only holds primitives such as integers or doubles, or might hold other objects
     * as well. */
    public abstract boolean isPrimitive(Object store);

    /** Return a description of {@code store} for debugging output. */
    public abstract String toString(Object store);

    /** Write {@code value} to {@code index} of {@code store}. */
    @Abstract(ifExported = { "acceptsValue", "acceptsAllValues", "isMutable", "sort" })
    public void write(Object store, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    /** Return the capacity of {@code store}. */
    public abstract int capacity(Object store);

    /** Return a copy of {@store} expanded to the requested capacity. */
    public abstract Object expand(Object store, int capacity);

    /** Return a store representing the range from {@code start} (inclusive) to {@code end} (exclusive) of {@code store}
     * . This is usually an unmodifiable view onto the existing store, so the user may need to replace the reference to
     * the original store with a second unmodifiable view. */
    public Object extractRange(Object store, int start, int end) {
        return new DelegatedArrayStorage(store, start, (end - start));
    }

    /** Copy the contents of {@code store} from {@code srcStart} to the {@code dest} store starting from
     * {@code destStart}. {@code length} entries will be copied. */
    public abstract void copyContents(Object store, int srcStart, Object dest, int destStart, int length);

    /** Create a mutable copy of {@code store} of length {@code length}. */
    public abstract Object copyStore(Object store, int length);

    /** Sort the first {@code size} elements of {@code store} in place. */
    @Abstract(ifExported = { "acceptsValue", "acceptsAllValues", "isMutable", "write" })
    public void sort(Object store, int size) {
        throw new UnsupportedOperationException();
    }

    /** Return an iterator over {@code store} from the element at {@code start} index, and returning up to
     * {@code length} elements. */
    public abstract Iterable<Object> getIterable(Object store, int start, int length);

    /** Return an allocator that can accept all the values of {@code
        * store} and {@code newValue}. */
    public abstract ArrayAllocator generalizeForValue(Object store, Object newValue);

    /** Return an allocator that can accept all the values of {@code store} and all the values that could be held in
     * {@code newStore}. */
    public abstract ArrayAllocator generalizeForStore(Object store, Object newStore);

    /** Return an allocator for a mutable version of {@code store}. */
    public abstract ArrayAllocator allocator(Object store);

    /** Return an allocator for storage that can hold {@code value}. */
    public static ArrayAllocator allocatorForValue(Object value) {
        if (value instanceof Integer) {
            return IntegerArrayStore.INTEGER_ARRAY_ALLOCATOR;
        } else if (value instanceof Long) {
            return LongArrayStore.LONG_ARRAY_ALLOCATOR;
        } else if (value instanceof Double) {
            return DoubleArrayStore.DOUBLE_ARRAY_ALLOCATOR;
        } else {
            return ObjectArrayStore.OBJECT_ARRAY_ALLOCATOR;
        }
    }

    /** Class for allocating array stores and querying properties related to that. */
    public static abstract class ArrayAllocator {

        /** Return a new array store with {@code capacity} elements. */
        public abstract Object allocate(int capacity);

        /** Return whether stores returned by this allocator can hold {@code value}. */
        public abstract boolean accepts(Object value);

        /** Return whether stores returned by this allocator specialize for {@code value}. This differs from
         * {@link #accepts(Object)} because a store holding objects might accepts a {@code
         * long}, but does not specialize for that in the way that a store that holds unboxed {@code long}s does. */
        public abstract boolean specializesFor(Object value);

        /** Return whether {@code value} is the default value for stores returned by this allocator. For primitive
         * stores the default value will normally be 0, or its numerical equivalent, while stores that hold Objects will
         * usually default to {@code null}, but this is not guaranteed. */
        public abstract boolean isDefaultValue(Object value);

    }
}
