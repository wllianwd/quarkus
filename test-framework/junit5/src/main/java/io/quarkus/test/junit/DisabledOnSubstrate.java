package io.quarkus.test.junit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated Use {@link DisabledOnNativeImage} instead.
 */
@Deprecated
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisabledOnSubstrate {
    /**
     * Reason for disabling this test
     */
    public String value() default "";
}
