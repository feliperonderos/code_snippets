package com.company;

import java.util.ArrayList;
import java.util.function.Function;
/**
 * A binary search tree <br>
 * Requires JDK 1.8 for Function*
 * @author Duncan, Felipe Ronderos
 * @param <E> the tree data type
 * @since November 29, 2015
 */
public class BSTree<E extends Comparable<E>> implements BSTreeAPI<E>
{
    /**
     * the root of this tree
     */
    private Node root;
    /**
     * the number of nodes in this tree
     */
    private int size;
    /**
     * A node of a tree stores a data item and references
     * to the child nodes to the left and to the right.
     */
    private class Node
    {
        /**
         * the data in this node
         */
        public E data;
        /**
         * A reference to the left subtree rooted at this node.
         */
        public Node left;
        /**
         * A reference to the right subtree rooted at this node
         */
        public Node right;
    }
    /**
     * Constructs an empty tree
     */
    public BSTree()
    {
        root = null;
        size = 0;
    }
    /**
     * This method creates a binary search tree with the same
     * structure and contents as the specified binary search tree.
     * @param tree a binary search tree
     */
    public BSTree(BSTree tree) {
        this();
        if (!tree.isEmpty()){
            copyTree(tree.root);
        }
    }
    /**
     * An auxiliary method of the copy constructor that inserts the
     * data from the specified node into this tree and recursively
     * inserts the data of the left and right subtrees of this node
     * as the tree is traversed pre-order.
     * @param originalSubtreeRoot a root of a subtree in the
     * original tree.
     */
    private void copyTree(Node originalSubtreeRoot){
        this.insert(originalSubtreeRoot.data);
        if (originalSubtreeRoot.left != null) copyTree(originalSubtreeRoot.left);
        if (originalSubtreeRoot.right != null) copyTree(originalSubtreeRoot.right);
    }
    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }
    @Override
    public void insert(E item)
    {
        Node newNode = new Node();
        newNode.data = item;
        if (size == 0)
        {
            root = newNode;
            size++;
        }
        else
        {
            Node tmp = root;
            while (true)
            {
                int d = tmp.data.compareTo(item);
                if (d==0)
                { /* Key already exists. (update) */
                    tmp.data = item;
                    return;
                }
                else if (d>0)
                {
                    if (tmp.left == null)
                    { /* If the key is less than tmp */
                        tmp.left = newNode;
                        size++;
                        return;
                    }
                    else
                    { /* continue searching for insertion pt. */
                        tmp = tmp.left;
                    }
                }
                else
                {
                    if (tmp.right == null)
                    {/* If the key is greater than tmp */
                        tmp.right = newNode;
                        size++;
                        return;
                    }
                    else
                    { /* continue searching for insertion point*/
                        tmp = tmp.right;
                    }
                }
            }
        }
    }
    @Override
    public boolean inTree(E item)
    {
        Node tmp;
        if (size == 0)
            return false;
/*find where it is */
        tmp = root;
        while (true)
        {
            int d = tmp.data.compareTo(item);
            if (d == 0)
                return true;
            else if (d > 0)
            {
                if (tmp.left == null)
                    return false;
                else
/* continue searching */
                    tmp = tmp.left;
            }
            else
            {
                if (tmp.right == null)
                    return false;
                else
/* continue searching for insertion pt. */
                    tmp = tmp.right;
            }
        }
    }
    @Override
    public void remove(E item)
    {
        Node nodeptr;
        nodeptr = search(item);
        if (nodeptr != null)
        {
            remove(nodeptr);
            size--;
        }
    }
    @Override
    public void inorderTraverse(Function func)
    {
        inorderTraverse(root,func);
    }
    @Override
    public E retrieve(E key) throws BSTreeException
    {
        Node nodeptr;
        if (size == 0)
            throw new BSTreeException("Non-empty tree expected on retrieve().");
        nodeptr = search(key);
        if (nodeptr == null)
            throw new BSTreeException("Existent key expected on retrieve().");
        return nodeptr.data;
    }
    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void trim() {
        if (this.root != null) trim(this.root);
    }
    private void trim(Node subtreeRoot){
        if (subtreeRoot != null){
            if ((subtreeRoot.left == null)&&(subtreeRoot.right==null))remove(subtreeRoot.data);
            if (subtreeRoot.left != null) trim(subtreeRoot.left);
            if (subtreeRoot.right != null) trim(subtreeRoot.right);
        }
    }
    @Override
    public ArrayList<String> getPaths() {
        ArrayList paths = new ArrayList<String>();
        genPaths(this.root,"",paths);
        return paths;
    }

    @Override
    public int diameter() {
        if (this.root != null) return diameter(this.root);
        else return 0;
    }
    /**
     * A recursive auxiliary method of the diameter method that
     * gives the diameter of the subtree rooted at the specified node.
     * @param node in the tree
     * @return the diameter of the subtree rooted at the specified node
     */
    private int diameter(Node node){
        if (node == null) return 0;
        int diam = 1 + maxPath(node.left) + maxPath(node.right);
        int childDiameter = Math.max(diameter(node.right),diameter(node.left));
        return Math.max(diam,childDiameter);
    }
    /**
     * Computes the maximum path of the subtree rooted at the specified node.
     * @param node the root of a subtree
     * @return the number of nodes along the longest path of the subtree
     * rooted at the specified node.
     */
    private int maxPath(Node node){
        if (node == null)return 0;
        else return 1 + Math.max(maxPath(node.left),maxPath(node.right));
    }
    /**
     * A recursive auxiliary method for the getPath method.
     * @param node a node along the root-to-leaf path
     * @param pStr a string representing a path
     * @param paths an array list whose elements are the
     * root-to-leaf paths in the tree in the format
    ^ n1->n2->n3...nk, where n1 is the root and nk a leaf.
     */
    private void genPaths(Node node, String pStr, ArrayList<String> paths){
        if (node.left != null) genPaths(node.left,pStr+node.data.toString()+"->",paths);
        if (node.right != null) genPaths(node.right,pStr+node.data.toString()+"->",paths);
        if ((node.left == null)&&(node.right==null))paths.add(pStr+node.data.toString());
    }
    /**
     * A recursive auxiliary method for the trim method.
     * @param subtreeRoot a node at which a subtree is rooted
     */





    /**
     * A recursive auxiliary method for the inorderTraver method that
     * @param node a reference to a Node object
     * @param func a function that is applied to the data in each
     * node as the tree is traversed in order.
     */
    private void inorderTraverse(Node node, Function func)
    {
        if (node != null)
        {
            inorderTraverse(node.left,func);
            func.apply(node.data);
            inorderTraverse(node.right,func);
        }
    }
    /**
     * An auxiliary method that support the remove method
     * @param node a reference to a Node object in this tree
     */
    private void remove(Node node)
    {
        E theData;
        Node parent, replacement;
        parent = findParent(node);
        if ((node.left != null) && (node.right != null))
        {
            replacement = node.right;
            while (replacement.left != null)
                replacement = replacement.left;
            theData = replacement.data;
            remove(replacement);
            node.data = theData;
        }
        else
        {
            if ((node.left == null) && (node.right == null))
                replacement = null;
            else if (node.left == null)
                replacement = node.right;
            else
                replacement = node.left;
            if (parent==null)
                root = replacement;
            else if (parent.left == node)
                parent.left = replacement;
            else
                parent.right = replacement;
        }
    }
    /**
     * An auxiliary method that supports the search method
     * @param key a data key
     * @return a reference to the Node object whose data has the specified key.
     */
    private Node search(E key)
    {
        Node current = root;
        while (current != null)
        {
            int d = current.data.compareTo(key);
            if (d == 0)
                return current;
            else if (d > 0)
                current = current.left;
            else
                current = current.right;
        }
        return null;
    }
    /**
     * An auxiliary method that gives a Node reference to the parent node of
     * the specified node
     * @param node a reference to a Node object
     * @return a reference to the parent node of the specified node
     */
    private Node findParent(Node node)
    {
        Node tmp = root;
        if (tmp == node)
            return null;
        while(true)
        {
            assert tmp.data.compareTo(node.data) != 0;
            if (tmp.data.compareTo(node.data)>0)
            {
/* this assert is not needed but just
in case there is a bug */
                assert tmp.left != null;
                if (tmp.left == node)
                    return tmp;
                else
                    tmp = tmp.left;
            }
            else
            {
                assert tmp.right != null;
                if (tmp.right == node)
                    return tmp;
                else
                    tmp = tmp.right;
            }
        }
    }
}