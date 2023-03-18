import java.util.ArrayList;
import java.util.HashSet;
public class Main {
  public static void main(String[] args) {
    HashSet<Integer> numbersInSets = new HashSet<Integer>();
    ArrayList<HashSet<Integer>> arrayList = new ArrayList<>();
    HashSet<Integer> sameNum = new HashSet<Integer>();
    int numSets = (int)(Math.random()*8)+3;
    for (int i = 0; i < numSets; i++) {
      HashSet<Integer> hashSet = new HashSet<>();
      while (hashSet.size() < 10) {
        int randNum = (int)(Math.random()*30)+1;
        hashSet.add(randNum);
      }
      arrayList.add(hashSet);
    }
     int i = 1;
    for (HashSet<Integer> hashSet : arrayList) {
      System.out.println("Set "+ i+" = >"+ hashSet);
      i++;
    }

   for(int y = 0; y<arrayList.size()-1; y++){
     if(y ==0){
      sameNum = intersection(arrayList.get(y),arrayList.get(y+1));
     }else{
      sameNum = intersection(sameNum, arrayList.get(y+1));
     }
   }
    System.out.println("Intersection => "+ sameNum);
    
   for(int x = 0; x<arrayList.size(); x++) {
      for(int num: arrayList.get(x)){
        numbersInSets.add(num);
      }
    }
    System.out.println("Union => "+numbersInSets);
  }

  public static HashSet<Integer>intersection(HashSet<Integer> hashSet1, HashSet<Integer> hashSet2){
    HashSet<Integer> intersection = new HashSet<Integer>();
    for(int num: hashSet1){
      if(hashSet2.contains(num)){
        intersection.add(num);
      }
    }
         return intersection;  
  }

  public static HashSet<Integer>union(HashSet<Integer> hashSet1, HashSet<Integer> hashSet2){
    HashSet<Integer> union = new HashSet<Integer>();
    for(int num: hashSet1){
      union.add(num);
    }
    for(int number: hashSet2){
      union.add(number);
    }

    return union;
  }
  
}