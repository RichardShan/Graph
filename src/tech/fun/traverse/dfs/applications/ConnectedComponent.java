package tech.fun.traverse.dfs.applications;

import tech.fun.graph.AdjSet;
import tech.fun.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Richard Shan
 * @since 2024-06-07
 */
public class ConnectedComponent {

    private final int[] visited;
    private int ccCount = 0;

    private final Graph graph;

    private final ArrayList<Integer>[] cc;

    public ConnectedComponent(Graph graph) {
        this.graph = graph;

        this.visited = new int[graph.getV()];

        Arrays.fill(visited, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                dfsRecursive(v, ccCount);
                ccCount++;
            }
        }

        cc = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++) {
            cc[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            cc[visited[v]].add(v);
        }
    }

    private void dfsRecursive(int v, int ccId) {
        visited[v] = ccId;
        for (Integer w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfsRecursive(w, ccId);
            }
        }
    }

    public boolean isConnected(int v, int w) {
        graph.validVertex(v);
        graph.validVertex(w);
        return visited[v] == visited[w];
    }


    public int count() {
        return ccCount;
    }

    public ArrayList<Integer>[] getCc() {
        return cc;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/cc.txt");
        ConnectedComponent cc = new ConnectedComponent(g);
        System.out.println("Connected Component: " + cc.count());
        System.out.println("Is connected: " + cc.isConnected(0, 6));
        System.out.println("Is connected: " + cc.isConnected(0, 5));
        ArrayList<Integer>[] ccs = cc.getCc();
        for (int ccId = 0; ccId < ccs.length; ccId++) {
            System.out.printf("%d: ", ccId);
            System.out.println(ccs[ccId]);
        }
    }
}
