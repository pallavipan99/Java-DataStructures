import java.util.*;
import java.io.*;
import java.util.*;
public class Graph{
private HashMap<String,HashSet<String>>g;
  
  public Graph(){
    g = new HashMap<String, HashSet<String>>();
    File name = new File("Geodata.csv");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";
			while((text=input.readLine())!= null) // Keep reading until end of file (null)
			{
        String[] arr = text.split(",");
        for(int i = 0; i<arr.length; i++){
          if(arr[i].length()>2){
            if(!g.containsKey(arr[i])){
              g.put(arr[i], new HashSet<String>());
            }else{
             if(g.containsKey(arr[i])){
               for(int j =0; j<arr.length-1; j++){
                 if(j!=1){
                   g.get(arr[i]).add(arr[j]);
                 }
               }
             }
            }
          }
        }
        
				//output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}
			System.out.println("FILE CONTENTS:\n"+output);

		}
		catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
  }

  public void add(String v1, String v2){
    if(!g.containsKey(v1)){
      g.put(v1, new HashSet<String>());
    }
    
    if(!g.containsKey(v2)){
      g.put(v2, new HashSet<String>());
    }
    g.get(v1).add(v2);
    g.get(v2).add(v1);
   }

  public boolean dfs (String v1, String v2){
      return dfs(v1, v2, new HashSet<String>());
    }
  // recursive dfs return true if path v1...>v2 exists

  private boolean dfs(String curr, String end, Set<String> visited){
   visited.add(curr);
    if(curr.equals(end)){
      return true;
    }
    for(String v: getNeighbors(curr)){
      if(!visited.contains(v)&&dfs(v,end,visited)){
        return true;
      }
    }
    return false;
    }
  
   //Breadth First Search
  public String bfs(String v1, String v2){
    Queue<String> q = new LinkedList<String>();
    Set <String> visited = new HashSet<String>(); 
    q.add(v1);
    while(!q.isEmpty()){
      String v = q.poll(); // poll from the q
      visited.add(v);// add to visited
       if(v.equals(v2)){ // check if equal v2 
         return "FOUND"; // return "FOUND" then later path
       }
     for(String neighbor: getNeighbors(v)){
      if(!visited.contains(neighbor))
        q.add(neighbor); // add the neighbors to q
      }  
    }
    return "NO CONNECTION";
  }

  
  private String bfsPath(HashMap<String, String> edges, String start, String end) {
        String s = end;
        while(edges.containsKey(end)) {
            s = edges.get(end) + "->" + s;
            end = edges.get(end);
        }   
        return s;
    }   

 // public String bfs 
  

  public int getNumVertices(){ return g.size();
     }

  public Set<String> getVertices(){
    return g.keySet();
  }

  public String dfsPath(String v1, String v2){
    //String v = " ";
    Stack<String> stack = new Stack<>();
    Stack<String> visited = new Stack<>(); //use this for visited AND to make path
    stack.add(v1);
    while(stack.size()>0){
      String v = stack.pop(); // pop from stack
      visited.add(v);
      if(v.equals(v2)){ // check if current vertext is equal v2
         return buildPath(visited); // if equal return buildPath(visited)
      }
    
      for(String neighbor: getNeighbors(v)){
        if(!visited.contains(neighbor)){
          stack.add(neighbor);
        }
       }  
    }
    
    return "No Connection";
  }

  private String buildPath(Stack<String> path){
    String output = " ";
    while(!path.isEmpty()){
      String v = path.pop();
      output=v+" -> "+output;
      while(!path.isEmpty() && !getNeighbors(v).contains(path.peek()))
        path.pop();
    }
    return output.substring(0, output.length()-3);

  }
  

  
  public Set<String> getNeighbors(String v){
  if(g.containsKey(v)){
   return g.get(v);
  }else{
    return new HashSet<String>();
   }
 } 
}
