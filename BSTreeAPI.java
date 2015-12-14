package com.company;

import java.util.ArrayList;
import java.util.function.Function;
/**
 * Describes a binary search tree<br>
 * Requires JDK 1.8 for Function
 * @author Duncan
 * @since November 29, 2015
 * @param <E> the data type
 */
public interface BSTreeAPI<E extends Comparable<E>>
{
    /**
     * Determines whether the binary search tree is empty.
     * @return this function returns true if the tree is empty;
     * otherwise, it returns false if the tree contains at least one node.
     */
    boolean isEmpty();
    /**
     * Inserts an item into the tree.
     * @param item the value to be inserted.
     */
    void insert(E item);
    /**
     * Determines whether an item is in the tree.
     * @param item item with a specified search key.
     * @return true on success; false on failure.
     */
    boolean inTree(E item);
    /**
     * Deletes an item from the tree.
     * @param item item with a specified search key.
     */
    void remove(E item);
    /**
     * Gives a reference to the item in the tree with the specified
     * key. If the item does not exists, an exception occurs.
     * @param key the key to the item to be retrieved.
     * @return it with the specified key.
     * @throws BSTreeException
     */
    E retrieve(E key) throws BSTreeException;
    /**
     * Traverses a binary tree in inorder
     * and the function is applied to the data in each node of the tree.
     * @param func a function applied to each node during the traversal
     */
    void inorderTraverse(Function func);
    /**
     * Gives the size of the binary search tree
     * @return the number of nodes in this tree
     */
    int size();

    /**
     * Delete all leaf nodes of this tree and decrement the size of
     * the tree. Does nothing if the tree is empty.
     */
     void trim();
    /**
     * Gives an array list containing all the root-to-leaf paths of
     * the tree. Each path is represented as n1->n2->n3...nk, where
     * each ni is a data value in the node along the path and n1 is
     * the root node, nk is a leaf node.
     * @return an array list of strings containing all the root-to-leaf
     * paths.
     */
    ArrayList<String> getPaths();
    /**
     * Gives the diameter of this tree.
     * @return the diameter of this tree.
     */
    int diameter();



}