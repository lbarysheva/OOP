package org.example;

/**
 * Abstract base class for document elements.
 */
public abstract class Element {


    public abstract String toString();

    /**
     * Checks equality with another object.
     */
    public abstract boolean equals(Object obj);
}