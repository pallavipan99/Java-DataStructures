class Main {
  public static void main(String[] args) {
     SuperList app = new SuperList();
    app.add(1);
    app.add(2);
    app.add(3);
    app.add(4);
    app.add(5);

     app.remove(1);
     System.out.println(app);
    
  }
}