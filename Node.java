
class Node{    
	//The Red Black Tree node class 
	
	//The event id, count and other properties are defined as follows
	private int id;
	private int count;
	private int color;
	private Node  parent;
	private Node left, right;
	
	
		    
    /* Constructor */
	public Node(){
		this.color = 0;
		this.count = 0;	
		this.left = null;
		this.right = null;
	}
	
	/* Constructor */
	public Node(int[] arr){
		this.id=arr[0];
		this.count=arr[1];
		this.color=0;
	}
	
	/* Constructor */
    public Node(int id, int count){
        this.id = id;
        this.count = count;
        this.parent = this;
        this.color = 1;
    }

	//Getter and Setter fucntions for all the properties
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getId() {
		return id;
	}
    
    
    
}