package com.red.app;

public class App {
    private static Computations createComputations(Integer caseNo) {
        System.out.println(caseNo);
        if (caseNo != null && 1 <= caseNo && caseNo <= 6) {
            return new DemoComputations(caseNo);
        }

        return new CustomComputations(x -> x * 2, x -> x / 3);
    }

    private static Integer getCaseNo(String[] args) {
        if (args.length == 2 && args[0].equals("-d")) {
            try {
                return Integer.valueOf(args[1]);
            }
            catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Computations computations = createComputations(getCaseNo(args));

        try {
            System.out.println(computations.computeF(1));
            System.out.println(computations.computeG(2));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
