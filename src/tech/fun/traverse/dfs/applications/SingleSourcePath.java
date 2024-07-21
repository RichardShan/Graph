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
public class SingleSourcePath {
    private final boolean[] visited;
    private final int pre[];
    private final int source;

    private final Graph graph;

    public SingleSourcePath(Graph graph, int source) {
        graph.validVertex(source);

        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.pre = new int[graph.getV()];
        this.source = source;

        Arrays.fill(pre, -1);

        dfsRecursive(source, source);
    }

    private void dfsRecursive(int v, int s) {
        visited[v] = true;
        pre[v] = s;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                dfsRecursive(w, v);
            }
        }
    }

    private boolean isConnectedTo(int t) {
        graph.validVertex(t);
        return visited[t];
    }

    public Iterable<Integer> pathTo(int t) {
        ArrayList<Integer> path = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return path;
        }

        int cur = t;
        while (cur != source) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(source);

        Collections.reverse(path);
        return path;
    }


    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/cc.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6: " + sspath.pathTo(6));
        System.out.println("0 -> 4: " + sspath.pathTo(4));
        System.out.println("0 -> 5: " + sspath.pathTo(5));
    }
}
