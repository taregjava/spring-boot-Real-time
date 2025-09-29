package com.halfacode.spring_real_time_learning.runnable;

@FunctionalInterface
public interface BusinessCommand<T, R> {
    R execute(T input);
}

