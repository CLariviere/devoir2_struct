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

    public Boolean exists(Node n) {
        if (n != null) return true;
        else return false;
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
        Node tempNode = root;

        if (i > root.weight) throw new IndexOutOfBoundsException();

        if (tempNode.weight < i && exists(tempNode.right)) {
            i -= tempNode.weight;
            return charAt(i);
        }

        if (exists(tempNode.left)) {
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

        Node temp=root;
        Rope resultat = new Rope();

        if (i>root.weight) throw new IndexOutOfBoundsException();

        if (temp.weight < i && exists(temp.right)) {
            i -= temp.weight;
            return split(i);
        }

        if (exists(temp.left)) {
            concat(resultat);
            return split(i);
        }
        return resultat;
    }

    //Ajoute la rope r à la fin de la rope sur laquelle la méthode est appellée.
    //Devrait être en temps O(1)
    public void concat(Rope r){
        Node newRoot = new Node();
        newRoot.left = this.root;
        newRoot.right = r.root;
        newRoot.weight=newRoot.right.weight+newRoot.left.weight;
        this.root.parent = newRoot;
        r.root.parent = newRoot;
        root = newRoot;
    }

    //Insere la chaine à l'index i (le premier caractère de s sera à l'index i)
    //Assez facile à implémenter en appellant split et concat!
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void insert(int i, String s) throws IndexOutOfBoundsException {
        if (i > root.weight) throw new IndexOutOfBoundsException();

        if (i == 0 && root.weight == 0) {
            root = new Node(s);
        } else {
            Rope ropeToInsert = new Rope();
            ropeToInsert.root = new Node(s);

            Rope r2 = this.split(i);

            this.concat(ropeToInsert);

            this.concat(r2);
        }
    }

    //Supprime de la rope les caractères de l'intervalle [i, j[
    //Assez facile à implémenter en appellant split et concat!
    //Si i et/ou j ne sont pas des index valides, ou que i > j, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void delete(int i, int j) throws IndexOutOfBoundsException{
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
        return root.weight;
    }

}