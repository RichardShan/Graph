package tech.fun.traverse.dfs.applications;

import tech.fun.graph.AdjSet;
import tech.fun.graph.Graph;

import java.util.Arrays;

/**
 * @author Richard Shan
 * @since 2024-06-07
 */
public class BiPartitionDetection {
    private final boolean[] visited;
    private final int[] colors;
    private final Graph graph;

    private boolean biPartition = true;

    public BiPartitionDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.colors = new int[graph.getV()];
        

        Arrays.fill(visited, false);
        Arrays.fill(colors, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (!dsfBiPartition(v, v)) {
                    biPartition = false;
                    break;
                }
            }
        }
    }

    private boolean dsfBiPartition(int v, int color) {
        visited[v] = true;
        colors[v] = color;

        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (!dsfBiPartition(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBiPartition() {
        return biPartition;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/cc.txt");
        BiPartitionDetection biPartitionDetection = new BiPartitionDetection(g);
        System.out.println("bi partition: " + biPartitionDetection.biPartition);

        Graph g1 = new AdjSet("graphs/nonBipartition.txt");
        BiPartitionDetection loopDetection1 = new BiPartitionDetection(g1);
        System.out.println("bi partition: " + loopDetection1.biPartition);

    }
}
