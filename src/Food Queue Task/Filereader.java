import java.io.*;
import java.util.*;
import java.util.Queue;
import java.util.PriorityQueue;
public class Filereader{
  public Filereader(){
    @SuppressWarnings("unchecked")
    PriorityQueue<String>[] pq = new PriorityQueue[24];
    for(int i  =0; i<24; i++){
    pq[i] =new PriorityQueue <String>();
    }

     File name = new File("foods.txt");  
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name)); 
			String text,output="";
			while((text=input.readLine())!= null) 
			{
        pq[text.length()].add(text);
			}
      for(int i = 0;i< 5; i++){
        pq[12].poll();
       }
        System.out.println(pq[12].peek());
		}
		catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
   // The word is elderberries
 }
}