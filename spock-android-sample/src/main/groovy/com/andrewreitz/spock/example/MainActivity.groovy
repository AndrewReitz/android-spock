package com.andrewreitz.spock.example

import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends Activity {
  @Override void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)

    getWindow().setContentView(R.layout.activity_main)
    setContentView(R.layout.activity_main)
  }
}
