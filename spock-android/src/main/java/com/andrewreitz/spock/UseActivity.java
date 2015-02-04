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
package com.andrewreitz.spock;

import android.app.Activity;
import org.spockframework.runtime.extension.ExtensionAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Apply to Activities in Specifications in order to test activities.
 *
 * {@code @UseActivity(MainActivity) def activity}
 *
 * @author Andrew Reitz
 * @since 1.1
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
@ExtensionAnnotation(UseActivityExtension.class)
public @interface UseActivity {
  /**
   * @return Provide the specific {@link Activity} type to be created for the test.
   */
  Class<? extends Activity> value();
}
