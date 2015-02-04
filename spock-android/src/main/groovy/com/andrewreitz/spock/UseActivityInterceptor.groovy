package com.andrewreitz.spock

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.InstrumentationRegistry
import org.spockframework.runtime.extension.AbstractMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo

class UseActivityInterceptor extends AbstractMethodInterceptor {
  private final FieldInfo fieldInfo
  private final Class<? extends Activity> activityClass

  private final Instrumentation instrumentation = InstrumentationRegistry.instrumentation

  private Activity activity

  UseActivityInterceptor(FieldInfo fieldInfo, Class<? extends Activity> activityClass) {
    this.fieldInfo = fieldInfo
    this.activityClass = activityClass
  }

  @Override void interceptSetupMethod(IMethodInvocation invocation) {
    fieldInfo.writeValue(invocation.instance, getActivity())
    invocation.proceed()
  }

  protected <T extends Activity> Intent getLaunchIntent(String targetPackage,
      Class<T> activityClass) {
    Intent intent = new Intent(Intent.ACTION_MAIN)
    intent.setClassName(targetPackage, activityClass.name)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    return intent
  }

  private final Activity getActivity() {
    launchActivity()
    return activity
  }

  @SuppressWarnings("unchecked") // Guarded by generics at the constructor.
  private void launchActivity() {
    if (activity != null) return

    String targetPackage = InstrumentationRegistry.instrumentation.targetContext.packageName
    Intent intent = getLaunchIntent(targetPackage, activityClass)

    activity = instrumentation.startActivitySync(intent)
    instrumentation.waitForIdleSync()
  }
}
