class Vertex {
    private final String name;
    private String description;

    Vertex(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    Vertex(String n, String d){
        name = n;
        description = d;
    }

    public void setDescription(String d){
      description = d;
    }

    public String toString(){
      String out = name;
      out+=description!=null?" ("+description+")":"";
      return out;
    }

    public boolean equals(Vertex other) {
        return this.name.equals(other.name);
    }

}