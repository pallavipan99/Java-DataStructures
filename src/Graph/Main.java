
class Main {
  public static void main(String[] args) {
    Graph graph = new Graph();
  /*  graph.add("TCNJ", "KEAN");
    graph.add("RUTG", "KEAN");
    graph.add("RUTG", "MSU");
    graph.add("TCNJ", "NJIT");
    graph.add("RUTG", "NJIT");
    System.out.println(graph.getVertices());
    System.out.println("Num vertices = "+graph.getNumVertices());
     System.out.println("Rutgers Neighbors = "+graph.getNeighbors("RUTG"));*/
   System.out.println(graph.dfsPath("Germany", "Thailand"));
   
  }
}