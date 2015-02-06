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
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityUnitTestCase;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;

/**
 * Intercepts setup fixtures to instantiate an activity.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public class UseActivityInterceptor extends AbstractMethodInterceptor {
  private final FieldInfo fieldInfo;
  private final Class<? extends Activity> activityClass;
  private final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

  private Activity activity;
  private BundleCreator bundleCreator;

  /**
   * Constructor
   *
   * @param fieldInfo The field that should be instantiated.
   * @param activityClass The activity class type that should be created and set the annotated
   * field
   * to.
   * @param bundleCreator The bundle creator that will be used to pass a bundle to the started
   * activity.
   */
  public UseActivityInterceptor(FieldInfo fieldInfo, Class<? extends Activity> activityClass,
      BundleCreator bundleCreator) {
    this.fieldInfo = fieldInfo;
    this.activityClass = activityClass;
    this.bundleCreator = bundleCreator;
  }

  @Override public void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {
    fieldInfo.writeValue(invocation.getInstance(), getActivity());
    invocation.proceed();
  }

  /**
   * The intent to launch the Activity.
   *
   * @param targetPackage The package of the Activity.
   * @param activityClass The Activity class send the intent to.
   * @param bundleCreator Bundle creator instance that will give a bundle to the activity.
   * @param <T> The specific Activity type.
   * @return The intent to start the activity.
   */
  protected <T extends Activity> Intent getLaunchIntent(String targetPackage,
      Class<T> activityClass, BundleCreator bundleCreator) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setClassName(targetPackage, activityClass.getName());
    intent.putExtras(bundleCreator.createBundle());
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    return intent;
  }

  /**
   * @return Launches the activity if it hasn't been started, then returns it.
   */
  private Activity getActivity() {
    launchActivity();
    return activity;
  }

  /** Launch the activity if it has not be started. */
  @SuppressWarnings("unchecked")
  private void launchActivity() {
    if (activity != null) return;

    String targetPackage = instrumentation.getTargetContext().getPackageName();
    Intent intent = getLaunchIntent(targetPackage, activityClass, bundleCreator);

    activity = instrumentation.startActivitySync(intent);
    instrumentation.waitForIdleSync();
  }
}
