//Catherine Lariviere 0955948
//Dominique Vigeant 20129080
//source: https://github.com/harymitchell/JavaRope/blob/master/Rope.java

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

    public Rope(Node noeud){
        root = noeud;
    }

    static void setChild(Node node, boolean toLeft, Node child){
        if (toLeft) {
            node.left = child;
        } else {
            node.right = child;
        }
    }

    //Retourne le caractère à l'index i dans la chaine de caractère de la Rope.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public char charAt(int i) throws IndexOutOfBoundsException{
        return root.charAt(i);/*
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
        return tempNode.data.charAt(i);*/
    }
    //Coupe la Rope en deux à l'index i.
    //La première moitiée devrait contenir les caractères aux index [0, i[
    //et la deuxième devrait contenir les caractères aux index [i, n-1]
    //L'instance sur laquelle est appellée la méthode devient la première moitié,
    //et la valeur de retour de la méthode est une Rope contenant la deuxième moitié.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public Rope split(int i) throws IndexOutOfBoundsException{

        if (i > root.weight) throw new IndexOutOfBoundsException(); //TODO modifier exception

        Rope resultat = new Rope();

        Node temp = root;
        while (temp.left != null || temp.right != null){
            if (i <= temp.weight ){
                //on descend a gauche
                temp=temp.left;
            } else {
                //on descend a droite
                i -= temp.weight;
                temp=temp.right;
            }
        }

        //determiner si on est au milieu d'un node
        if (i != 0 && i != temp.weight){
            Node gauche = new Node();
            Node droite = new Node();
            gauche.parent=droite.parent=temp;
            gauche.data=temp.data.substring(0, i-1);
            gauche.weight=gauche.data.length();
            droite.data=temp.data.substring(i);
            droite.weight=droite.data.length();
            temp.weight=gauche.weight;
            temp=gauche;
        }

        //on construit la nouvelle rope
        if (i == 0 && temp.parent.right == temp){
            Rope orphelin = new Rope(temp);
            temp.parent=null;
            temp.parent.right=null;
            resultat.concat(orphelin);
            temp=temp.parent;
        }
        else if (i == 0 && temp.parent.left == temp){
            Rope orphelin = new Rope(temp.parent);
            resultat.concat(orphelin);
            Node grandParent=temp.parent.parent;
            temp.parent=null;
            temp.parent.left=null;
            temp = grandParent;
        }
        else if (i == temp.weight && temp.parent.right == temp){
            temp = temp.parent;
        }

        while (temp.parent != null){
            if (temp.parent.right == temp) temp=temp.parent;
            else if (temp.parent.right != null){
                Rope orphelin = new Rope(temp.parent.right);
                resultat.concat(orphelin);
                temp = temp.parent;
            } else {
                temp=temp.parent;
            }
        }

        return resultat;
    }

    //Ajoute la rope r à la fin de la rope sur laquelle la méthode est appellée.
    //Devrait être en temps O(1)
    public void concat(Rope r){
        Node newRoot = new Node();
        newRoot.left = root;
        newRoot.right = r.root;
        newRoot.weight = newRoot.right.weight + newRoot.left.weight;
        root.parent = newRoot;
        r.root.parent = newRoot;
        root = newRoot;
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

//         if (i == 0 && root.weight == 0) {
//             root = new Node(s);
//         } else {
//             Rope ropeToInsert = new Rope();
//             ropeToInsert.root = new Node(s);

//             Rope r2 = this.split(i);

//             this.concat(ropeToInsert);

//             this.concat(r2);
//         }
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

        StringBuilder sb = new StringBuilder();
        sb.append(this.root.nodeToString());
        String result = sb.toString();
        return result;
            
    }

    //Retourne la taille de la chaine contenue dans la rope.
    //Devrait être en temps O(1), mais c'est correct si vous faites O(logn) (lorsque l'arbre est balancé)
    public int length(){
        return root.weight; //TODO revalider le poids
    }

    // Fonction maison
    public Boolean exists(Node n) {
        if (n != null) return true;
        else return false;
    }

}