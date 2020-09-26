package com.red.app;

import spos.lab1.demo.IntOps;

public class DemoComputations implements Computations{
    private final int zeroBasedDemoCaseNo;

    public DemoComputations(int demoCaseNo) {
        this.zeroBasedDemoCaseNo = demoCaseNo - 1;
    }

    @Override
    public int computeF(int x) throws InterruptedException {
        return IntOps.funcF(this.zeroBasedDemoCaseNo);
    }

    @Override
    public int computeG(int x) throws InterruptedException {
        return IntOps.funcG(this.zeroBasedDemoCaseNo);
    }
}
