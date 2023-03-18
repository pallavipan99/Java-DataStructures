import java.util.*;
import java.io.*;
import org.junit.experimental.theories.Theory;
public class Graph{
  
	private HashMap<String,HashSet<String>> g;
	public Graph(){
		g = new HashMap<String,HashSet<String>>();
    File name = new File("GEODATASOURCE.CSV");
    try{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text = "";
			while((text=input.readLine())!= null){
				String[] countries = text.split(",");
				if(countries.length >= 4){
					if(!g.containsKey(countries[1])){
            HashSet<String> values = new HashSet<String>();
						values.add(countries[3]);
						g.put(countries[1], values);
					} else {
					g.get(countries[1]).add(countries[3]);
					}
				}
			}
		}catch (IOException io){
			System.err.println("Error reading file => "+io);
  	}
	}

	public void add(String v1, String v2){
		if(!g.containsKey(v1)){
			g.put(v1,new HashSet<String>());
		}
		if(!g.containsKey(v2)){
			g.put(v2,new HashSet<String>());
		}
		g.get(v1).add(v2);
		g.get(v2).add(v1);
	}

	public boolean dfs(String v1, String v2){
		return dfs ( v1,  v2, new HashSet<String>());
	}

	private boolean dfs(String curr, String end, Set<String> visited){
		visited.add(curr);
		if(curr.equals(end)){
			return true;
		}
		for(String v : getNeighbors(curr)){
			if(!visited.contains(v) && dfs(v,end,visited)){
				return true;
			}
		}
		return false;
	}

	public Set<String> getNeighbors(String v){
		if(g.containsKey(v)){
			return g.get(v);
		}else{
			return new HashSet<String>();
		}
	}

	public String dfsPath(String v1, String v2){
		Stack<String> stack = new Stack<>();
		Stack<String> visited = new Stack<>();
		stack.add(v1);
		while(stack.size()>0){
			String v = stack.pop();
			visited.add(v);
			if(v.equals(v2)){
				return buildPath(visited);
			}
			for(String neighbor : getNeighbors(v)){
				if(!visited.contains(neighbor)){
					stack.add(neighbor);
				}
			}
		}
		return "not connected";
	}

  public String bfs(String v1, String v2){
	Queue<String> q = new LinkedList<>();
	Set<String> visited = new HashSet<>();
	HashMap<String, String> track = new HashMap<>();   
	q.add(v1);
	while(!q.isEmpty()){
		String v = q.poll();
		visited.add(v);
		for(String n : getNeighbors(v)){
			if(!visited.contains(n)){
				q.add(n);
				if(!track.containsKey(n))
					track.put(n,v);
			}
		}
    if(v.equals(v2))
			return bfsPath(v1, v2, track);
	}
	return "no connection";
}

private String bfsPath(String first, String last, HashMap<String, String> map){
  String v = last;
  while(map.containsKey(last)){
    v = map.get(last) + "->" + v;
    last = map.get(last);
  }
  return v;
}   

	private String buildPath(Stack<String> path){
		String output = "";
		while(!path.isEmpty()){
			String v = path.pop();
			output = v+ "->" + output;
			while(!path.isEmpty() && !getNeighbors(v).contains(path.peek())){
				path.pop();
			}
		}
		return output.substring(0,output.length()-3);
	}

	public int numVertices(){
		return g.size();
	}

	public Set<String> getVertices(){
		return g.keySet();
	}
}