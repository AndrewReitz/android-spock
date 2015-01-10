package com.andrewreitz.spock.example

import spock.lang.Specification

class AndroidTestSpec extends Specification {
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
