package tech.fun.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵
 * <p>
 * 空间复杂度：O(V^2)
 * 建图时间：O(E)
 * 查看两点是否相邻: O(1)
 * 查找点的所有临边: O(V)
 *
 * @author Richard Shan
 * @since 2024-06-06
 */
public class AdjMatrix implements Graph {

    private int V;
    private int E;

    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            int v = scanner.nextInt();
            if (v < 0) {
                throw new RuntimeException("Vertex cannot be negative");
            }
            V = v;

            adj = new int[V][V];
            int e = scanner.nextInt();
            if (e < 0) {
                throw new RuntimeException("Edge cannot be negative");
            }

            for (int i = 0; i < e; i++) {
                int a = scanner.nextInt();
                validVertex(a);
                int b = scanner.nextInt();
                validVertex(b);

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected");
                }

                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasEdge(int v, int w) {
        validVertex(v);
        validVertex(w);
        return adj[v][w] == 1;
    }

    public Iterable<Integer> adj(int v) {
        validVertex(v);
        ArrayList<Integer> adjVertices = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                adjVertices.add(i);
            }
        }
        return adjVertices;
    }

    public int degree(int v) {
        validVertex(v);
        return adj[v].length;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void validVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdjMatrix:\n").append(String.format("V:%d, E:%d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String filename = "graphs/adj.txt";
        AdjMatrix matrix = new AdjMatrix(filename);
        System.out.println(matrix);
    }
}
