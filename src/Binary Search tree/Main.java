class Main {
  public static void main(String[] args) {
    /*BST app = new BST();
     String word = "kaleidoscope";
    BST<Character> bst = new BST<>();
    for (int i = 0; i < word.length(); i++)
        bst.add(word.charAt(i));
    System.out.println("Size => "+bst.size());
    System.out.println("In Order => "+bst.inOrder());
    System.out.println("Pre Order => "+bst.preOrder());
   System.out.println("Post Order => "+bst.postOrder());
   System.out.println("Contains 'i' => "+bst.contains('i'));
    System.out.println("Contains 't' => "+bst.contains('t'));*/

   /*BST<Integer> tree = new BST<>();
    int[] nums = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
    for (int i: nums)
      tree.add(i);

    tree.print();
    tree.rotateRight();
    tree.print();
  }*/

  BST<Integer> tree = new BST<>();
    int[] nums = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
    for (int i: nums)
      tree.add(i);

    
    tree.print();
    tree.remove(99); // Delete a leaf
    tree.print();
    tree.remove(17); // Delete a 1-Child Node
    tree.print();
    tree.remove(23); // Delete a 2-Child Node
    tree.print();
    tree.remove(45); // Delete the root
    tree.print();

}
}