package com.andrewreitz.spock.android

import com.andrewreitz.spock.mock.MockGroovyInterface
import com.andrewreitz.spock.mock.MockJavaInterface
import spock.lang.Specification;

class MockingSpec extends Specification {
  def "should make a java mock from an interface"() {
    given:
    def mocked = Mock(MockJavaInterface)

    when:
    mocked.string
    mocked.int
    mocked.boolean
    mocked.boolean

    then:
    1 * mocked.string
    1 * mocked.int
    2 * mocked.boolean
  }

  def "should make a java mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockJavaInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
    }

    when:
    def stringVal = mocked.string
    def boolVal = mocked.boolean
    def intVal = mocked.int

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  def "should make a groovy mock from an interface"() {
    given:
    def mocked = Mock(MockGroovyInterface)

    when:
    mocked.string
    mocked.int
    mocked.boolean
    mocked.boolean

    then:
    1 * mocked.string
    1 * mocked.int
    2 * mocked.boolean
  }

  def "should make a groovy mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockGroovyInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
    }

    when:
    def stringVal = mocked.string
    def boolVal = mocked.boolean
    def intVal = mocked.int

    then:
    stringVal == "Testing"
    boolVal
    intVal == 100
  }

  //def "should make a mock from a concrete class"() {
  //  given:
  //  def mocked = Mock(MockJavaConcrete)
  //
  //  when:
  //  mocked.boolean
  //  mocked.boolean
  //  mocked.string
  //  mocked.int
  //
  //  then:
  //  1 * mocked.string
  //  1 * mocked.int
  //  2 * mocked.boolean
  //}
  //
  //def "should make a mock from a concrete class with initializing"() {
  //  given:
  //  def mocked = Mock(MockJavaConcrete) {
  //    1 * getString() >> "Testing"
  //    1 * getBoolean() >> true
  //    1 * getInt() >> 100
  //  }
  //
  //  when:
  //  def stringVal = mocked.string
  //  def boolVal = mocked.boolean
  //  def intVal = mocked.int
  //
  //  then:
  //  stringVal == "Testing"
  //  boolVal
  //  intVal == 100
  //}
}
