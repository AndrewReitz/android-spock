package com.andrewreitz.spock

import org.spockframework.mock.constraint.EqualPropertyNameConstraint;
import spock.lang.Specification;

class MockingSpec extends Specification {
  def "should make a mock from an interface"() {
    given:
    def mocked = Mock(MockInterfafce)

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

  def "should make a mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockInterfafce) {
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
}
