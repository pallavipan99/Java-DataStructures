import com.sun.jdi.event.EventIterator;
import java.util.EmptyStackException;

public class SuperList<E>{
private int size;
private ListNode<E> root, end;

public SuperList(){
size = 0;
root = end = null;
}

class ListNode<E>{
private E value;
private ListNode<E> previous, next;

public ListNode(E val){
value = val;
previous = next = null;
}

public E getVal() {return value;}
public ListNode<E> getPrev() {return previous;}
public ListNode<E> getNext() {return next;}

public void setVal(E val) {value = val;}
public void setPrev(ListNode <E> node) {previous = node;}
public void setNext(ListNode<E> node) {next = node;}

public boolean hasPrev() {return previous != null;}
public boolean hasNext() {return next != null;}
}

  public void add(E val){
    ListNode<E> newNode = new ListNode<>(val);
    if(size ==0){
      root = newNode;
      end = newNode; 
      size++;
    }else{
      ListNode<E> temp = end;
      end.setNext(newNode);
      end = newNode;
      end.setPrev(temp);  
      size++;
    }
  }

  public void add(int index, E val){
    if(index<0 || index>size){
      throw new ArrayIndexOutOfBoundsException();
      }
    
   if(index == 0){
      ListNode<E> newNode = new ListNode<>(val);
      root.setPrev(newNode);
      newNode.setNext(root);
      root = newNode;
      size++;
    }
    
    if(index == this.size){
      this.add(val);
    }
    
    if(index>0 && index< size){
      ListNode<E> newNode = new ListNode<>(val);
      ListNode<E> pNode = root;
      for(int i = 0; i<index-1; i++){
        pNode = pNode.getNext();
      }
      ListNode<E> nNode = pNode.getNext();
      pNode.setNext(newNode);
      newNode.setNext(nNode);
      nNode.setPrev(newNode);
      newNode.setPrev(pNode);
      size++;
    }
  }
    
  
  public String toString(){
    ListNode<E> node = root;
    String str = "[";
    if(node!=null){
      if(size == 1 ){
        str+=node.getVal();
      }
      else{
      while(node.hasNext()){
      str+=node.getVal()+",";
      node= node.getNext();
      if(!node.hasNext()){
            str+=node.getVal();
        }
       }
     }
    }
    
    str+="]";
    return str; 
  }
  
  public int size(){
    return size; 
  }
  
public E remove(int index){
   if(index == this.size()-1){
     ListNode<E> temp = end;
     end = end.getPrev();
     end.setNext(null);
     size--;
     return temp.getVal();
   }
    if(index == 0){
    ListNode<E> temp = root;
    root = root.getNext();
    root.setPrev(null);
    size--;
    return temp.getVal();
  }
    if(index>0 && index<(size-1)){
      ListNode<E> pNode = root;
      for(int i = 0; i<index-1; i++)
        pNode = pNode.getNext();
      ListNode<E> temp = pNode.getNext();
      ListNode<E> nNode = pNode.getNext().getNext();
      pNode.setNext(nNode);
      nNode.setPrev(pNode);
      size--;
   }
  
  return null;
  }

  public E get(int index){
    if(index>size || index<size){
      throw new ArrayIndexOutOfBoundsException();
    }
    if(index == 0){
      ListNode<E> temp = root;
      return temp.getVal();
    }
    
    if(index == this.size()-1){
      ListNode<E> temp = end;
      return temp.getVal();
    }

    if(index>0 && index<(size-1)){
      ListNode<E> pNode = root;
      for(int i = 0; i<index-1; i++)
        pNode = pNode.getNext();
      ListNode<E> temp = pNode.getNext();
      ListNode<E> nNode = pNode.getNext().getNext();
      return temp.getVal();  
    }
    return null;
  }

  public E set(int index, E val){
    E initialVal = this.get(index);
    if(index>size || index<size){
      throw new ArrayIndexOutOfBoundsException();
    }

    if(index == 0){
      ListNode<E> temp = root;
      temp.setVal(val);
    }
    
    if(index == this.size()-1){
      ListNode<E> temp = end;
      temp.setVal(val);
    }
    
    if(index>0 && index<(size-1)){
      ListNode<E> pNode = root;
      for(int i = 0; i<index-1; i++)
        pNode = pNode.getNext();
      ListNode <E> temp = pNode.getNext();
      ListNode<E> nNode = pNode.getNext().getNext();
    }
    return initialVal;
  }
  
  public boolean contains(E val){
    ListNode<E> newNode = root;
    int num = 0;
    for(int i =0; i<size; i++){
      if(newNode.getVal().equals(val)){
        newNode = newNode.getNext();
        return true;
      }
      newNode = newNode.getNext();
    }
    return false;
  }
  
  public boolean isEmpty(){
    if(size == 0){
      return true;
    }else{
      return false;
    }
  }

  public E pop(){
    if(size == 0){
      throw new EmptyStackException(); 
    }
    return this.remove(this.size()-1);
  }
  
  public void clear(){
    root = null;
    end = null;
    size = 0;
  }

  
   public E stackPeek(){
      if(size ==0){
      throw new EmptyStackException(); 
      }
   ListNode<E> lastTemp = end;
    return lastTemp.getVal();
  }
  
   public void push(E var){
    add(var);
  }

  public E poll(){
    if(size == 0){
      return null;
    }
  return this.remove(0);
  }

  public E queuePeek(){
    E val;
    if(size == 0){
      val = null; 
     }else{
      ListNode<E> lastTemp = root;
      val = lastTemp.getVal();  
     }
      return val;
  }
}

