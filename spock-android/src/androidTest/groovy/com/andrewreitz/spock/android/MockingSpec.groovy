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
    mocked.setStuff(null)
    mocked.setStuff("Test")

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.setStuff(null)
    1 * mocked.setStuff("Test")
  }

  def "should make a java mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockJavaInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * setStuff(null)
      1 * setStuff("Test")
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()

    mocked.setStuff(null)
    mocked.setStuff("Test")

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
    mocked.setStuff(null)
    mocked.setStuff("test")

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
    1 * mocked.setStuff(null)
    1 * mocked.setStuff("test")
  }

  def "should make a groovy mock from an interface with initializing"() {
    given:
    def mocked = Mock(MockGroovyInterface) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
      2 * setStuff(_)
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()

    mocked.doStuff()
    mocked.setStuff(null)
    mocked.setStuff("Test")

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
    mocked.setStuff("Test")
    mocked.setStuff(null)

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
    1 * mocked.setStuff("Test")
    1 * mocked.setStuff(null)
  }

  def "should make a java mock from a concrete class with initializing"() {
    given:
    def mocked = Mock(MockJavaConcrete) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
      1 * setStuff(null)
      1 * setStuff("Test")
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()

    mocked.doStuff()
    mocked.setStuff(null)
    mocked.setStuff("Test")

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
    mocked.setStuff(null)
    mocked.setStuff("Test")

    then:
    1 * mocked.getString()
    1 * mocked.getInt()
    2 * mocked.getBoolean()
    1 * mocked.doStuff()
    1 * mocked.setStuff("Test")
    1 * mocked.setStuff(null)
  }

  def "should make a groovy mock from a concrete class with initializing"() {
    given:
    def mocked = Mock(MockGroovyConcrete) {
      1 * getString() >> "Testing"
      1 * getBoolean() >> true
      1 * getInt() >> 100
      1 * doStuff()
      1 * setStuff(null)
      1 * setStuff("Test")
    }

    when:
    def stringVal = mocked.getString()
    def boolVal = mocked.getBoolean()
    def intVal = mocked.getInt()

    mocked.doStuff()
    mocked.setStuff(null)
    mocked.setStuff("Test")

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
    mocked.setStuff(null)
    mocked.setStuff("Test")

    then:
    1 * mocked.getString()
    1 * mocked.getBoolean()
    1 * mocked.getInt()
    1 * mocked.doStuff()
    1 * mocked.setStuff(null)
    1 * mocked.setStuff("Test")
  }

  def "wild cards should work"() {
    given:
    def mockJava = Mock(MockJavaConcrete)
    def mockGroovy = Mock(MockGroovyConcrete)

    when:
    mockJava.setStuff(null)
    mockJava.setStuff("Test")
    mockGroovy.setStuff(null)
    mockGroovy.setStuff("Test")

    then:
    2 * mockJava.setStuff(_)
    2 * mockGroovy.setStuff(_)
  }
}
