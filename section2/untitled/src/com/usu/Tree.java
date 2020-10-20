package com.usu;

import java.util.ArrayList;

public class Tree {
    public TreeNode root;

    public void generate(int maxDepth) {
        root = new TreeNode();
        root.depth = 0;
        generate(root, 1, maxDepth);
    }

    private void generate(TreeNode node, int currentDepth, int maxDepth) {
        if (currentDepth == maxDepth) return;
        node.right = new TreeNode();
        node.left = new TreeNode();
        node.left.rotation = -45;
        node.right.rotation = 45;
        node.right.depth = currentDepth;
        node.left.depth = currentDepth;

        generate(node.left, currentDepth + 1, maxDepth);
        generate(node.right, currentDepth + 1, maxDepth);
    }

    private class TreeNode {
        int depth;
        int rotation;
        TreeNode left;
        TreeNode right;
//        ArrayList<TreeNode> branches;
    }
}
