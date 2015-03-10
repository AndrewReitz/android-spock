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
package com.andrewreitz.spock.android

import com.andrewreitz.spock.android.mock.runtime.AndroidMockFactory
import org.spockframework.mock.MockImplementation
import org.spockframework.mock.MockNature
import org.spockframework.mock.runtime.MockConfiguration
import org.spockframework.runtime.GroovyRuntimeUtil
import spock.lang.Specification

import java.lang.reflect.Type

/**
 * Extend this class in order to make mocks of concrete classes on Android.
 *
 * @since 1.1
 * @author Andrew Reitz
 */
abstract class AndroidSpecification extends Specification {
  // Note to self. This MUST be a groovy class. If java it will not run some some reason.
  @Override Object createMock(String name, Type type, MockNature nature, MockImplementation implementation,
      Map<String, Object> options, Closure closure) {
    Object mock = AndroidMockFactory.INSTANCE.create(new MockConfiguration(name, type, nature,
        implementation, options), this)

    if (closure != null) {
      GroovyRuntimeUtil.invokeClosure(closure, mock)
    }
    return mock
  }
}
