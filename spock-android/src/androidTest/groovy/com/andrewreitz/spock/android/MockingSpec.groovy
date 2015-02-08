package com.andrewreitz.spock.android

import com.andrewreitz.spock.mock.MockCompileStaticGroovyConcrete
import com.andrewreitz.spock.mock.MockGroovyConcrete
import com.andrewreitz.spock.mock.MockGroovyInterface
import com.andrewreitz.spock.mock.MockJavaConcrete
import com.andrewreitz.spock.mock.MockJavaInterface

class MockingSpec extends AndroidSpecification {
  def "should make a java mock from an interface"() {
    given:
    def mocked = Mock(MockJavaInterface)

    when:
    mocked.getString()
    mocked.getInt()
    mocked.getBoolean()
    mocked.getBoolean()

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
  }

  def "should make a java mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockJavaInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  def "should make a groovy mock from an interface"() {
    given:
    def mocked = Mock(MockGroovyInterface)

    when:
    mocked.getString()
    mocked.getInt()
    mocked.getBoolean()
    mocked.getBoolean()
    mocked.doStuff()

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
  }

  def "should make a groovy mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockGroovyInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()
    mocked.doStuff()

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  def "should make a java mock from a concrete class"() {
    given:
    def mocked = Mock(MockJavaConcrete)

    when:
    mocked.getBoolean()
    mocked.getBoolean()
    mocked.getString()
    mocked.getInt()
    mocked.doStuff()

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
  }

  def "should make a java mock from a concrete class with initializing"() {
    given:
    def mocked = Mock(MockJavaConcrete) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()
    mocked.doStuff()

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  def "should make a groovy mock from a concrete class"() {
    given:
    def mocked = Mock(MockGroovyConcrete)

    when:
    mocked.getBoolean()
    mocked.getBoolean()
    mocked.getString()
    mocked.getInt()
    mocked.doStuff()

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
  }

  def "should make a groovy mock from a concrete class with initializing"() {
    given:
    def mocked = Mock(MockGroovyConcrete) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()
    mocked.doStuff()

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  def "should mock a compiled static mock"() {
    given:
    def mocked = Mock(MockCompileStaticGroovyConcrete)

    when:
    mocked.getString()
    mocked.getBoolean()
    mocked.getInt()
    mocked.doStuff()

    then:
    1 * mocked.getString()
    1 * mocked.getBoolean()
    1 * mocked.getInt()
    1 * mocked.doStuff()
  }
}
