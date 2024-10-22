package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph g = new AdjacencyMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(2, 1));
        g.addVertex();
        g.addEdge(new Edge(1, 3));
        g.removeVertex(2);

        System.out.println(g.neighbors(3));
    }
}