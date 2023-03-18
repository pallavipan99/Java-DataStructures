public class BST<E extends Comparable<E>>{
  private TreeNode<E> root;
  private int size;
  private String st = ""; // use this to help format output

  public BST(){
    root = null;
    size = 0;
  }

  public void add(E val){
    if(root == null){
      root = new TreeNode<E>(val);
      size++;
    }
    else{
      add(root, val);
    }
  }

  private void add(TreeNode<E> curr, E val){
    int comp = val.compareTo(curr.value);
     if(comp == 0){
     }
    
    if(comp < 0){
      if(curr.left == null){
        curr.left = new TreeNode<>(val);
        size++;
      }else{
        add(curr.left, val);
      }
    }

    if(comp>0){
      if(curr.right == null){
        curr.right = new TreeNode<E>(val);
        size++;
      }else{
        add(curr.right, val);
      }
    }
  
  
  }
  /******* INNER CLASS********/
private class TreeNode<E extends Comparable<E>>{
  private E value;
  private TreeNode<E> left;
  private TreeNode<E> right;


  private TreeNode(E val){
    value = val;
    left = right = null;
  }
 public String toString(){
   return value.toString();
 } 
}

  public String inOrder(){
     st = "";
     if(size == 0){
       return "[]";
     }else{
      st+="[";
      inOrder(root);
      return st+"]";
     }
 }
  
  public String inOrder(TreeNode<E> node){
	if (node != null){	
    inOrder(node.left);
  if(st.length() == ((size*3)-2)){
    st+=node.value;
  }else{
    st+=node.value+", ";
  }
		inOrder(node.right);  
    return st;
	}else{
   return"";
  }
}
  
 public String preOrder(){
   st = "";
   if(size == 0){
     return "[]";
   }else{
      st+="[";
    preOrder(root);
     return st+"]";
 }
}
  
  public String preOrder(TreeNode<E> node){
	if(node !=null){	
     if(st.length() == ((size*3)-2)){
    st+=node.value;
     }else{
    st+=node.value+", ";
    }
		preOrder(node.left);
		preOrder(node.right);
	}
    return st;
}
 

 public String postOrder(){
   st = "";
    if(size == 0){
     return "[]";
   }else{
      st+="[";
    postOrder(root);
    return st+"]";
 }
   
}

public String postOrder(TreeNode<E> node)
{
	if (node !=null)
	{ postOrder(node.left);
		postOrder(node.right);
    if(st.length() == ((size*3)-2)){
    st+=node.value;
  }else{
    st+=node.value+", ";
  }
	}
  return st;
}

  public int size(){
    return size;
  }

  public boolean contains (E value){
    boolean has = contains(root,value);
    return has;
  }
  
  public boolean contains (TreeNode<E>node, E value){
    if(node == null){
      return false;
    }
    if (node.value.equals(value)){
        return true;
    }
    if (node.value.compareTo(value) > 0){
        return contains(node.left, value);
    }
    return contains(node.right, value);
  }

  public TreeNode<E> remove(E value){
    if(root == null){
      return null;
    }
    if(contains(root,value)){
      size--;
      root = remove(root,value);
    }
    return root;
  }
  
  public TreeNode<E> remove(TreeNode<E> node, E value){
    TreeNode<E> parent = null;
    TreeNode<E> next = null;
    if (node == null){
        return null;
    }
    if (value.compareTo(node.value) < 0){
        node.left = remove(node.left, value);
    }else if(value.compareTo(node.value) > 0){
        node.right = remove(node.right, value);
    }else{
      if(node.left == null && node.right == null){ 
        return null;
      }else if(node.left == null){ 
        return node.right;
      }else if(node.right==null){ 
        return node.left;
      }else{ 
        parent = node;
        next = node.right;
        while(next.left != null){
          parent = next;
          next = next.left;
        }
        if(parent !=node){
          parent.left = next.right;
          next.right = node.right;
        }
        next.left = node.left;
        return next;
      }
    }
    return node;
  }
  /*************   PRINT  **************/
	public void print()
	{
		if (root == null)
			System.out.print("Empty Tree");
		print(root, 0, "");
		System.out.println();
	}
	private void print(TreeNode<E> curr, int depth, String s)
	{
    	if (curr == null)
        	return;
    	for (int i = 1; i <= depth - 1; i++)
        	System.out.print("|\t");
    	if (depth == 0)
        	System.out.println("[" + curr.value + "] <- root (L___ left, R___ right)");
    	if (depth > 0)
    	{
        	System.out.print(s);
            System.out.println(curr.value);
        }
  		print(curr.left, depth + 1, "L___"); // indicates left or "less than" side
  		print(curr.right, depth + 1, "R___");  // indicate right or "greater than" side
  }
    /********** END PRINT **********/

  public void rotateRight(){
    TreeNode<E>oldRoot;
    TreeNode<E>newRoot;
    TreeNode<E>val;
    TreeNode<E>valLeft;
    oldRoot = root; 
    newRoot = root.left; 
    val = root.left.right;
    valLeft = root.left.left;
    root = root.left; 
    root.left = valLeft;
    root.left.right = null;
    root.right = oldRoot;
    root.right.left = val;
 }

  public void rotateLeft(){
    TreeNode<E>oldRoot;
    TreeNode<E>newRoot;
    TreeNode<E>val;
    TreeNode<E>valLeft;
    oldRoot = root; 
    newRoot = root.right; 
    val = root.right.left;
    valLeft = root.right.right;
    root = root.right; 
    root.right = valLeft;
    root.right.left = null;
    root.left = oldRoot;
    root.left.right = val;
 }
}
    
