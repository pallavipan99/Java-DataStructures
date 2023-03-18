import java.util.*;

public class WeightedGraph {
  Map<Vertex, HashSet<Edge>> map;
  DijkstraHelper dh;

  public WeightedGraph() {
    map = new HashMap<Vertex, HashSet<Edge>>();
  }

  public String findPath(Vertex v1, Vertex v2) {
    dh = new DijkstraHelper(this);
    dh.createPaths(v1);
    return dh.getShortestPath(v2);
  }

  public void addEdge(Vertex v1, Vertex v2, int d) {
    addEdge(new Edge(v1, v2, d));
  }

  public void addEdge(Edge edge) {
    if (!map.containsKey(edge.getV1()))
      map.put(edge.getV1(), new HashSet<Edge>());
    if (!map.containsKey(edge.getV2()))
      map.put(edge.getV2(), new HashSet<Edge>());
    map.get(edge.getV1()).add(edge);
    map.get(edge.getV2()).add(edge);
    System.out.println(map.get(edge.getV1()));
    System.out.println(map.get(edge.getV2()));
  }

  public int getDistance(Vertex v1, Vertex v2) {
    for (Edge e : map.get(v1)) {
      if (e.getV2().equals(v2) || e.getV1().equals(v2))
        return e.getDistance();
    }
    return Integer.MAX_VALUE;
  }

  public Vertex getVertex(String s) {
    for (Vertex v : getVertices()) {
      if (v.getName().equals(s))
        return v;
    }
    return new Vertex(s);
  }

  public Set<Vertex> getNeighbors(Vertex v) {
    Set<Vertex> n = new HashSet<Vertex>();

    if (map.get(v) != null)
      for (Edge e : map.get(v)) {
        if (e.getV1().equals(v))
          n.add(e.getV2());
        if (e.getV2().equals(v))
          n.add(e.getV1());

      }
    return n; // return neighbor vertex set
  }

  public Set<Vertex> getVertices() {
    return map.keySet();
  }

  public Set<Edge> getEdges() {
    Set<Edge> edges = new HashSet<Edge>();
    for (Map.Entry<Vertex, HashSet<Edge>> entry : map.entrySet()) {
      edges.addAll(entry.getValue());
    }
    return edges;
  }
}
 