package com.springboot2.homework1.Annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)// method
public @interface Benchmark {

}
