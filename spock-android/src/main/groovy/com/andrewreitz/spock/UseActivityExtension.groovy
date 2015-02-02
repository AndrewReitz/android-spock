package com.andrewreitz.spock

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FieldInfo

class UseActivityExtension extends AbstractAnnotationDrivenExtension<UseActivity> {
  @Override void visitFieldAnnotation(UseActivity annotation, FieldInfo field) {
    UseActivityInterceptor activityInterceptor = new UseActivityInterceptor(field,
        annotation.value())
    field.parent.setupMethod.addInterceptor(activityInterceptor)
  }
}
