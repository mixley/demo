package com.mixley.cloud.common.core.function;

import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface MyFunction<T, R> extends Function<T, R> {

    @SneakyThrows
    default R apply(T t){
     return myApply(t);
    }

    @SneakyThrows
    R myApply(T t) throws Throwable;
}
