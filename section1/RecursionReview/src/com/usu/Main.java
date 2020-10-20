package com.usu;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(sum(10));
        System.out.println(factorial(10));
//        System.out.println(fib(50));

//        BinaryTree tree = new BinaryTree(10);
//        tree.insert(5);
//        tree.insert(15);
//        tree.insert(7);
        Tree tree = new Tree();
        tree.generate(100);
        System.out.println();
    }

    public static int sum(int value) {
        if (value == 0) return 0;
        return value + sum(value - 1);
    }

    public static int factorial(int value) {
        if (value == 0) return 1;
        return value * factorial(value - 1);
    }

//    f(n) = f(n-1) + f(n-2)

    public static int fib(int value) {
        if (value <= 1) return 1;
        if (value == 2) return 2;
        return fib(value - 1) + fib(value - 2);
    }
}
