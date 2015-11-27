package com.andrewreitz.spock.android

import spock.lang.Specification
import spock.lang.Stepwise

import static ActivityRunMode.METHOD

@Stepwise
class UseActivityPerMethodSpec extends Specification {
  @UseActivity(value=SimpleTestActivity, runMode = METHOD )
  def simpleActivity

  def "should run an activity"() {
    expect:
      simpleActivity instanceof SimpleTestActivity
      simpleActivity.finish()
  }

  def "should run second activity"() {
    expect:
      simpleActivity instanceof SimpleTestActivity
      !simpleActivity.isFinishing()
  }
}
