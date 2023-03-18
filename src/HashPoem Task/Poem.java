import java.io.*;
import java.util.*;
public class Poem
{
 
	public Poem()
	{
  HashMap<Character,Integer> map = new HashMap<Character, Integer>();
  ArrayList<String>array = new ArrayList<String>();
		File name = new File("poem.txt");  
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name)); 
			String text,output="";
			while((text=input.readLine())!= null) 
			{
        String[] words = text.toLowerCase().split(" ");
        for(int i = 0; i<words.length; i++){
          array.add(words[i]);
        }
			}
      for(String s: array){
      if(map.containsKey(s.toLowerCase().charAt(0)))
        map.put(s.toLowerCase().charAt(0),map.get(s.toLowerCase().charAt(0))+1);
      else
        map.put(s.toLowerCase().charAt(0),1);

	  	}

     for (char key: map.keySet())
       if(map.get(key)%2!=0){
    System.out.println(key+" = "   + map.get(key));
       }

    }
		catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
    
  }


	}
	
