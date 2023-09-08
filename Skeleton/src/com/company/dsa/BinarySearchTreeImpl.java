package com.company.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E value;
    private BinarySearchTreeImpl<E> left;
    private BinarySearchTreeImpl<E> right;

    public BinarySearchTreeImpl(E value) {
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public E getRootValue() {
        return value;
    }

    @Override
    public BinarySearchTree<E> getLeftTree() {
        return left;
    }

    @Override
    public BinarySearchTree<E> getRightTree() {
        return right;
    }

    @Override
    public void insert(E value) {
        if (value.compareTo(this.value) < 0) {
            if (left == null) {
                left = new BinarySearchTreeImpl<>(value);
            } else {
                left.insert(value);
            }
        } else if (value.compareTo(this.value) > 0) {
            if (right == null) {
                right = new BinarySearchTreeImpl<>(value);
            } else {
                right.insert(value);
            }
        }
    }

    @Override
    public boolean search(E value) {
        if (value.compareTo(this.value) == 0){
            return true;
        }
        if(value.compareTo(this.value) < 0 && left != null){
            return left.search(value);
        }
        if (value.compareTo(this.value) > 0 && right != null){
            return right.search(value);
        }
        return false;
    }

    @Override
    public List<E> inOrder() {
        List<E> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.inOrder());
        }
        result.add(value);
        if (right != null) {
            result.addAll(right.inOrder());
        }
        return result;
    }

    @Override
    public List<E> postOrder() {
        List<E> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.postOrder());
        }
        if (right != null) {
            result.addAll(right.postOrder());
        }
        result.add(value);
        return result;
    }

    @Override
    public List<E> preOrder() {
        List<E> result = new ArrayList<>();
        result.add(value);
        if (left != null) {
            result.addAll(left.preOrder());
        }
        if (right != null) {
            result.addAll(right.preOrder());
        }
        return result;
    }

    @Override
    public List<E> bfs() {
        List<E> result = new ArrayList<>();
        Queue<BinarySearchTreeImpl<E>> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            BinarySearchTreeImpl<E> currentNode = queue.poll();
            result.add(currentNode.value);
            if (currentNode.left != null){
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null){
                queue.offer(currentNode.right);
            }
        }
        return result;
    }

    @Override
    public int height() {
        return measureHeight(this);
    }

    private int measureHeight(BinarySearchTreeImpl<E> node) {
        if (node == null) {
            return -1;
        }

        int left = measureHeight(node.left);
        int right = measureHeight(node.right);

        return Math.max(left, right) +1;
    }

    // Advanced task: implement remove method. To test, uncomment the commented tests in BinaryTreeImplTests
    @Override
    public boolean remove(E value) {
        BinarySearchTreeImpl<E> parent = null;
        BinarySearchTreeImpl<E> current = this;

        while (current != null && !current.value.equals(value)) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left == null && current.right == null) {
            if (parent == null) {
                return false;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        else if (current.left == null || current.right == null) {
            BinarySearchTreeImpl<E> child = (current.left != null) ? current.left : current.right;
            if (parent == null) {
                this.value = child.value;
                this.left = child.left;
                this.right = child.right;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        else {
            BinarySearchTreeImpl<E> successorParent = current;
            BinarySearchTreeImpl<E> successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.value = successor.value;

            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }

        return true;
    }
}
