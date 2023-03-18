import java.io.*;
import java.util.*;
public class LandFill{
  public LandFill(){
    Stack<String> temp = new Stack<String>();  
  ArrayList<Stack<String>> countiesMun = new ArrayList<Stack<String>>();
    int readLine = 0;
    ArrayList<String> counties = new ArrayList<String>();

    File name = new File("NJMunicipalities.csv");  
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));  
			String text,output="";
			while((text=input.readLine())!= null) {
        readLine ++;
        if(readLine>1){
        if(counties.contains(text.split(",")[1])){
          countiesMun.get(counties.indexOf(text.split(",")[1])).push(text.split(",")[2]+" of "+text.split(",")[0]);
        }else{
          temp.push(text.split(",")[2]+" of "+text.split(",")[0]);
            counties.add(text.split(",")[1]);
            countiesMun.add(temp);
          temp.pop();
        }
		 }
      }
    }
		catch (IOException io)
		{
			System.err.println("Error reading file => "+io);
		}
   


    int randCounty = (int)(Math.random()*counties.size());
    System.out.println("Selected "+counties.get(randCounty)+" county");
    int randNum = 0;
    int count = 0;
    for(int i =0; i<countiesMun.get(randCounty).size(); i++){
        randNum =(int)(Math.random()*6)+1; 
         if(randNum == 1){
            System.out.println(countiesMun.get(randCounty).pop()+" rolled "+randNum+". They get the Landfill!");
           i = countiesMun.get(randCounty).size();
          }
      else{
        if(count ==countiesMun.get(randCounty).size()){
          i = count;
          System.out.println("Will send the garbage to Pennsylvania!");

        }
          count++;
          System.out.println(countiesMun.get(randCounty).pop()+" rolled "+randNum+". No Landfill!");
      }

    }

    
  }
}