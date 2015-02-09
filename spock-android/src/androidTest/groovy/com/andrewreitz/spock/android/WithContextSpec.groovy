package com.andrewreitz.spock.android

import android.content.Context
import spock.lang.Specification

class WithContextSpec extends Specification {
  //@WithContext
  def context

  def "should get a context"() {
    expect:
    context != null
    context instanceof Context
  }
}
