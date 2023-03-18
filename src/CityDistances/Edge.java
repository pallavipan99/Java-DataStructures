public class Edge{
  Vertex v1;
  Vertex v2;
  int d;
  public Edge(Vertex v1, Vertex v2, int d){
    this.v1 =  v1;
    this.v2 = v2;
    this.d = d;
  }

  public Vertex getV1(){
    return v1;
  }

  public Vertex getV2(){
    return v2;
  }

    public int getDistance(){
    return d;
  }

  public boolean equals(Edge one){
    if(one.getV1().equals(v1) && one.getV2().equals(v2)){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    return "The distance from "+v1+" to "+ v2+" is "+d;
  }
}