/*
  	Class: CE3345
 	Section: 003
  	Semester: Fall 2019
  	Name: Delia V. Trejo
  	Description: This class will be called from the Main class. This class has the instructions
  				on how to insert, logically delete, find the smallest and largest, and if the tree
  				contains a value. This class also determines the height and size of the tree.
  				Each value must be in the range 1 to 99, therefore there is an in range method that
  				will determine if a value passed in is between 1 and 99.
  				Finally, there is a TreeNode class that will keep the data for each node. This class
  				will store the value, left and right child, and the logical deletion of the nodes used
  				in this program.
 */
public class LazyBinarySearchTree {
	private TreeNode root;
	static int size;
	
	public LazyBinarySearchTree() {
		this.root = null;
	}
	
	//This will insert a new node into the tree in the appropriate place
	public boolean insert(int key) throws IllegalArgumentException{
		if(inRange(key) == false)
			throw new IllegalArgumentException("IllegalArgumentException raised");
		else if(inRange(key) == true) {
			if(this.root == null)
				this.root = new TreeNode(key);
			else
				insert(key, root);
			
			size++;
			return true;
		}
		return false;
	}
	public TreeNode insert(int key, TreeNode parent) {
		if(parent == null) 
			return new TreeNode(key);
		else if((key == parent.key) && (parent.deleted))
			parent.deleted = false;
		else if(key < parent.key)
			parent.leftChild = insert(key, parent.leftChild);
		else if (key > parent.key)
			parent.rightChild = insert(key, parent.rightChild);

		return parent;
	}
	
	//Will find the value in the tree and mark it as deleted
	public boolean delete(int key) throws IllegalArgumentException{
		if(root == null)
			return false;
				
		return delete(key,root);
	}
	public boolean delete(int key, TreeNode temp) {
		if(inRange(key) == false)
			throw new IllegalArgumentException("IllegalArgumentException raised");
		if(this.root == null)
			return false;
		
		while(temp != null) {
			if (temp.key == key) {
				if(temp.deleted == false) {
					temp.deleted = true;
					return true;
				}
				else
					return false;
			}
			
			else if(key > temp.key) 
				temp = temp.rightChild;
			else if(key < temp.key) 
				temp = temp.leftChild;
		}
		return false;
	}
	
	//Find the smallest NON-deleted value in the tree
	public int findMin() {
		if(root == null)
			return -1;
		
		TreeNode temp = root;
		int min = temp.key;
		while(temp.leftChild != null) {
			if(temp.deleted == false) 
				min = temp.key;
			temp = temp.leftChild;
		}
			
		return min;
	}
	
	//This will find the largest NON-deleted value in the tree
	public int findMax() {
		if(root == null)
			return -1;
		
		TreeNode temp = root;
		int max = temp.key;
		while(temp.rightChild != null) {
			if(temp.deleted == false) 
				max = temp.key;
				
			temp = temp.rightChild;
		}
		return max;
	}
	
	//Check to see if a value is in the tree AND is not marked as deleted
	public boolean contains(int key) {
		if(inRange(key) == false) 
			throw new IllegalArgumentException("IllegalArgumentException raised");
		
		if(root == null)
			return false;
		
		TreeNode temp = root;
		
		while(temp != null) {
			if(key == temp.key) {
				if(temp.deleted == false) 
					return true;
				else
					return false;
			}
			else if(key < temp.key)
				temp = temp.leftChild;
			else if(key > temp.key)
				temp = temp.rightChild;
		}
		return false;
	}
	
	//Print the tree out in pre-order transversal form
	public String printingTree(TreeNode root) {
		if(root == null)
			return "";
		String side = "", partL = "", partR = "";
		
		  if(root.deleted == true)
			  side = side + "*" + root.key + " ";
		  else
			  side = side + root.key+" ";

		  partL = printingTree(root.leftChild); 
		  partR = printingTree(root.rightChild);
		  return (side + partL + partR);
	}

	public String toString(){
		return printingTree(root);
	}
	
	//Will calculate the height of the binary tree
	public int height() {
		return Math.max(height(root.leftChild), height(root.leftChild));
	}
	public int height (TreeNode root) {
		if(root == null)
			return 0;
		
		return Math.max(height(root.leftChild), height(root.rightChild)) + 1;
	}
	
	//Return the size of the tree
	public int size() {
		return size;
	}
	
	//Will check if the value is in range of [1,99]
	public boolean inRange(int key) {
		if(key < 1)
			return false;
		if(key > 99)
			return false;
		
		return true;
	}

	//Private class for TreeNode
	private static class TreeNode {
		boolean deleted;
		int key;
		TreeNode leftChild, rightChild;
		
		public TreeNode(int key) {
			this.key = key;
			this.leftChild = this.rightChild = null;
			this.deleted = false;
		}
	}
	
}
