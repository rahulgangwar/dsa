package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Bipartite: A graph is bipartite if you can color the graph such that no 2 adjacent nodes have
 * same color
 */
public class IsGraphBipartite {

  // BFS solution
  public boolean isBipartiteUsingBFS(int[][] graph) {
    int[] colorOfNode = new int[graph.length];

    // There can be multiple disconnected graph so in order to make sure we traverse all
    // We loop on all the edges and do BFS on all
    for (int i = 0; i < graph.length; i++) {
      if (colorOfNode[i] != 0) {
        continue;
      }

      Queue<Integer> queue = new ArrayDeque<>();
      queue.offer(i);
      colorOfNode[i] = 1;

      while (!queue.isEmpty()) {
        int node = queue.poll();
        int color = colorOfNode[node];

        for (int neighbour : graph[node]) {
          int colorOfNeighbour = colorOfNode[neighbour];

          if (colorOfNeighbour == color) {
            return false;
          }

          if (colorOfNeighbour == 0) {
            queue.offer(neighbour);
            colorOfNode[neighbour] = -1 * color;
          }
        }
      }
    }

    return true;
  }

  // DFS solution
  public boolean isBipartiteDFS(int[][] graph) {
    int[] colorOfNode = new int[graph.length];

    for (int i = 0; i < graph.length; i++) {
      if (colorOfNode[i] != 0) {
        continue;
      }

      // Color the starting node
      colorOfNode[i] = 1;
      if (hasColorConflict(graph, i, colorOfNode)) {
        return false;
      }
    }

    return true;
  }

  private boolean hasColorConflict(int[][] graph, int node, int[] colorOfNode) {
    int color = colorOfNode[node];
    System.out.println("node: " + node);
    for (int neighbour : graph[node]) {
      int colorOfNeighbour = colorOfNode[neighbour];
      if (colorOfNeighbour == color) return true;

      if (colorOfNeighbour == 0) {
        colorOfNode[neighbour] = -1 * color;
        if (hasColorConflict(graph, neighbour, colorOfNode)) {
          return true;
        }
      }
    }
    return false;
  }
}
