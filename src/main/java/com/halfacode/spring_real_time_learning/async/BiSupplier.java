package com.halfacode.spring_real_time_learning.async;


@FunctionalInterface
public interface BiSupplier<T, U> {
    Pair<T, U> get();
}
