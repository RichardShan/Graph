package tech.fun.traverse.dfs.applications;

import tech.fun.graph.AdjSet;
import tech.fun.graph.Graph;

import java.util.Arrays;

/**
 * @author Richard Shan
 * @since 2024-06-07
 */
public class LoopDetection {
    private final boolean[] visited;
    private final Graph graph;

    private boolean haLoop;

    public LoopDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];

        Arrays.fill(visited, false);

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (dsfHaLoop(v, v)) {
                    haLoop = true;
                    break;
                }
            }
        }
    }

    private boolean dsfHaLoop(int v, int parent) {
        visited[v] = true;

        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (dsfHaLoop(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    private boolean hasLoop() {
        return haLoop;
    }


    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/cc.txt");
        LoopDetection loopDetection = new LoopDetection(g);
        System.out.println("loop: " + loopDetection.haLoop);

        Graph g1 = new AdjSet("graphs/nonLoop.txt");
        LoopDetection loopDetection1 = new LoopDetection(g1);
        System.out.println("loop: " + loopDetection1.haLoop);

    }
}
