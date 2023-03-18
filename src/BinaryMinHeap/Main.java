class Main {
  public static void main(String[] args) {
   boolean passing = true;
for (int i = 0; i < 1000; i++){ // run 1000 tests
      BinaryMinHeap bmh = new BinaryMinHeap();
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        // add a random number of elements 
        int howManyToAdd = (int)(Math.random()*32+1);;
      for (int j = 0; j < howManyToAdd ; j++){
        int numToAdd = (int)(Math.random()*89+10);
        bmh.add(numToAdd);
        pq.add(numToAdd);
        if (!bmh.toString().equals(pq.toString())){
            passing = false;
            System.out.println("Failed during add>>\n"+bmh+"|"+"\n"+"|"+pq+"|");
        }
      }
      // poll a random number of elements
      int howManyToPoll = (int)(Math.random()*howManyToAdd);
      for (int k = 0; k < howManyToPoll ; k++){
        bmh.poll();
        pq.poll();
        //System.out.println(bmh+"\n"+pq);
        if (!bmh.toString().equals(pq.toString())){
            passing = false;

  }
}