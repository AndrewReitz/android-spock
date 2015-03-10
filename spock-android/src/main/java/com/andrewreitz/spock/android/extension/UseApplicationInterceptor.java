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

import android.app.Application;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;

/**
 * Creates a new application and attaches it to the annotated field.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class UseApplicationInterceptor extends AbstractMethodInterceptor {
  private final FieldInfo fieldInfo;
  private final Class<? extends Application> applicationClass;

  private final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

  public UseApplicationInterceptor(FieldInfo fieldInfo,
      Class<? extends Application> applicationClass) {
    this.fieldInfo = fieldInfo;
    this.applicationClass = applicationClass;
  }

  @Override public void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {
    final Application application =
        Instrumentation.newApplication(applicationClass, instrumentation.getTargetContext());
    fieldInfo.writeValue(invocation.getInstance(), application);
    invocation.proceed();
  }
}
