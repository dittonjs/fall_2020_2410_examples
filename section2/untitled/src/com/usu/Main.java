package com.usu;

public class Main {

    public static void main(String[] args) {
//	 write your code here
//        System.out.println(factorial(4));
//        int n = 10;
//        while(n > 0) {
//            System.out.println(n);
//            n --;
//        }
//        printNumbers(10);
//        System.out.println(fib(100));
//        BinaryTree tree = new BinaryTree(10);
//        tree.insert(15);
//        tree.insert(5);
//        tree.insert(7);

        Tree tree = new Tree();
        tree.generate(10);
        System.out.println();
    }

    public static void printNumbers(int n) {
        if (n == 0) return;
        System.out.println(n);
        printNumbers(n - 1);
    }

    public static int factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }

    public static int sum(int value) {
        if (value == 1) return 1;
        return value + sum(value - 1);
    }

//    f(n) = f(n-1) + f(n-2);
    public static int fib(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return fib(n-1) + fib(n-2);
    }

}
