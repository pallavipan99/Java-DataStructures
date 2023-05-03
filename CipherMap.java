import java.util.*;
import java.io.*;
public class CipherMap {
  public HashMap<Character,Character> map;
  public HashMap<Character, Character> one;
   public static final String OUTPUT_FILE = "message.txt";
  // No parameter constructor creates a random map.
  public CipherMap(){

    //create an Arraylist of all alphabet characters
    ArrayList<Character> abc = new ArrayList<Character>();
    for (char c = 'a'; c <= 'z'; c++)
        abc.add(c);



    // YOUR TASK
    // load the map with RANDOM recipricol key/value pairings
    // for example, if you pair a -> r also pair r -> a
    one = new HashMap<Character, Character>();
     while(one.size() != 26){
      int randKey = (int)(Math.random()*26);
      int randValue = (int)(Math.random()*26);
      while(randKey == randValue){
         randValue = (int)(Math.random()*26);
      }
      char key = abc.get(randKey);
      char value =  abc.get(randValue);
       if(!one.containsValue(value) && !one.containsValue(value)){
         if(!one.containsKey(key) && !one.containsKey(value)){
          one.put(key, value);
          one.put(value, key);
         }
       }
     }
    map = new HashMap<>();
  }


  // Write an overloaded constructor that takes a String filename
  // and loads the map from a serialized text file.
  public CipherMap(String fileName){
   try {
      FileInputStream fileInput = new FileInputStream(fileName);
      ObjectInputStream objectInput = new ObjectInputStream(fileInput);
      one = (HashMap<Character,Character>)objectInput.readObject();
      objectInput.close();
      fileInput.close();
    }
	catch (Exception obj1) {
		obj1.printStackTrace();
		return;
  	}
  }


  public void serialize(String fileName){
 // Write map to file to store object data
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



  // Write a method called encode that will encode or decode
  // a String value based on the the map
  public String encode(String s){
    String encoded = "";
    //encode
    for(int i = 0; i<s.length(); i++){
      if(s.charAt(i) == ' '){
        encoded+=" ";
      }else{
     encoded+= one.get(s.charAt(i));
      }
    }


    // replace characters based on map

    return "Encoded: "+encoded ;//+" \n Decoded: "+ decoded;
  }
  public static void main(String[] args) {

        CipherMap app1 = new CipherMap("CMAP.txt");
        String secret = app1.encode("gu bkdu xpsnq upwbn vkqx etnpz ximptw hmuka");
       System.out.println(secret);
  }
}