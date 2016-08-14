package com.example.spock

import android.widget.Button;
import com.andrewreitz.spock.android.AndroidSpecification

class MockTestSpec extends AndroidSpecification {
  def "I'm mocking on Android!"() {
    given:
    def mocked = Mock(Button)

    when:
    mocked.setOnClickListener(null)

    then:
    1 * mocked.setOnClickListener(null)
  }
}
