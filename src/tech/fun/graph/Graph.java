package tech.fun.graph;

import java.util.Iterator;

/**
 * @author Richard Shan
 * @since 2024-06-06
 */
public interface Graph {

    /**
     * 两个顶点是否相邻
     *
     * @param v 顶点 v
     * @param w 顶点 w
     */
    boolean hasEdge(int v, int w);

    /**
     * 某个顶点的所有临边
     *
     * @param v 顶点 v
     * @return adj(v)
     */
    Iterable<Integer> adj(int v);

    /**
     * 顶点 v 的度
     *
     * @param v 顶点 v
     * @return degree(v)
     */
    int degree(int v);

    /**
     * 顶点数
     *
     * @return v
     */
    int getV();

    /**
     * 边数
     *
     * @return e
     */
    int getE();

    void validVertex(int v);
}
