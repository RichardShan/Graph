package tech.fun.traverse.dfs.applications;

import tech.fun.graph.AdjSet;
import tech.fun.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Richard Shan
 * @since 2024-06-07
 */
public class Path {
    private final boolean[] visited;
    private final int pre[];
    private final Graph graph;

    private int cccount = 0;

    int s;
    int t;

    public Path(Graph graph, int s, int t) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.pre = new int[graph.getV()];
        this.s = s;
        this.t = t;

        Arrays.fill(visited, false);
        Arrays.fill(pre, -1);

        dfsRecursive(s, s);
    }

    private boolean dfsRecursive(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;

        if (v == t) {
            return true;
        }

        for (Integer w : graph.adj(v)) {
            if (!visited[w] && dfsRecursive(w, v)) {
                return true;
            }
        }

        return false;
    }

    private boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> path = new ArrayList<>();
        if (!isConnected()) {
            return path;
        }

        int cur = t;
        while (cur != s) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(s);

        Collections.reverse(path);
        return path;
    }


    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/cc.txt");
        Path path1 = new Path(g, 0, 1);
        System.out.println("0 -> 1: " + path1.path());

        Path path2 = new Path(g, 0, 4);
        System.out.println("0 -> 4: " + path2.path());

        Path path3 = new Path(g, 1, 5);
        System.out.println("0 -> 5: " + path3.path());

    }
}
