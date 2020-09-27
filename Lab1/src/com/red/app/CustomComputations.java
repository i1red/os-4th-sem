package com.red.app;

import java.util.function.Function;

public class CustomComputations implements Computations{
    private final Function<Integer, Integer> f;
    private final Function<Integer, Integer> g;

    public CustomComputations(Function<Integer, Integer> f, Function<Integer, Integer> g) {
        this.f = f;
        this.g = g;
    }

    @Override
    public Integer computeF(int x) {
        return this.f.apply(x);
    }

    @Override
    public Integer computeG(int x) {
        return this.g.apply(x);
    }
}
