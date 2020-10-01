package com.red.app;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.function.Function;

public class ComputationThread extends Thread{
    private final Function<Integer, Integer> computation;
    private final Pipe.SinkChannel sink;
    private final int x;

    public ComputationThread(Function<Integer, Integer> computation, int x, Pipe.SinkChannel sink) {
        this.computation = computation;
        this.x = x;
        this.sink = sink;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(Const.INT_SIZE);
        Integer computationResult = this.computation.apply(this.x);

        if (computationResult != null) {
            buffer.putInt(computationResult);
        }
        else {
            buffer.put((byte) 0);
        }
        buffer.flip();

        try {
            sink.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
