//Catherine Lariviere 0955948
//Dominique Vigeant 20129080

//Une potentielle bonne source à consulter (avec images!): https://en.wikipedia.org/wiki/Rope_(data_structure)

//Remarque : Certaines méthodes sont beaucoup plus faciles à implémenter récursivement.
//           Si vous voulez les faire comme cela, faites-vous des méthodes privées!
public class Rope {


    //Attributs
    Node root, left, right;

    //Constructeur de Rope, construit une Rope initialement vide.
    public Rope(){
        root = new Node();
    }

    //Retourne le caractère à l'index i dans la chaine de caractère de la Rope.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public char charAt(int i) throws IndexOutOfBoundsException{
        Node tempNode = root;
         
        if (i > root.weight) throw new IndexOutOfBoundsException();

        if (tempNode.weight < i && exists(tempNode.right)) {
            i -= tempNode.weight;
            tempNode = tempNode.right;
            return charAt(i);
        }

        if (exists(tempNode.left)) {
            tempNode = tempNode.left;
            return charAt(i);
        }
        return tempNode.data.charAt(i);
    }

    //Coupe la Rope en deux à l'index i.
    //La première moitiée devrait contenir les caractères aux index [0, i[
    //et la deuxième devrait contenir les caractères aux index [i, n-1]
    //L'instance sur laquelle est appellée la méthode devient la première moitié,
    //et la valeur de retour de la méthode est une Rope contenant la deuxième moitié.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public Rope split(int i) throws IndexOutOfBoundsException{
        if (i > root.weight) throw new IndexOutOfBoundsException();
        
        Node tempNode = this.root;
        Rope secondHalf = new Rope();
        

        while (tempNode.left != null && tempNode.right != null) {
            if (i >= tempNode.weight && tempNode.right != null) {
                i -= tempNode.weight;
                tempNode = tempNode.right;
            } else if (i < tempNode.weight && tempNode.left != null){
                tempNode = tempNode.left;
            }
        }
        
        Node startRope = new Node(tempNode.data.substring(i));
        tempNode.data = tempNode.data.substring(0, i);
        tempNode.weight = i;

        
        secondHalf.root = startRope;

        while (tempNode.nextRight() != null) {
            Node temp = tempNode.nextRight();
            Rope tempRope = new Rope();
            tempRope.root = temp;
            secondHalf.concat(tempRope);
            tempNode = temp.nextRight();
            temp.parent = null;
        }
        
        return secondHalf;
    }    


    //Ajoute la rope r à la fin de la rope sur laquelle la méthode est appellée.
    //Devrait être en temps O(1)
    public void concat(Rope r){
        Node newRootNode = new Node();
        newRootNode.left = root;
        newRootNode.right = r.root;
        newRootNode.weight = newRootNode.right.weight + newRootNode.left.weight;
        root.parent = newRootNode;
        r.root.parent = newRootNode;
        root = newRootNode;
    }

    //Insere la chaine à l'index i (le premier caractère de s sera à l'index i)
    //Assez facile à implémenter en appellant split et concat!
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void insert(int i, String s) throws IndexOutOfBoundsException {
        if (i > root.weight) throw new IndexOutOfBoundsException();

        Rope ropeToInsert = new Rope();
        ropeToInsert.root = new Node(s);

        if (i == 0 && root.weight == 0) {
            root = new Node(s);
        } else if (i == 0) {
            ropeToInsert.concat(this);
            this.root = ropeToInsert.root;
        } else if (i == this.root.weight) {
            this.concat(ropeToInsert);
        } else {
            Rope endOfThisRope = this.split(i);
            this.concat(ropeToInsert);
            this.concat(endOfThisRope);
        }

        //TODO : à compléter
    }

    //Supprime de la rope les caractères de l'intervalle [i, j[
    //Assez facile à implémenter en appellant split et concat!
    //Si i et/ou j ne sont pas des index valides, ou que i > j, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void delete(int i, int j) throws IndexOutOfBoundsException {
        if (i > root.weight || j > root.weight || i > j) throw new IndexOutOfBoundsException();
        Rope secondHalf = this.split(i);
        Rope end = secondHalf.split(j - i);
        this.concat(end);
        //TODO : à compléter
    }

    //Méthode pour rebalancer l'arbre. Ne l'appellez pas avec vos autres méthodes.
    //Nous supposerons qu'elle sera appeller uniquement de l'exterieur.
    //Devrait être en O(n)
    public void rebalance(){
        //TODO : à compléter
    }

    //Efface le contenu de la rope.
    //Devrait être en temps O(1).
    public void clear(){
        root = new Node("");
    }

    //Retourne le chaine de caractère aux index [i, j[
    //Si i et/ou j ne sont pas des index valides, ou que i > j, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(n).
    public String substring(int i, int j) throws IndexOutOfBoundsException{
        //TODO : à compléter
        return null;
    }




    //Retourne l'entièreté de la chaine de caractère contenue dans la Rope.
    //Si la rope est vide, retourner "".
    //Devrait être en temps O(n).
    public String toString(){

        // Starting at the Rode's root, builds a string by recursively traversing Rope.
        StringBuilder sb = new StringBuilder();
        sb.append(this.root.nodeToString());
        String result = sb.toString();
        return result;
            
    }



    //Retourne la taille de la chaine contenue dans la rope.
    //Devrait être en temps O(1), mais c'est correct si vous faites O(logn) (lorsque l'arbre est balancé)
    public int length(){
        return root.weight;
    }

    // Fonction maison
    public Boolean exists(Node n) {
        if (n != null) return true;
        else return false;
    }

}