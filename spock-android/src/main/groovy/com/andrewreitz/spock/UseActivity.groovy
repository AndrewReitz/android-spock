package com.andrewreitz.spock

import android.app.Activity
import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ExtensionAnnotation(UseActivityExtension)
public @interface UseActivity {
  Class<? extends Activity> value()
}
