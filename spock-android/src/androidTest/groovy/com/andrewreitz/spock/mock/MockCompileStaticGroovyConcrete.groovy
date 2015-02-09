package com.andrewreitz.spock.mock

import groovy.transform.CompileStatic;

/** For testing mocking groovy concretes. */
@CompileStatic
class MockCompileStaticGroovyConcrete implements MockGroovyInterface {
  @Override String getString() {
    throw new UnsupportedOperationException("Stub")
  }

  @Override int getInt() {
    throw new UnsupportedOperationException("Stub")
  }

  @Override boolean getBoolean() {
    throw new UnsupportedOperationException("Stub")
  }

  @Override void doStuff() {
    throw new UnsupportedOperationException("Stub")
  }

  @Override void setStuff(Object value) {
    throw new UnsupportedOperationException("Stub")
  }
}
