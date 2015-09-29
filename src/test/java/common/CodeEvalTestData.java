package common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CodeEvalTestData {
    Class<?> testClass();

    String[] input() default {};

    String[] expectedOutput();

    String inputFile() default "";

    boolean logOutput() default false;
}
