package com.andrewreitz.spock

import spock.lang.Specification;

class TestSpec extends Specification {
  def "this should run on android!"() {
    given:
    def a = 2
    def b = 3

    when:
    def result = a + b

    then:
    result == 5
  }
}
