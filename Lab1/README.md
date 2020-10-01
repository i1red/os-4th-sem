## Lab1 (#2): tasks synchronization and parallelization
Compute binary operation with zero value on f(x) and g(x) (f and g are computed in parallel)

Use Java threads, java.nio.channels.Pipe (non-blocking mode) for function result communication

My binary operation is multiplication of Java Integers:\
 -if f or g is 0 then return 0\
 -if f or g is null then return null (undefined result, for example f(7) should return null, f and g accept integers in range 1..6)\
 -else return f * g

### Instructions

Note #1: program is written with java 14, some feautures may not work with older versions

Note #2: I assume you running these commands from Lab1 directory

To compile project use command:
```
javac -cp libs/lab1.jar -d compiled src/com/red/app/*.java
```

To run project use command:
```
java -cp "./libs/lab1.jar:./compiled" com/red/app/App
```

To run project in demo mode use command:
```
java -cp "./libs/lab1.jar:./compiled" com/red/app/App -d
```

To exit program press:
```
Ctrl+C
```
    

