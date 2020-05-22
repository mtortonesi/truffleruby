#include <truffleruby-impl.h>

// Accessing Ruby constants, rb_const_*

int rb_const_defined(VALUE module, ID name) {
  return polyglot_as_boolean(RUBY_INVOKE_NO_WRAP(module, "const_defined?", name));
}

int rb_const_defined_at(VALUE module, ID name) {
  return polyglot_as_boolean(RUBY_INVOKE_NO_WRAP(module, "const_defined?", name, Qfalse));
}

VALUE rb_const_get(VALUE module, ID name) {
  return RUBY_CEXT_INVOKE("rb_const_get", module, name);
}

VALUE rb_const_get_at(VALUE module, ID name) {
  return RUBY_INVOKE(module, "const_get", name, Qfalse);
}

VALUE rb_const_get_from(VALUE module, ID name) {
  return RUBY_CEXT_INVOKE("rb_const_get_from", module, name);
}

void rb_const_set(VALUE module, ID name, VALUE value) {
  RUBY_CEXT_INVOKE_NO_WRAP("rb_const_set", module, name, value);
}

void rb_define_const(VALUE module, const char *name, VALUE value) {
  rb_const_set(module, rb_str_new_cstr(name), value);
}

void rb_define_global_const(const char *name, VALUE value) {
  rb_define_const(rb_cObject, name, value);
}