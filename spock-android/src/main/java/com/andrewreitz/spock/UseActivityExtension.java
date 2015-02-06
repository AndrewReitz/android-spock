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

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FieldInfo;

/**
 * Spock extension for hooking into field annotations. This is used to add an interceptor to the
 * setup fixture allowing it to create the activity specified.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class UseActivityExtension extends AbstractAnnotationDrivenExtension<UseActivity> {
  @Override public void visitFieldAnnotation(UseActivity annotation, FieldInfo field) {

    BundleCreator bundleCreator = instanciateBundleCreator(annotation.bundleCreator());

    UseActivityInterceptor activityInterceptor =
        new UseActivityInterceptor(field, annotation.value(), bundleCreator);
    field.getParent().getSetupMethod().addInterceptor(activityInterceptor);
  }

  private BundleCreator instanciateBundleCreator(
      Class<? extends BundleCreator> bundleCreatorClass) {
    BundleCreator bundleCreator;
    try {
      bundleCreator = bundleCreatorClass.newInstance();
    } catch (InstantiationException e) {
      throw new RuntimeException(
          "Can not instantiate BundleCreator, make sure there is an empty public constructor.", e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(
          "Can not instantiate BundleCreator, make sure there is an empty public constructor.", e);
    }

    return bundleCreator;
  }
}
