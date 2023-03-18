/**
Code based on Source code from Baeldung
https://github.com/eugenp/tutorials/tree/master/data-structures

Edited by Chris Haver to make some of the implementation more familiar
to Data Structure Students and to add count attribute to TrieNode.
*/
import java.util.HashMap;
import java.util.Map;
import java.util.*;

class Trie {
  private TrieNode root;
  Trie() {
      root = new TrieNode(); // Note root has no
  }

  public void add(String word){
	  TrieNode current = root;

	  for(char letter: word.toCharArray()){
		  Map<Character,TrieNode>kids = current.getChildren();
		  if(kids.containsKey(letter)){ //letter is present
		 	 current = kids.get(letter);
		 	 current.incrementCount();
		  }else{ //letter is not present
		  	kids.put(letter, new TrieNode());
		  	current = kids.get(letter);
		  }
	  }
	  current.setEndOfWord(true);
  }

  public boolean contains(String word){
	  TrieNode current = root;
	  char[]arr = word.toCharArray();
	  for(int i = 0; i<arr.length; i++){
	  	Map<Character, TrieNode> kids = current.getChildren();
	  	if(kids.containsKey(arr[i])){
			current = kids.get(arr[i]);
                }else{
                 return false;
                }
	  }
	  return current.isEndOfWord();
  }

  public String top5MostLikely(String word){
  	 char[] likelyWords = new char[5];
  	 String likelyStr = "";
	 HashMap<Integer, Character> map = new HashMap<>();
	 int sumOfPossibilities = 0;
	 for(int i=0; i<5; i++){
	 TrieNode current = root;
	 Map<Character,TrieNode> kids = new HashMap<>();
	 for(char letter : word.toCharArray()){
	 kids = current.getChildren();
	 if(kids.containsKey(letter)){
	 current = kids.get(letter);
	 }
	 else{
	 return "_";
	 }
	}
	 kids = current.getChildren();
	 int most = 0;
	 char likely = '_';
	 for(Map.Entry<Character,TrieNode> entry : kids.entrySet()){
	 TrieNode child = entry.getValue();
	 char letter = entry.getKey();
	 int count = child.getCount();
	 map.put(count, letter);
	 sumOfPossibilities = sumOfPossibilities + count;
	 }
	}
	TreeSet<Integer> setOfKeys = new TreeSet(map.keySet());
	NavigableSet<Integer> sortedSet = setOfKeys.descendingSet();
    for(int k=0; k< 5 && k < sortedSet.size(); k++){
	int val = sortedSet.pollFirst();
	likelyWords[k] = map.get(val);

	likelyStr = likelyStr + map.get(val) + " => " + (val * 100/ sumOfPossibilities) + " % \t";
	}
	return likelyStr;
	}

public char mostLikelyNextChar(String word) {
    TrieNode current = root;
    Map<Character, TrieNode> kids = new HashMap<>();
    for (char letter : word.toCharArray()) {
        kids = current.getChildren();
        if (kids.containsKey(letter)) {
            current = kids.get(letter);
        } else {
            return '_';
        }
    }
    kids = current.getChildren();
    int max = 0;
    char mostLikelyLetter = '_';
    for (Map.Entry<Character, TrieNode> entry : kids.entrySet()) {
        TrieNode child = entry.getValue();
        char letter = entry.getKey();
        int count = child.getCount();
        if (count > max) {
            max = count;
            mostLikelyLetter = letter;
        }
    }
    return mostLikelyLetter;
}

  public static void main(String[]args){
	  Trie t = new Trie();
	  t.add("hi");
	  t.add("high");
	  t.add("low");
	  System.out.println(t.root.getChildren());
	  System.out.println(t.contains("hi"));
	  System.out.println(t.mostLikelyNextChar("h"));

  }

	/***********   INNER CLASS ****/
	class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean endOfWord;
    private int count;

    public TrieNode(){
      children = new HashMap<>();
      endOfWord = false;
      count = 1;
    }

    private Map<Character, TrieNode> getChildren() {
        return children;
    }

    private void incrementCount(){
      count++;
    }

    private int getCount(){
      return count;
    }

    private boolean isEndOfWord() {
        return endOfWord;
    }

    private void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public String toString(){
      return "("+count+")";
    }
	}
}



