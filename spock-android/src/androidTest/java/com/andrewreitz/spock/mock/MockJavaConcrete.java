package com.andrewreitz.spock.mock;

/** For testing mocking concrete classes. */
public class MockJavaConcrete implements MockJavaInterface {
  @Override public String getString() {
    throw new UnsupportedOperationException("Stub");
  }

  @Override public int getInt() {
    throw new UnsupportedOperationException("Stub");
  }

  @Override public boolean getBoolean() {
    throw new UnsupportedOperationException("Stub");
  }

  @Override public void doStuff() {
    throw new UnsupportedOperationException("Stub");
  }

  @Override public void setStuff(Object value) {
    throw new UnsupportedOperationException("Stub");
  }
}
