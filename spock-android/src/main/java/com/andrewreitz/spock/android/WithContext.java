package com.andrewreitz.spock.android;

import com.andrewreitz.spock.android.extension.WithContextExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.spockframework.runtime.extension.ExtensionAnnotation;

/**
 * Use to get the context in your tests.
 * {@code @WithContext def context}
 *
 * @author Andrew Reitz
 * @since 1.1
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
@ExtensionAnnotation(WithContextExtension.class)
public @interface WithContext {
}
