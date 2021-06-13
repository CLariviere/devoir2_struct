public class Node {

    Node left, right, parent;
    String data;
    int weight;

    public Node(String data)
    {
        this.data = data;
        left = right = parent =null;
        right = null;
        parent = null;
        weight = data.length();
    }

    public Node()
    {
        data = null;
        left = right = parent = null;
        right = null;
        parent = null;
        weight = 0;
    }

    public String nodeToString() {
        if (this.left == null && this.right == null) {
          if (this.data!=null) return this.data;
          else return "";
        } else if (this.left == null) {
          return this.right.nodeToString();
        } else if (this.right == null) {
          return this.left.nodeToString();
        } else {		
          return this.left.nodeToString() + this.right.nodeToString();
        }
    }

    public char charAt(int i){
        if (this.left == null && this.right == null){
            return data.charAt(i);
        }
        if (i > weight) return this.right.charAt(i-weight);
        else return this.left.charAt(i);
    }

    public Node nextRight() 
    {
        if (this.parent == null){ 		// catches root
          return null;
        } else if (this.parent.parent != null && this.parent.parent.right != null){
          return this.parent.parent.right;
        // } else if (this.parent.parent == null && this.parent.left == null){
          // return this.parent.right;
        // } else if (this.parent.parent == null && this.left != null){
          // return this.left;
        } else {
          return null;
        }
    }
}
