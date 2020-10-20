package com.usu;

import java.util.ArrayList;

public class Tree {
    private TreeNode root;
    public void generate(int depth) {
        root = new TreeNode();
        root.depth = depth + 1;
        generate(root, depth);
    }

    public TreeNode
    private void generate(TreeNode node, int depth) {
        if (depth == 0) return;
        node.right = new TreeNode();
        node.left = new TreeNode();
//        node.left.depth = depth;
        generate(node.left, depth - 1);
        generate(node.right, depth - 1);
    }

    private class TreeNode {
        int depth;
        ArrayList<TreeNode> nodes;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }
}
