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
package com.andrewreitz.spock.android.extension;

import android.support.test.InstrumentationRegistry;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;

/**
 * Intercepts setup fixtures to set to the context.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class WithContextInterceptor extends AbstractMethodInterceptor {
  private final FieldInfo fieldInfo;

  public WithContextInterceptor(FieldInfo fieldInfo) {
    this.fieldInfo = fieldInfo;
  }

  @Override public void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {
    fieldInfo.writeValue(invocation.getTarget(), InstrumentationRegistry.getTargetContext());
  }
}
