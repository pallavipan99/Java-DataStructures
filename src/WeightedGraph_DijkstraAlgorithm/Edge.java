class Edge{
    private Vertex v1;
    private Vertex v2;
    private int distance;
  

    Edge(Vertex v1, Vertex v2, int d){
        this.v1 = v1;
        this.v2 = v2;
        this.distance = d;
    }

    public Vertex getV1() { return v1; }

    public Vertex getV2() { return v2; }

    public int getDistance() { return distance; }

    public boolean equals(Edge other) {
        return (v1.equals(other.v1) && v2.equals(other.v2)) || (v1.equals(other.v2) && v2.equals(other.v1));
    }

    public String toString(){
      return v1+" <- ["+distance+" mi.] -> "+v2;
    }

    @Override
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }
}