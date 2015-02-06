package com.andrewreitz.spock;

import spock.lang.Specification;

class UseActivitySpec extends Specification {
  @UseActivity(TestActivity)
  def activity

  def "should create an activity"() {
    expect:
    activity != null
    activity instanceof TestActivity
  }
}
