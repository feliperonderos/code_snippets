package com.company;
/**
 * Reports an exception in the BSTree implementation
 * @author duncan
 * @since November 13, 2015
 * @see BSTree
 */
public class BSTreeException extends Exception
{
    /**
     * Creates a new instance of <code>BSTreeException</code> without detail
     * message.
     */
    public BSTreeException()
    {
    }
    /**
     * Constructs an instance of <code>BSTreeException</code> with the specified
     * detail message.
     * @param msg the detail message.
     */
    public BSTreeException(String msg)
    {
        super(msg);
    }
}