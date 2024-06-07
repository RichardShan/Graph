package tech.fun.traverse.dfs;

import tech.fun.graph.AdjSet;
import tech.fun.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Shan
 * @since 2024-06-07
 */
public class GraphDFS {

    private final List<Integer> preOrder;
    private final List<Integer> postOrder;

    private final boolean[] visited;

    private Graph graph;

    public GraphDFS(Graph graph) {
        this.graph = graph;

        this.preOrder = new ArrayList<>(graph.getV());
        this.postOrder = new ArrayList<>(graph.getV());

        this.visited = new boolean[graph.getV()];

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfsRecursive(v);
            }
        }
    }

    private void dfsRecursive(int v) {
        visited[v] = true;
        preOrder.add(v);
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                dfsRecursive(w);
            }
        }
        postOrder.add(v);
    }


    public List<Integer> getPreOrder() {
        return preOrder;
    }

    public List<Integer> getPostOrder() {
        return postOrder;
    }


    public static void main(String[] args) {
        Graph g = new AdjSet("graphs/adj.txt");
        GraphDFS dfs = new GraphDFS(g);
        System.out.println("PreOrder: " + dfs.getPreOrder());
        System.out.println("PostOrder: " + dfs.getPostOrder());
    }
}
