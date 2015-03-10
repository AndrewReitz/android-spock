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

import com.andrewreitz.spock.android.UseApplication;
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FieldInfo;

/**
 * Extension for hooking into the setup fixture. This adds a interceptor for creating a Application
 * in tests.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class UseApplicationExtension extends AbstractAnnotationDrivenExtension<UseApplication> {
  @Override public void visitFieldAnnotation(UseApplication annotation, FieldInfo field) {
    UseApplicationInterceptor interceptor =
        new UseApplicationInterceptor(field, annotation.value());
    field.getParent().addSetupInterceptor(interceptor);
  }
}
