# Operating systems lab works

## Lab1 (#2): tasks synchronization and parallelization
Compute binary operation with zero value on f(x) and g(x) (f and g are computed in parallel)

Use Java threads, java.nio.channels.Pipe (non-blocking mode) for function result communication

My binary operation is multiplication of Java Integers:\
 -if f or g is 0 then return 0\
 -if f or g is null then return null (undefined result, for example sqrt(-1) should return null)\
 -else return f * g
    

