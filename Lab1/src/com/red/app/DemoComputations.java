package com.red.app;

import spos.lab1.demo.IntOps;

public class DemoComputations implements Computations{
    private final int zeroBasedDemoCaseNo;

    public DemoComputations(int demoCaseNo) {
        this.zeroBasedDemoCaseNo = demoCaseNo - 1;
    }

    @Override
    public Integer computeF(int x) {
        try {
            return IntOps.funcF(this.zeroBasedDemoCaseNo);
        }
        catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public Integer computeG(int x) {
        try {
            return IntOps.funcG(this.zeroBasedDemoCaseNo);
        }
        catch (InterruptedException e) {
            return null;
        }
    }
}
