package com.andrewreitz.spock.android;

import spock.lang.Specification;

class WithApplicationSpec extends Specification {
  @UseApplication(TestApplication)
  def application

  def "should create a application"() {
    expect:
    application != null
    application instanceof TestApplication
  }
}
