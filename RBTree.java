package RedBlackTrees;

public class RBTree {
	
	private Node nil = new Node();  
     Node root = nil;
     
     // Method to initialize the Red-Black Tree from a sorted array
	 //rbTreeCreate calls rbInitialize and rbColorize to create a RB-Tree froma a sorted array  
     public void rbTreeCreate(int[][] initial_array, int size){
    	 int level=0;
    	 int height = (int)(Math.log(size)/Math.log(2)); 
    	 this.root=rbInitialize(root,initial_array,0,size-1,height,level);
     }
     
	  // Method to initialize the Red-Black Tree from a sorted array
     public Node rbInitialize(Node root, int[][] initial_array, int low, int high, int height,int level){
    	 
    	 if(low > high){
    		 return nil;
    	 }
 		
		int mid = (low + high) / 2;
    	root = new Node(initial_array[mid]);
 		root.setParent(nil);
 		root.setLeft(nil);
 		root.setRight(nil);
 		
 		if(height == level){
 			root.setColor(1);
 		}

    	root.setLeft(rbInitialize(root, initial_array, low, mid-1, height, level+1));
    	root.getLeft().setParent(root);
    	root.setRight(rbInitialize(root, initial_array, mid+1, high, height, level+1));
		root.getRight().setParent(root);
    		
    	return root;
     }

    
    //Method to insert a node into RB-Tree or increase the count of existing node 
	public Node insert(Node root, Node node){
		if(root == nil){
			root = node;
			System.out.println(root.getCount());
			return root;
		}
		else if(root.getId() == node.getId()){
			root.setCount(root.getCount()+node.getCount());
			System.out.println(root.getCount());
			return root;
		}
		else if(node.getId()<root.getId()){
			root.setLeft(insert(root.getLeft(),node));
			root.getLeft().setParent(root);
		}
		else{
			root.setRight(insert(root.getRight(),node));
			root.getRight().setParent(root);
		}
		return root;
		
	}
	
	//Method to insert a node into RB-Tree or increase the count of existing node
	public void increase(int id, int count){
		Node node = new Node(id,count);
		node.setParent(nil);
		node.setLeft(nil);
		node.setRight(nil);
		this.root=insert(root,node);
		insertFix(node);		
	}
	
	//Method to search for a Node
	public Node searchNode(int id){
        Node node = root;
        while (node != nil){
            if(node.getId() == id)
                return node;
            else if(node.getId() < id)
                node = node.getRight();
            else
                node = node.getLeft();
        }
        return node;
	}
	
	//Method to delete a Node from the RB-Tree
	public void deleteNode(int id){
		Node z = searchNode(id);
		if(z != nil)
			rbDelete(z);
		else
			System.out.println("Node Not Found!!");
	}
	
	//RB-Tree Left Rotate Function
	public void leftRotate(Node x){
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if(y.getLeft() != nil){
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == nil){
			root = y;
		}
		else if( x == x.getParent().getLeft()){
			x.getParent().setLeft(y);
		}
		else{
			x.getParent().setRight(y);
		}
		
		y.setLeft(x);
		x.setParent(y);
	}
	
	//RB-Tree Right Rotate Function
	public void rightRotate(Node x){
		Node y = x.getLeft();
		x.setLeft(y.getRight());
		if(y.getRight() != nil){
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		
		if(x.getParent() == nil){
			root = y;
		}
		else if( x == x.getParent().getRight()){
			x.getParent().setRight(y);
		}
		else{
			x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);
	}
	
	//Method to fix the insert function to ensure RB properties are satisfied
	public void insertFix(Node z){
		
		while(z.getParent().getColor() == 1){
			if(z.getParent() == z.getParent().getParent().getLeft()){
								
				Node y = z.getParent().getParent().getRight();
				if(y.getColor() == 1){
					z.getParent().setColor(0);
					y.setColor   (0);
					z.getParent().getParent().setColor(1);
					z = z.getParent().getParent();
				}
				else{
					if( z == z.getParent().getRight()){
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(0);
					z.getParent().getParent().setColor(1);
					rightRotate(z.getParent().getParent());
				}
			}
			else{
				Node y = z.getParent().getParent().getLeft();
				if(y.getColor() == 1){
					z.getParent().setColor(0);
					y.setColor(0);
					z.getParent().getParent().setColor(1);
					z = z.getParent().getParent();
				}
				else{
					if( z == z.getParent().getLeft()){
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(0);
					z.getParent().getParent().setColor(1);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		
		root.setColor(0);
		
	}
	
	//RB-Tree transplant function
	public void rbTransplant(Node u, Node v){
		if(u.getParent() == nil)
			root = v;
		else if(u == u.getParent().getLeft())
			u.getParent().setLeft(v);
		else
			u.getParent().setRight(v);
		v.setParent(u.getParent());
	}
	
	//Method to fix the delete function to ensure RB properties are satisfied
	public void deleteFix(Node x){
		while(x != root && x.getColor() == 0){
			if(x == x.getParent().getLeft()){
				Node w = x.getParent().getRight();
				if(w.getColor() == 1){
					w.setColor(0);
					x.getParent().setColor(1);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().getColor() == 0 && w.getRight().getColor() == 0){
					w.setColor(1);
					x = x.getParent();
				}
				else{
					if(w.getRight().getColor() == 0){
						w.getLeft().setColor(0);
						w.setColor(1);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(0);
					w.getRight().setColor(0);
					leftRotate(x.getParent());
					x=root;
				}
			}
			else{
				Node w = x.getParent().getLeft();
				if(w.getColor() == 1){
					w.setColor(0);
					x.getParent().setColor(1);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().getColor() == 0 && w.getLeft().getColor() == 0){
					w.setColor(1);
					x = x.getParent();
				}
				else{
					if(w.getLeft().getColor() == 0){
						w.getRight().setColor(0);
						w.setColor(1);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(0);
					w.getLeft().setColor(0);
					rightRotate(x.getParent());
					x=root;
				}
			}
		}
		x.setColor(0);
	}
	
	//Method to find the mimimum event in the tree
	public Node rbMinimum(Node x){
		while(x.getLeft() != nil)
			x = x.getLeft();
		
		return x;
	}
	
	//Method to find the maximum event in the tree
	public Node rbMaximum(Node x){
		while(x.getRight() != nil)
			x = x.getRight();
		
		return x;
	}
	
	//Method to delete the given Node
	public void rbDelete(Node z){
		Node y = z;
		Node x;
		int y_original_color = y.getColor();
		if(z.getLeft() == nil){
			x = z.getRight();
			rbTransplant(z,z.getRight());
		}
		else if(z.getRight() == nil){
			x = z.getLeft();
			rbTransplant(z,z.getLeft());
		}
		else{
			y = rbMinimum(z.getRight());
			y_original_color = y.getColor();
			x = y.getRight();
			if(y.getParent() == z){
				x.setParent(y);
			}
			else{
				rbTransplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			rbTransplant(z,y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
		}
		if(y_original_color == 0){
			deleteFix(x);
		}
	}
	
/*	public void inOrder(Node root){
		if(root==nil){return;}
		inOrder(root.getLeft());
		System.out.print(root.getId() + " "+ root.getColor() + " " + root.getCount() + " " + "->");
		System.out.println(root.getParent().getId());
		inOrder(root.getRight());
	}
	
	public void inTraverse(){
		inOrder(root);
	}
	*/
	/*---------------------------------------------------------------------------------------------*/
	
	//Method to reduce the count of a given event id
	public void reduce(int id, int count){
		//count = -count;
		Node z = searchNode(id);
		if(z == nil){
			System.out.println(z.getCount());
			return;
		}
		z.setCount(z.getCount()-count);
		if(z.getCount()<1){
			rbDelete(z);
			System.out.println(0);
		}
		else{
			System.out.println(z.getCount());
		}
	}
	
	//Method to return the count of given event id
	public void countNode(int id){
		Node z = searchNode(id);
		System.out.println(z.getCount());
	}
	
	//Method to return the next ID and the count of the event with the lowest ID that is greater than the given id.
	public Node searchNext(Node root, Node node){
	        if (node.getRight() != nil) {
	            return rbMinimum(node.getRight());
	        }
	        Node parent = node.getParent();
	        while (parent != null && node == parent.getRight()) {
	            node = parent;
	            parent = parent.getParent();
	        }
	        return parent;
	}
	
	
	//Method to return the ID and the count of the event with the greatest key that is less than the given id.
	public Node searchPrevious(Node root, Node node){
        if (node.getLeft() != nil) {
            return rbMaximum(node.getLeft());
        }
        Node parent = node.getParent();
        while (parent != null && node == parent.getLeft()) {
            node = parent;
            parent = parent.getParent();
        }
        return parent;
}
	
	//Method to find the Node having the given event id
	public Node searchMatch(int id){
        Node node = root;
        while (node != nil){
            if(node.getId() == id)
                return node;
            else if(node.getId() < id){
            	if(node.getRight() == nil)
            		return node;
            	node = node.getRight();
            }
            else{
            	if(node.getLeft() == nil)
            		return node;
            	node = node.getLeft();
            }           
        }
        return node;
	}
	
	//Method to return the ID and the count of the event with the lowest ID that is greater than the given id.
	public void nextNode(int id){
		Node z = searchMatch(id);

		if(z.getId()==id){
			z=searchNext(root,z);
			System.out.println(z.getId()+" "+z.getCount());
		}
		else if(id<z.getId()){
			System.out.println(z.getId()+" "+z.getCount());
		}
		else if(id>z.getId()){
			z=searchNext(root,z);
			System.out.println(z.getId()+" "+z.getCount());
		}

	}
	
	//Methods to return the ID and the count of the event with the greatest key that is less than the given id.
	public void previousNode(int id){
		Node z = searchMatch(id);

		if(z.getId()==id){
			z=searchPrevious(root,z);
			System.out.println(z.getId()+" "+z.getCount());
		}
		else if(id<z.getId()){
			z=searchPrevious(root,z);
			System.out.println(z.getId()+" "+z.getCount());

		}
		else if(id>z.getId()){
			System.out.println(z.getId()+" "+z.getCount());
		}

	}
	
	//Method to find the sum of count of all events in a given range.
	public int inRangeCount(Node root,int id1,int id2){
	   if(root==nil)
	   return 0;
	   else if(root.getId()>=id1&&root.getId()<=id2){
	   return root.getCount()+inRangeCount(root.getLeft(),id1,id2)+inRangeCount(root.getRight(),id1,id2);
	   }
	   else if(root.getId()<id1)
		   return inRangeCount(root.getRight(),id1,id2);
	   else
		   return inRangeCount(root.getLeft(),id1,id2);
	   }
	   
	   //Method to find the sum of count of all events in a given range.
	   public void inRange(int id1, int id2){
		   int rangeCount = inRangeCount(root, id1, id2);
		   System.out.println(rangeCount);
	   }
	

}
