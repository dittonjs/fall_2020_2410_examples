package com.usu;

import java.util.Queue;

public class Main {

    public static void main(String[] args) {
	// write your code here\
        QueueNode root;
        root = new QueueNode();
        root.value = "Joseph";
        root.next = new QueueNode();
        root.next.value = "Kyle";

        CustomQueue queue = new CustomQueue();
        queue.root = root;

    }
}
