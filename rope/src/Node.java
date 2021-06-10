public class Node {

    Node left, right;
    String data;
    int key, weight;

    public Node(String data)
    {
        this.data = data;
        left = null;
        right = null;
        weight = key = data.length();
    }

    public Node()
    {
        data = null;
        left = null;
        right = null;
        weight = key = 0;
    }

         public String nodeToString(){
		if(this.left == null && this.right == null){
			if(this.data!=null)
				return this.data;
			else
				return "";
		} else if(this.left == null){
			return this.right.nodeToString();
		} else if(this.right == null){
			return left.nodeToString();
		} else 	{		
			return this.left.nodeToString() + this.right.nodeToString();
		}
	} 
}
