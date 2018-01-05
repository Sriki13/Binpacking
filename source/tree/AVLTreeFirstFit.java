package tree;

import bin.Bin;

import java.util.Arrays;
import java.util.Collections;

/**
 * Un arbre AVL utilisé pour stocker les bins au cours des algorithmes.
 * Adapté à partir de https://gist.github.com/nehaljwani/8243688
 */
@SuppressWarnings("SuspiciousNameCombination")
public class AVLTreeFirstFit {

    protected NodeFirstFit root;

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

    protected NodeFirstFit insert(NodeFirstFit node, Bin value) {
        // Insertion comme dans un BST standard
        if (node == null) {
            return (new NodeFirstFit(value));
        }
        if (value.compareTo(node.bin) < 0)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        // Mise à jour de la hauteur de la node parent
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        // Mise à jour de l'indice min du parent
        node.minIndex = Collections.min(Arrays.asList(minIndex(node.left), minIndex(node.right), node.minIndex));

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

    protected NodeFirstFit deleteNode(NodeFirstFit root, Bin value) {
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
                NodeFirstFit temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;
                root = temp; // Copie du contenu de l'enfant existant
            } else {
                // Node à 2 enfants: trouve le successeur suivant (plus petit à droite)
                NodeFirstFit temp = minValueNode(root.right);
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
        // Mise à jour de l'indice min du parent
        root.minIndex = Collections.min(Arrays.asList(minIndex(root.left), minIndex(root.right), root.minIndex));
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
    protected int height(NodeFirstFit node) {
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
    protected NodeFirstFit rightRotate(NodeFirstFit y) {
        NodeFirstFit x = y.left;
        NodeFirstFit t = x.right;
        // Rotation droite
        x.right = y;
        y.left = t;
        // Mise à jour des hauteurs
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        // Mise à jour des indices mins
        y.minIndex = Collections.min(Arrays.asList(minIndex(y.left), minIndex(y.right), y.minIndex));
        x.minIndex = Collections.min(Arrays.asList(minIndex(x.left), minIndex(x.right), x.minIndex));
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
    protected NodeFirstFit leftRotate(NodeFirstFit x) {
        NodeFirstFit y = x.right;
        NodeFirstFit t = y.left;
        // Rotation gauche
        y.left = x;
        x.right = t;
        // Mise à jour des hauteurs
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        // Mise à jour des indices mins
        y.minIndex = Collections.min(Arrays.asList(minIndex(y.left), minIndex(y.right), y.minIndex));
        x.minIndex = Collections.min(Arrays.asList(minIndex(x.left), minIndex(x.right), x.minIndex));
        // Nouvelle racine
        return y;
    }

    /**
     * Trouve la node de valeur minimale du sous arbre partant de la node donnée.
     *
     * @param node la node racine à partir de laquelle chercher
     * @return la node minimale du sous-arbre partant de la node donnée
     */
    protected NodeFirstFit minValueNode(NodeFirstFit node) {
        NodeFirstFit current = node;
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
    protected int getBalance(NodeFirstFit node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    /**
     * Donne l'indice minimum des bins de l'arbre partant de la node donnée.
     *
     * @param node la node à analyser
     * @return l'indice minimum de bins dans l'arbre de la node, ou Integer.MAX_VALUE si
     * la node est null
     */
    private int minIndex(NodeFirstFit node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        } else return node.minIndex;
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

    private Bin searchFirstBin(int size, NodeFirstFit node) {
        if (node == null) {
            return null;
        }
        if (!node.bin.fits(size)) {
            return searchFirstBin(size, node.right);
        }
        if (minIndex(node.right) <= node.minIndex) {
            return searchFirstBin(size, node.right);
        }
        if (minIndex(node.left) <= node.minIndex) {
            Bin down = searchFirstBin(size, node.left);
            if (down != null) {
                return down;
            }
        }
        return node.bin;
    }

}
