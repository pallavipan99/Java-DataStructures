import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) {
    Graph graph = new Graph();
    System.out.println(graph.dfsPath("Germany","Thailand"));
		System.out.println(graph.bfs("Germany","Thailand"));
	}
}