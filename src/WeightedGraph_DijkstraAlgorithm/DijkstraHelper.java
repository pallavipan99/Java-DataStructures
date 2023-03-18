import java.util.*;

public class DijkstraHelper {

    WeightedGraph g;
    private HashSet<Vertex> visited;
    private HashMap<Vertex, Vertex> predecessors;
    private HashMap<Vertex, Integer> distance;
    private PriorityQueue<VertexDistance> queue;

    public DijkstraHelper(WeightedGraph wg) {
        g = wg;
    }


    /**  Finds the shortest path from source to all other vertices
      */
    public void createPaths(Vertex source) {
      visited = new HashSet<Vertex>();
      distance = new HashMap<Vertex, Integer>();
      predecessors = new HashMap<Vertex, Vertex>();
      queue = new PriorityQueue<VertexDistance>();

      for (Vertex v : g.getVertices())
        distance.put(v,Integer.MAX_VALUE);

      distance.put(source, 0);
      predecessors.put(source,null);
      queue.add(new VertexDistance(source,0));

      while (queue.size() > 0) {
          Vertex v = queue.poll().v;
          if (visited.contains(v))
              continue;

          for (Vertex target : g.getNeighbors(v))
          {
           if (visited.contains(target))  // not required here but could save steps
                continue;

              // If you can find a shorter path, update path in Map
              int altDist = distance.get(v) + g.getDistance(v, target);
              if (distance.get(target) > altDist){

                distance.put(target, altDist);
                predecessors.put(target, v);

                queue.add(new VertexDistance(target,distance.get(target)));
                // NOTE: the vertex may already be in the queue with a longer distance
                // You may be inserting a duplicate.  This is OK because the shorter
                // will take precedence.   When you reach the old (longer path) it will
                // be ignored as visited

              }
            }
            visited.add(v);
      }

    }

    public String getShortestPath(Vertex target)
    {
	 // After calling Create paths.  Use the predecessor map in conjunction with
	 // the distance map to construct a path with distances from the
	 // source vertex to the target.

     return "STUB"; // this is a placeholder so the file will compile
    }


  private class VertexDistance implements Comparable<VertexDistance>{
    private Vertex v;
    private int d;

    VertexDistance(Vertex vert, int dist){
      v = vert;
      d = dist;
    }

    public int compareTo(VertexDistance other){
      return this.d - other.d;
    }


  }


}