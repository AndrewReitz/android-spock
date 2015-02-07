/*
 * Copyright (C) 2015 Andrew Reitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.spock

import android.widget.Button
import com.andrewreitz.spock.android.UseActivity
import spock.lang.Specification

class MainActivitySpec extends Specification {

  @UseActivity(MainActivity)
  def activity

  def "test activity setup"() {
    expect:
    activity != null
    activity instanceof MainActivity
  }

  def "test layout"() {
    given:
    def button = activity.findViewById(R.id.main_button) as Button

    when:
    def buttonText = button.getText()

    then:
    buttonText == "Test"
  }
}
