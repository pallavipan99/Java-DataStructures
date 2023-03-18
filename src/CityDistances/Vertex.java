public class Vertex{
  final String name;
  String description;
public Vertex(String name){
  this.name = name;
}
  
  public Vertex(String name, String description){
   this.name = name;
   this.description = description;
    
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public void setDiscription(String discription){
    this.description = description;
  }

  public boolean equals(String name){
    if(name.equals(this.name)){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    if(description ==null){
    return name;
  }else{
    return name+" ("+description+" )";
  }
}
  
}