package org.example;

import java.io.InputStream;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private Boolean[][] matrix;
    private final ArrayList<Integer> list_of_vertex = new ArrayList<>();
    private int size = 0;


    @Override
    public void addVertex() {
        size++;
        Boolean[][] newMatrix = new Boolean[size][size];
        newMatrix[size - 1][size - 1] = false;
        for (int i = 0; i < size - 1; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, size - 1);
            newMatrix[i][size - 1] = false;
            newMatrix[size - 1][i] = false;
        }
        matrix = newMatrix;
        matrix[size - 1][size - 1] = true;
        int u;
        if (size == 1) {
            u = 1;
        } else {
            u = list_of_vertex.getLast() + 1;
        }
        list_of_vertex.add(u);
    }

    @Override
    public void removeVertex(int u) {
        if (!list_of_vertex.contains(u)) {
            return;
        }
        int v = list_of_vertex.indexOf(u);
        Boolean[][] new_matrix = new Boolean[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < v) {
                    if (j < v) {
                        new_matrix[i][j] = matrix[i][j];
                    } else if (j > v) {
                        new_matrix[i][j - 1] = matrix[i][j];
                    }
                } else if (i > v) {
                    if (j < v) {
                        new_matrix[i - 1][j] = matrix[i][j];
                    } else if (j > v) {
                        new_matrix[i - 1][j - 1] = matrix[i][j];
                    }
                }
            }
        }
        size--;
        matrix = new_matrix;
        list_of_vertex.remove((Integer) u);
    }

    @Override
    public void addEdge(Edge e) {
        if (!list_of_vertex.contains(e.getFrom()) || !list_of_vertex.contains(e.getTo())) {
            return;
        }
        matrix[list_of_vertex.indexOf(e.getFrom())][list_of_vertex.indexOf(e.getTo())] = true;
    }

    @Override
    public int removeEdge(Edge e) {
        if (!list_of_vertex.contains(e.getFrom()) || !list_of_vertex.contains(e.getTo()) ||
                e.getFrom() == e.getTo()) {
            return 1;
        }
        if (!matrix[list_of_vertex.indexOf(e.getFrom())][list_of_vertex.indexOf(e.getTo())]) {
            return 1;
        }
        matrix[list_of_vertex.indexOf(e.getFrom())][list_of_vertex.indexOf(e.getTo())] = false;
        return 0;
    }

    @Override
    public ArrayList<Integer> neighbors(int v) {
        v = list_of_vertex.indexOf(v);
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (matrix[v][i]) {
                neighbors.add(list_of_vertex.get(i));
            }
        }
        return neighbors;
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        String line = sc.nextLine();
        String[] frst = line.split(regex);
        size = frst.length;
        matrix = new Boolean[size][size];
        for (int i = 0; i < size; i++) {
            matrix[0][i] = Integer.parseInt(frst[i]) == 1;
        }
        list_of_vertex.add(1);
        int num;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                num = sc.nextInt();
                matrix[i][j] = num == 1;
            }
            list_of_vertex.add(i + 1);
        }
        sc.close();
    }

    @Override
    public List<Integer> topologicalSort(){
        return List.of();
    }

    @Override
    public void print() {
        if (size > 0) {
            System.out.print("    |");
        }
        for (int i : list_of_vertex) {
            System.out.printf("%3d %s", i, i == list_of_vertex.getLast() ? "" : "|");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size + 1; k++) {
                System.out.print("-----");
            }
            System.out.println();
            System.out.printf("%3d |", list_of_vertex.get(i));
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d %s", matrix[i][j] ? 1 : 0, j == size - 1 ? "" : "|");
            }
            System.out.println();
        }
    }

    @Override
    public void isEqual() {

    }
}
