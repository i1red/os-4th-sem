package com.red.app;

import spos.lab1.demo.IntOps;

public class DemoComputations implements Computations{
    @Override
    public Integer computeF(int x) {
        try {
            return IntOps.funcF(x - 1);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer computeG(int x) {
        try {
            return IntOps.funcG(x - 1);
        }
        catch (Exception e) {
            return null;
        }
    }
}
