package com.red.app;

public class Ref<T> {
    private T value;

    public Ref() {
        this.setValue(null);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
