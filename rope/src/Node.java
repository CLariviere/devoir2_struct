public class Node {

    Node left, right;
    String data;
    int weight;
    /** Constructor **/
    public Node(String data)
    {
        this.data = data;
        left = null;
        right = null;
        weight = data.length();
    }
    /** Constructor **/
    public Node()
    {
        data = null;
        left = null;
        right = null;
        weight = 0;
    }
}
