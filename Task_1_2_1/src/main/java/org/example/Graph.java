package org.example;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс графа.
 */
public interface Graph{
    void addVertex();

    void removeVertex(int v);

    void addEdge(Edge e);

    int removeEdge(Edge e);

    ArrayList<Integer> neighbors(int v);

    void read(InputStream input, String regex);

    List<Integer> topologicalSort();

    void print();

    void isEqual();
}