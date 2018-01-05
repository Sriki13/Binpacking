package tree;

import bin.Bin;

import java.util.Arrays;
import java.util.List;

/**
 * Un arbre AVL utilisé pour stocker les bins au cours des algorithmes.
 * Adapté à partir de https://gist.github.com/nehaljwani/8243688
 */
@SuppressWarnings("SuspiciousNameCombination")
public class AVLTree {

    private Node root;

    // -----------------------------------------------------------------------------------------
    //                                    INSERTION
    // -----------------------------------------------------------------------------------------

    /**
     * Insère une bin dans l'arbre.
     *
     * @param value la bin à insérer
     */
    public void insert(Bin value) {
        root = insert(root, value);
    }

    private Node insert(Node node, Bin value) {
        // Insertion comme dans un BST standard
        if (node == null) {
            return (new Node(value));
        }
        if (value.compareTo(node.bin) < 0)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        // Mise à jour de la hauteur de la node parent
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Calcul du facteur d'équilibrage de la node pour savoir si il faut rééquilibrer
        int balance = getBalance(node);

        // 4 cas de déséquilibre
        // Cas gauche gauche
        if (balance > 1 && value.compareTo(node.left.bin) < 0)
            return rightRotate(node);
        // Cas droite droite
        if (balance < -1 && value.compareTo(node.right.bin) > 0)
            return leftRotate(node);
        // Cas gauche droite
        if (balance > 1 && value.compareTo(node.left.bin) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Cas droite gauche
        if (balance < -1 && value.compareTo(node.right.bin) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // -----------------------------------------------------------------------------------------
    //                                    SUPPRESSION
    // -----------------------------------------------------------------------------------------

    /**
     * Supprime une bin de l'abre.
     *
     * @param value la bin à supprimer
     */
    public void delete(Bin value) {
        root = deleteNode(root, value);
    }

    private Node deleteNode(Node root, Bin value) {
        // Supression comme dans un BST standard
        if (root == null) {
            return null;
        }
        if (value.compareTo(root.bin) < 0)
            root.left = deleteNode(root.left, value);
        else if (value.compareTo(root.bin) > 0)
            root.right = deleteNode(root.right, value);
        else {
            if (root.left == null && root.right == null) {
                // Pas d'enfants
                root = null;
            } else if (root.left == null || root.right == null) {
                Node temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;
                root = temp; // Copie du contenu de l'enfant existant
            } else {
                // Node à 2 enfants: trouve le successeur suivant (plus petit à droite)
                Node temp = minValueNode(root.right);
                // Copie des données du successeur dans la node courante
                root.bin = temp.bin;
                // Suppression du successeur
                root.right = deleteNode(root.right, temp.bin);
            }
        }

        // Retour si l'arbre n'avait qu'une seule node
        if (root == null)
            return null;

        // Mise à jour de la hauteur de la node courante
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        // Calcul du facteur d'équilibrage de la node pour savoir si il faut rééquilibrer
        int balance = getBalance(root);
        // 4 cas de déséquilibre
        // Cas gauche gauche
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        // Cas gauche droite
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // Cas droite droite
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        // Cas droite gauche
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    // -----------------------------------------------------------------------------------------
    //                                    UTILITAIRES
    // -----------------------------------------------------------------------------------------

    /**
     * Détermine la hauteur d'une node de l'arbre.
     *
     * @param node la node à analyser
     * @return sa hauteur
     */
    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Rotation droite :
    //            y                            x
    //           / \          ----->         /   \
    //         x    y.right            x.left     y
    //        / \                                / \
    //  x.left   t                              t   y.right

    /**
     * Effectue une rotation droite avec pour racine la node donnée.
     *
     * @param y la node racine orginale
     * @return la racine après rotation
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t = x.right;
        // Rotation droite
        x.right = y;
        y.left = t;
        // Mise à jour des hauteurs
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        // Nouvelle racine
        return x;
    }

    // Rotation gauche :
    //         x                                y
    //        / \           ----->            /   \
    //  x.left   y                          x    y.right
    //          / \                        / \
    //         t   y.right           x.left   t

    /**
     * Effectue une rotation gauche avec pour racine la node donnée.
     *
     * @param x la node racine orginale
     * @return la racine après rotation
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node t = y.left;
        // Rotation gauche
        y.left = x;
        x.right = t;
        // Mise à jour des hauteurs
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        // Nouvelle racine
        return y;
    }

    /**
     * Trouve la node de valeur minimale du sous arbre partant de la node donnée.
     *
     * @param node la node racine à partir de laquelle chercher
     * @return la node minimale du sous-arbre partant de la node donnée
     */
    private Node minValueNode(Node node) {
        Node current = node;
        // Parcours pour trouver la feuille la plus à gauche
        while (current.left != null)
            current = current.left;
        return current;
    }

    /**
     * Donne le facteur d'équilibrage d'une node, correspondant à la différence
     * de hauteur entre le sous arbre gauche et le droit.
     *
     * @param node la node au facteur à déterminer
     * @return le facteur d'équilibrage de cette node
     */
    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // -----------------------------------------------------------------------------------------
    //                                    FIRST BIN
    // -----------------------------------------------------------------------------------------

    /**
     * Recherche la bin d'indice le plus bas pouvant accepter l'objet demandé.
     * Renvoie null si aucune ne convient.
     *
     * @param size la taille de l'objet à insérer
     */
    public Bin searchFirstBin(int size) {
        return searchFirstBin(size, root);
    }

    private Bin searchFirstBin(int size, Node node) {
        if (node == null) {
            return null;
        }
        if (!node.bin.fits(size)) {
            return searchFirstBin(size, node.right);
        }
        return lowestIndex(Arrays.asList(node.bin, searchFirstBin(size,node.left), searchFirstBin(size,node.right)));
    }

    private Bin lowestIndex(List<Bin> list) {
        Bin min = null;
        for (Bin bin: list) {
            if (min == null || (bin != null && bin.getIndex() < min.getIndex()) ) {
                min = bin;
            }
        }
        return min;
    }

    // -----------------------------------------------------------------------------------------
    //                                    BEST BIN
    // -----------------------------------------------------------------------------------------

    /**
     * Recherche la bin la plus remplie pouvant accepter l'objet demandé.
     * Renvoie null si aucune ne convient.
     *
     * @param size la taille de l'objet à insérer
     */
    public Bin searchBestBin(int size) {
        return searchBestBin(size, root);
    }

    private Bin searchBestBin(int size, Node node) {
        if (node == null) {
            return null;
        }
        if (!node.bin.fits(size)) {
            return searchBestBin(size, node.right);
        }
        Bin down = searchBestBin(size, node.left);
        if (down == null) {
            return node.bin;
        } else {
            return down;
        }
    }

    // -----------------------------------------------------------------------------------------
    //                                    WORST BIN
    // -----------------------------------------------------------------------------------------

    /**
     * Recherche la bin la moins remplie pouvant accepter l'objet demandé.
     * Renvoie null si aucune ne convient.
     *
     * @param size la taille de l'objet à insérer
     */
    public Bin searchWorstBin(int size) {
        return searchWorstBin(size, root);
    }

    private Bin searchWorstBin(int size, Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null && node.bin.fits(size)) {
            return node.bin;
        }
        return searchWorstBin(size, node.right);
    }

    // -----------------------------------------------------------------------------------------
    //                                    ALMOST WORST BIN
    // -----------------------------------------------------------------------------------------

    /**
     * Recherche la deuxième bin la moins remplie pouvant accepter l'objet demandé.
     * Si une seule bin convient, elle est renvoyée à la place.
     * Renvoie null si aucune ne convient.
     *
     * @param size la taille de l'objet à insérer
     */
    public Bin searchAlmostWorstBin(int size) {
        return searchAlmostWorstBin(size, root, null);
    }

    private Bin searchAlmostWorstBin(int size, Node node, Node second) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            if (second != null) {
                return second.bin;
            }
            if (node.bin.fits(size)) {
                return node.bin;
            }
            return null;
        }
        if (node.bin.fits(size)) {
            second = node;
        }
        return searchAlmostWorstBin(size, node.right, second);
    }

}
