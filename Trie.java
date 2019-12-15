import java.util.*;
import java.io.*;

class Trie

{
	private class TrieNode{
	Map<Character,TrieNode> children;
	boolean endofword;
	
	public TrieNode(){
		children = new HashMap<>();
		endofword = false;
	}
 }

private final TrieNode root;

public Trie()
 {
 	root = new TrieNode();
 }

 //iterative implementation of insert into trie
 public void insert(String word)
 {
 	TrieNode current = root;
 	for(int i =0;i < word.length();i++)
 	{
 		char ch = word.charAt(i);
 		TrieNode node = current.children.get(ch);
 		if(node == null)
 		{
 			node = new TrieNode();
 			current.children.put(ch,node);
 		}
 		current = node;
 	}
 	//mark the current nodes endofword as true

 	current.endofword = true;
 }

 //Recursive implementation of insert into trie
 public void insertRecursive(String word)
 {
 	insertRecursive(root,word,0);
 }
private void insertRecursive(TrieNode current,String word,int index)
{
  if(index==word.length())
  {
  	current.endofword = true;
  	return;
  }
char ch = word.charAt(index);
TrieNode node = current.children.get(ch);
   
   if(node==null)
   {
   	node = new TrieNode();
   	current.children.put(ch,node);
   }
   insertRecursive(node,word,index+1);
}

//iterative implementation of search into trie
public boolean search(String word)
{
	TrieNode current = root;
	for(int i =0;i<word.length();i++)
	{
		char ch = word.charAt(i);
		TrieNode node = current.children.get(ch);
		if(node == null){
			return false;
		}

		current = node;
	}

	//return true of endword is true else return false
	return current.endofword;
}


//Recursive implemenatation of word into trie
public boolean searchRecursive(String word)
{
	return searchRecursive(root,word,0);
}

private boolean searchRecursive(TrieNode current,String word,int index)
{
	if(index == word.length()){
		//return true of end word is true else return false
		return current.endofword;
	}

	char ch = word.charAt(index);
	TrieNode node = current.children.get(ch);
	if(node == null){
	  return false; 
	}

	return searchRecursive(node,word,index+1);
}

//Delete Recursively  word from trie
public boolean delete(String word)
{
	 return delete(root,word,0);
}

//return true . we should delete parent mapping
private boolean delete(TrieNode current,String word,int index)
{
	if(index == word.length())
	{
		if(!current.endofword){
			return false;
		}
		current.endofword = false;

		//if current has no other mapping than return true otherwise false
		return current.children.size() == 0;
	}

	char ch = word.charAt(index);

	TrieNode node = current.children.get(ch);

	if(node == null){
		return false;
	}

	boolean shouldDeleteCurrentnode = delete(node,word,index+1);
	//if true is returned then delete the mapping of 
	//character and trie Node reference from map
	if(shouldDeleteCurrentnode){
		current.children.remove(ch);

		//return true if no mapping are left in the map
     	return current.children.size() == 0;
	}
	return false;
} 


// Test program
public static void main(String[] args)
{
	Trie word = new Trie();
	System.out.println("Inserting word into trie");
	word.insertRecursive("abc");
	word.insertRecursive("dfg");
	word.insertRecursive("abcd");
	word.insertRecursive("xfghjk");

	boolean s = word.searchRecursive("abc");
	System.out.println("Word present in trie " +s);
	boolean s1 = word.searchRecursive("abcde");
	System.out.println("word present in trie " +s1);

	boolean d = word.delete("abc");
	System.out.println("abc deleted from trie " +d);

	boolean d1 = word.delete("abcd");
	System.out.println("abcd deleted from trie " +d1);
	boolean d2 = word.delete("xfghjk");
	System.out.println("xfghjk deleted from trie "+d2);
}
}