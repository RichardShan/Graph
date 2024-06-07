package tech.fun.graph;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 邻接表
 * <p>
 * 空间复杂度：O(V + E)
 * 建图时间：O(E)
 * 查看两点是否相邻: O(degree(V)), 最坏 O(V)
 * 查找点的所有临边: O(degree(V)), 最坏 O(V)
 *
 * @author Richard Shan
 * @since 2024-06-06
 */
public class AdjList implements Graph {

    private int V;
    private int E;

    private LinkedList<Integer>[] adj;

    public AdjList(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            int v = scanner.nextInt();
            if (v < 0) {
                throw new RuntimeException("Vertex cannot be negative");
            }
            V = v;

            adj = new LinkedList[V];
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

                if (adj[a] != null && adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected");
                }

                LinkedList<Integer> aList = adj[a];
                if (aList == null) {
                    aList = new LinkedList<>();
                    adj[a] = aList;
                }
                LinkedList<Integer> bList = adj[b];
                if (bList == null) {
                    bList = new LinkedList<>();
                    adj[b] = bList;
                }
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasEdge(int v, int w) {
        validVertex(v);
        validVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        validVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validVertex(v);
        return adj[v].size();
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
        sb.append("AdjList:\n").append(String.format("V:%d, E:%d\n", V, E));
        for (int i = 0; i < V; i++) {
            sb.append(String.format("%d: ", i));
            for (Integer v : adj[i]) {
                sb.append(String.format("%d ", v));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String filename = "graphs/adj.txt";
        AdjList matrix = new AdjList(filename);
        System.out.println(matrix);
    }
}
