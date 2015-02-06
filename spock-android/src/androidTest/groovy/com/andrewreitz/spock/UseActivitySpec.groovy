package com.andrewreitz.spock

import android.os.Bundle;
import spock.lang.Specification;

class UseActivitySpec extends Specification {
  @UseActivity(SimpleTestActivity)
  def simpleActivity

  @UseActivity(//
      value = ArgumentsTestActivity, //
      bundleCreator = ArgumentsTestActivityBundleCreator //
  )
  ArgumentsTestActivity argumentsActivity

  def "should create an activity"() {
    expect:
    simpleActivity != null
    simpleActivity instanceof SimpleTestActivity
  }

  def "should have a set value"() {
    expect:
    argumentsActivity != null
    argumentsActivity instanceof ArgumentsTestActivity
    argumentsActivity.value == "TESTING"
  }

  final static class ArgumentsTestActivityBundleCreator implements BundleCreator {
    @Override Bundle createBundle() {
      def bundle = new Bundle()
      bundle.putString(ArgumentsTestActivity.TEST_EXTRA, "TESTING")
      return bundle
    }
  }
}
