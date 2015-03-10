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

import com.andrewreitz.spock.android.WithContext;
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FieldInfo;
import org.spockframework.runtime.model.SpecInfo;

/**
 * Extension for hooking into field annotations. This is used to add an interceptor to the
 * setup fixture allowing for the context to be set.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class WithContextExtension extends AbstractAnnotationDrivenExtension<WithContext> {
  @Override public void visitFieldAnnotation(WithContext annotation, FieldInfo field) {
    WithContextInterceptor interceptor = new WithContextInterceptor(field);
    field.getParent().addSetupInterceptor(interceptor);
  }
}
