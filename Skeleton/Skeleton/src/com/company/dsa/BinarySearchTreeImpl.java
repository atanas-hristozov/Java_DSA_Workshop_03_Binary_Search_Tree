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

    public BinarySearchTreeImpl(E value, BinarySearchTreeImpl<E> left, BinarySearchTreeImpl<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
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
        if(value.compareTo(this.value) < 0 && left!=null){
            left.insert(value);
        }
        if(value.compareTo(this.value) < 0 && left==null){
            left = new BinarySearchTreeImpl<>(value);
        }

        if(value.compareTo(this.value) > 0 && right!=null){
            right.insert(value);
        }
        if(value.compareTo(this.value) > 0 && right==null){
            right = new BinarySearchTreeImpl<>(value);
        }
    }

    @Override
    public boolean search(E value) {
        if(value.compareTo(this.value) == 0){
            return true;
        }
        if(value.compareTo(this.value) < 0 && left!=null){
            return left.search(value);
        }
        if(value.compareTo(this.value) > 0 && right!=null){
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

        if(left!=null) {
            result.addAll(left.postOrder());
        }
        if(right!=null) {
            result.addAll(right.postOrder());
        }
        result.add(value);

        return result;
    }

    @Override
    public List<E> preOrder() {
        List<E> result = new ArrayList<>();

        result.add(value);
        if(left!=null) {
            result.addAll(left.preOrder());
        }
        if(right!=null) {
            result.addAll(right.preOrder());
        }

        return result;
    }

    @Override
    public List<E> bfs() {
        List<E> result = new ArrayList<>();
        Queue<BinarySearchTreeImpl<E>> queue = new LinkedList<>();
        queue.offer(this);
        while(!queue.isEmpty()){
            BinarySearchTreeImpl<E> current = queue.poll();
            result.add(current.value);
            if(current.left!=null)
                queue.add(current.left);
            if(current.right!=null)
                queue.add(current.right);
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

//     Advanced task: implement remove method. To test, uncomment the commented tests in BinaryTreeImplTests
//    @Override
//    public boolean remove(E value) {
//        if(value.compareTo(this.value) == 0){
//            return true;
//        }
//        if(value.compareTo(this.value) < 0 && left!=null){
//            return left.search(value);
//        }
//        if(value.compareTo(this.value) > 0 && right!=null){
//            return right.search(value);
//        }
//        return false;
//    }
}
