import java.util.*;
public class HashSetSim{

	public static void main(String[] args) {
	    int n = 100, iterations = 0;

	    // Create an array of n random decimal numbers
	    double[] orig = new double[n];
	    for (int i = 0; i <n; i++)
		orig[i] = (double)(int)(Math.random()*n)/100;

	    System.out.println("\nArrayList As Set");
	    ArrayList<Double> list = new ArrayList<>();
	    long start = System.currentTimeMillis(); // START CLOCK
	    for (int i = 0; i < n; i++){
	      double d = orig[i];  // get val from original array
	      boolean contains = false;
	      for (int j = 0; j < list.size(); j++){
			  iterations++;
			  if (d == list.get(j)){ // element already in list
				  contains = true;
				  break;
			  }
	      }
	      if (!contains) // add only if not already there
		  list.add(d);
	    }

		double timeInSecs = (System.currentTimeMillis()- start) /1000.0; // STOP CLOCK

		/***********  OUTPUT  ********/
		//Collections.sort(list);  // Optional Sort
		System.out.print("numItems = "+list.size()+", iterations = "+iterations);
		System.out.println(", time = "+timeInSecs);
		if ( n < 200) // Only print full list if n < 200
		System.out.println("\n"+list);
    
    System.out.println("\nHashSet simulation");
    iterations = 0;
    int numItems = 0, hashSize = n;
    LinkedList<Double>[] hashList = new LinkedList[hashSize];
    start = System.currentTimeMillis(); // START CLOCK
    for(int i = 0; i<n; i++){
      Double d = orig[i];
     // System.out.println(d+" -->"+d.hashCode());
      int code = Math.abs(d.hashCode()%hashSize);

      if(hashList[code] == null ){ //empty 
        hashList[code] =  new LinkedList<Double>();
        hashList[code].add(d);
        iterations++;
        numItems++;
        
      }
     else{ // search for element in current list
       boolean contains = false;
       for(double x: hashList[code]){
         iterations++;
         if(d == x){
           contains = true;
           break;
         }
        }   
       if(!contains){
         hashList[code].add(d);
         numItems++;
       }
      } 
    } //end for 

    timeInSecs = (System.currentTimeMillis()-start)/1000.0;

    System.out.print("numItems = "+numItems+", iterations = "+iterations);
    System.out.println(", time = "+ timeInSecs);
    
    
	}

}