package com.usu;

public class BinaryTree {
    private TreeNode root;
    public BinaryTree(int initialValue) {
        root = new TreeNode();
        root.value = initialValue;
    }

    public void insert(int value) {
        insert(root, value);
    }

    private void insert(TreeNode node, int value) {
        if (node == null) return;
        if (node.value == value) return;
        if (value > node.value) {
            if (node.right == null) {
                node.right = new TreeNode();
                node.right.value = value;
            } else {
                insert(node.right, value);
            }
        }
        if (value < node.value) {
            if (node.left == null) {
                node.left = new TreeNode();
                node.left.value = value;
            } else {
                insert(node.left, value);
            }
        }
    }

    private class TreeNode {

        int value;
        TreeNode left;
        TreeNode right;
    }
}
