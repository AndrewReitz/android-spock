package com.andrewreitz.spock;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity that takes in intent values.
 */
public class ArgumentsTestActivity extends Activity {
  public static final String TEST_EXTRA = ArgumentsTestActivity.class.getName() + ":test";

  private String value;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_activity);

    value = getIntent().getStringExtra(TEST_EXTRA);
  }

  String getValue() {
    return value;
  }
}
