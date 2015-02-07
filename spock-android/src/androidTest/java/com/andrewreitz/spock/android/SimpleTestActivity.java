package com.andrewreitz.spock.android;

import android.app.Activity;
import android.os.Bundle;
import com.andrewreitz.spock.R;

public class SimpleTestActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_activity);
  }
}
