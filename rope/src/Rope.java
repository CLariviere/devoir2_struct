//Catherine Lariviere 0955948
//Dominique Vigeant 20129080
//source: https://github.com/harymitchell/JavaRope/blob/master/Rope.java
//source: https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/

//Une potentielle bonne source à consulter (avec images!): https://en.wikipedia.org/wiki/Rope_(data_structure)

import java.util.Vector;

//Remarque : Certaines méthodes sont beaucoup plus faciles à implémenter récursivement.
//           Si vous voulez les faire comme cela, faites-vous des méthodes privées!
public class Rope {


    //Attributs
    Node root;

    //Constructeur de Rope, construit une Rope initialement vide.
    public Rope(){
        root = new Node();
    }

    public Rope(Node noeud){
        root = noeud;
        root.parent = null;
    }

    //calcul du poids d'un noeud (poids du sous-arbre de gauche)
    static int getWeight(Node noeud){
        Node temp = noeud;

        if (temp.right == null && temp.left == null) return temp.data.length();
        if (temp.left == null) return 0;

        int poids = 0;

        poids += temp.left.weight;
        temp = temp.left;
        while (temp.right != null) {
            poids += temp.right.weight;
            temp = temp.right;
        }
        return poids;
    }

    //Retourne le caractère à l'index i dans la chaine de caractère de la Rope.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public char charAt(int i) throws IndexOutOfBoundsException{

        if (i > this.toString().length()) throw new IndexOutOfBoundsException();
        return root.charAt(i);
    }

    //Coupe la Rope en deux à l'index i.
    //La première moitiée devrait contenir les caractères aux index [0, i[
    //et la deuxième devrait contenir les caractères aux index [i, n-1]
    //L'instance sur laquelle est appellée la méthode devient la première moitié,
    //et la valeur de retour de la méthode est une Rope contenant la deuxième moitié.
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public Rope split(int i) throws IndexOutOfBoundsException {
        if (i > this.toString().length()) throw new IndexOutOfBoundsException();

        Rope resultat = new Rope();
        Node temp = this.root;

        //cas limite si i est au début ou a la fin de la string
        if (i == 0) {
            resultat.concat(this);
            this.clear();
            return resultat;
        } else if (i == this.length()) {
            resultat.root = new Node("");
            return resultat;
        }

        //on descend jusqu'à une feuille
        while (temp.left != null || temp.right != null) {
            if (i <= temp.weight ) {
                //on descend a gauche
                temp = temp.left;
            } else {
                //on descend a droite
                i -= temp.weight;
                if(temp.right != null) temp = temp.right;
            }
        }

        //determiner si on est au milieu d'un node
        //si oui on le coupe en deux, et le noeud devient
        //le parent des deux nouveaux noeuds
        if (i != 0 && i != temp.data.length()){
            Node gauche = new Node();
            Node droite = new Node();
            gauche.parent = droite.parent = temp;
            gauche.data   = temp.data.substring(0, i);
            gauche.weight = gauche.data.length();
            droite.data   = temp.data.substring(i);
            droite.weight = droite.data.length();
            temp.data=null;
            temp.weight   = gauche.weight;
            temp.right    = droite;
            temp.left     = gauche;
            temp          = gauche;
        }

        //on determine a partir de quel noeud on remonte
        if (i == 0 && temp.parent.right == temp) { //beginning of right node
            temp = temp.parent;
        }else if (i == temp.weight && temp.parent.right == temp) { //end of right node
            temp = temp.parent;
        }

        //on remonte la rope initiale
        //on concat les noeuds a la rope resultat
        while (temp.parent != null) {
            if (temp.parent.left == temp){
                temp = temp.parent;
                if (temp.right != null) resultat.concat(new Rope(temp.right));
                temp.right = null;
                temp.weight = temp.left.weight;

                if (temp.left.data == null && temp.left.right == null){
                    temp.left = temp.left.left;
                    temp.left.parent=temp;
                }
            } else {
                temp = temp.parent;
            }
        }

        this.root.weight = getWeight(this.root);
        return resultat;
    }

    //Ajoute la rope r à la fin de la rope sur laquelle la méthode est appellée.
    //Devrait être en temps O(1)
    public void concat(Rope r) {

        Node newRoot = new Node();
        newRoot.left = this.root;
        newRoot.right = r.root;

        if (this.root.weight == 0) {
            this.root = r.root;
        } else {
            if (newRoot.left.left == null && newRoot.left.right == null) {
                newRoot.weight = newRoot.left.weight;
            } else {
                newRoot.weight = getWeight(newRoot);
            }

            this.root.parent = newRoot;
            r.root.parent = newRoot;
            this.root = newRoot;
        }
    }

    //Insere la chaine à l'index i (le premier caractère de s sera à l'index i)
    //Assez facile à implémenter en appellant split et concat!
    //Si i n'est pas un index valide, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void insert(int i, String s) throws IndexOutOfBoundsException {

        if (i > this.toString().length()) throw new IndexOutOfBoundsException();

        Rope milieu = new Rope();
        milieu.root = new Node(s);

        if (i == 0 && root.weight == 0) {
            this.root = new Node(s);
        } else if (i == 0) {
            milieu.concat(this);
            this.root = milieu.root;
        } else if (i == this.toString().length()) {
            this.concat(milieu);
        } else {
            //on doit insérer au milieu de la string
            Rope droite = this.split(i);
            this.concat(milieu);
            this.concat(droite);
        }
    }

    //Supprime de la rope les caractères de l'intervalle [i, j[
    //Assez facile à implémenter en appellant split et concat!
    //Si i et/ou j ne sont pas des index valides, ou que i > j, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(logn) si l'arbre est balancé
    public void delete(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i > this.length() || j < 0 || i > this.length() || j < i ) throw new IndexOutOfBoundsException();

        if (i != j) {
            if (i == 0){
                Rope milieu = this.split(j);
                this.root = milieu.root;
            }
            else {
                //on supprime au milieu de la string
                Rope milieu = this.split(i);
                Rope end = milieu.split(j - i);
                this.concat(end);
            }
        }
    }

    //Méthode pour rebalancer l'arbre. Ne l'appellez pas avec vos autres méthodes.
    //Nous supposerons qu'elle sera appeller uniquement de l'exterieur.
    //Devrait être en O(n)
    public void rebalance(){

        Vector<Node> nodes = new Vector<>();
        storeNoeud(root, nodes);
        int n = nodes.size();

        //on reconstruit l'arbre noeud par noeud
        this.root=buildTreeUtil(nodes, 0, n - 1);

    }

    //Efface le contenu de la rope.
    //Devrait être en temps O(1).
    public void clear() {
        this.root = new Node("");
    }

    //Retourne le chaine de caractère aux index [i, j[
    //Si i et/ou j ne sont pas des index valides, ou que i > j, lancer un IndexOutOfBoundsException.
    //Devrait être en temps O(n).
    public String substring(int i, int j) throws IndexOutOfBoundsException{

        if (i > j) throw new IndexOutOfBoundsException();

        if (i < 0 || i > this.length() || j < 0 || i > this.length() || j < i ) throw new IndexOutOfBoundsException();

        return this.toString().substring(i,j);
    }

    //Retourne l'entièreté de la chaine de caractère contenue dans la Rope.
    //Si la rope est vide, retourner "".
    //Devrait être en temps O(n).
    public String toString(){

        return this.root.nodeToString();

    }

    //Retourne la taille de la chaine contenue dans la rope.
    //Devrait être en temps O(1), mais c'est correct si vous faites O(logn) (lorsque l'arbre est balancé)
    public int length(){

        Node temp = this.root;
        int poids = temp.weight;
        while (temp.right != null) {
            temp = temp.right;
            poids += temp.weight;
        }
        return poids;

        //return this.toString().length();

    }

    void storeNoeud(Node root, Vector<Node> nodes)
    {
        // cas de base
        if (root == null)
            return;

        // Store les noeuds dans l'ordre
        storeNoeud(root.left, nodes);
        nodes.add(root);
        storeNoeud(root.right, nodes);
    }

    //on construit l'arbre de manière récursive
    Node buildTreeUtil(Vector<Node> nodes, int start,
                       int end)
    {
        // cas de base
        if (start > end)
            return null;

        //le noeud du milieu devient la racine
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);
        if (node.data == null){
            node.weight = 0;
        } else {
            node.weight=node.data.length();
        }

        //on construit les sous-arbres de gauche et de droite
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);

        return node;
    }
}