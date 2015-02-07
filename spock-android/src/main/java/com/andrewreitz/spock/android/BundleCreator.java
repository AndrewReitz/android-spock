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
package com.andrewreitz.spock.android;

import android.os.Bundle;

/**
 * Implement this interface to return a bundle that has all the extras an activity needs. Must have
 * an empty public constructor.
 *
 * @author Andrew Reitz
 * @see UseActivity
 * @since 1.1
 */
public interface BundleCreator {
  Bundle createBundle();
}
