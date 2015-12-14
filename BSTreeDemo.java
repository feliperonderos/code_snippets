package com.company;
/**
 * A program to test our BSTree implementation<br>
 * Requires JDK 1.8 for Function
 * @author Felipe Ronderos
 * @since November 29, 2015
 * @see BSTree
 */
public class BSTreeDemo {

    public static void main(String[] args) {
	    BSTree<Integer> tree1 = new BSTree<>();
        BSTree<Integer> tree2 = new BSTree<>();
        int[] p = { 6, 2, 1, 4, 3, 5, 7, 8, 12, 13, 10, 9, 11};
        for(int i = 0; i<p.length; i++){
            tree1.insert(p[i]);
        }
        int[] p1 = { 13, 14, 15, 7, 2, 1, 6, 4, 3, 5, 8, 10, 9, 11, 12};
        for(int i = 0; i<p1.length; i++){
            tree2.insert(p1[i]);
        }
        System.out.println("Tree 1 paths are: \n"+tree1.getPaths());
        System.out.println("Tree 2 paths are: \n"+tree2.getPaths());
        System.out.println("Tree 1 diameter is: \n"+tree1.diameter());
        System.out.println("Tree 2 diameter is: \n"+tree2.diameter());
        BSTree<Integer> tree3 = new BSTree<>(tree2);
        System.out.println("Tree 3 paths are: \n"+tree3.getPaths());
        System.out.println("Tree 3 diameter is: \n"+tree3.diameter());
        System.out.println("Tree 3 size is: \n"+tree3.size());
        tree3.trim();
        System.out.println("Tree 3 paths are: \n"+tree3.getPaths());
        System.out.println("Tree 3 diameter is now: \n"+tree3.diameter());
        System.out.println("Tree 3 size is now: \n"+tree3.size());
    }
}
