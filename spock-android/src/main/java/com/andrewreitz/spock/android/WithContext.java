package com.andrewreitz.spock.android;

import com.andrewreitz.spock.android.extension.WithContextExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.spockframework.runtime.extension.ExtensionAnnotation;

@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
@ExtensionAnnotation(WithContextExtension.class)
public @interface WithContext {
}
