package com.andrewreitz.spock.mock;

/** For testing mocking groovy concretes. */
class MockGroovyConcrete implements MockGroovyInterface {
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
