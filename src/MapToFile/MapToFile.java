import java.io.*;
import java.util.*;

public class MapToFile {
  public MapToFile(){
    
  }
 
  public static final String OUTPUT_FILE = "StateMap.txt";  // serialization
  private String fileName; // original file read name
  private TreeMap<String,String> map;

  public MapToFile(String file){
	  fileName = file;
	  map = new TreeMap<String,String>();
  }

  public TreeMap<String,String> getMap(){
  	  return map;
  }

  public void serialize(){ // Write map to file to store object data
	  try {
	      FileOutputStream myFileOutStream = new FileOutputStream(OUTPUT_FILE);
	      ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
	      myObjectOutStream.writeObject(map);
		  myObjectOutStream.close();
		  myFileOutStream.close();
	    }
	    catch (IOException e) {
	           e.printStackTrace();
        }
  }

  public void deserialize(){ // Read map from object dataa file
	try {
      FileInputStream fileInput = new FileInputStream(OUTPUT_FILE);
      ObjectInputStream objectInput = new ObjectInputStream(fileInput);
      map = (TreeMap<String,String>)objectInput.readObject();
      objectInput.close();
      fileInput.close();
    }
	catch (Exception obj1) {
		obj1.printStackTrace();
		return;
	}
  }

  public void read(){   // Read initially from file
		File name = new File("StateAndCaptial.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine())!= null)
			{
				String[] fields = text.split("=");
				map.put(fields[0],fields[1]);
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

   public void print(){
      for (Map.Entry<String,String> entry: map.entrySet())
			System.out.println(entry.getKey()+" capital => "+entry.getValue());
   }

   public String set (String k, String v){  // Set K/V pair in Map
	   String prev = map.get(k);
	   map.put(k,v);
	   return prev;

  }

  public static void main(String[]args){
	MapToFile mtf = new MapToFile("StateAndCapital.txt");
	mtf.read(); // remove from run
	mtf.print();
	// Part 1
	// change a k/v pair
	// serialize map

	// Part 2
	// deserialize INSTEAD of reading
	// print out to confirm changes saved in serialization

  }
}