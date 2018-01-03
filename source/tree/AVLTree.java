package tree;

import base.Bin;
import base.ConcreteBin;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class AVLTree {

    private Node root;
    private Comparator<Bin> comparator;

    public AVLTree(Comparator<Bin> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        AVLTree rbst = new AVLTree(new ComparatorNotFirstFit());
        rbst.insert(new ConcreteBin(10, 0));
        rbst.insert(new ConcreteBin(9, 1));
        rbst.insert(new ConcreteBin(8, 2));
        rbst.insert(new ConcreteBin(7, 3));
        rbst.insert(new ConcreteBin(6, 4));
        rbst.insert(new ConcreteBin(5, 5));
        rbst.insert(new ConcreteBin(4, 6));
        rbst.insert(new ConcreteBin(2, 7));
        rbst.insert(new ConcreteBin(11, 8));
        rbst.insert(new ConcreteBin(12, 9));
        rbst.insert(new ConcreteBin(12, 10));
        rbst.insert(new ConcreteBin(12, 11));
        rbst.insert(new ConcreteBin(12, 12));
        rbst.insert(new ConcreteBin(12, 13));
        rbst.insert(new ConcreteBin(12, 14));
        rbst.display();
//        rbst.delete(new ConcreteBin(2, 7));
//        rbst.display();
//        rbst.delete(new ConcreteBin(11, 8));
//        rbst.delete(new ConcreteBin(8, 2));
//        rbst.display();
        //System.out.println(rbst.searchBestBin(3));
        for (int i = 0; i <= 13; i++) {
            System.out.println(i);
            System.out.println(rbst.searchFirstBin(i));
        }
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public void insert(Bin value) {
        root = insert(root, value);
    }

    private Node insert(Node node, Bin value) {
        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            return (new Node(value));
        }

        if (this.comparator.compare(value, node.bin) < 0)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        /* 2. Update height of this ancestor node */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && this.comparator.compare(value, node.left.bin) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && this.comparator.compare(value, node.right.bin) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && this.comparator.compare(value, node.left.bin) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && this.comparator.compare(value, node.right.bin) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public void delete(Bin value) {
        root = deleteNode(root, value);
    }

    private Node deleteNode(Node root, Bin value) {
        // STEP 1: PERFORM STANDARD BST DELETE

        if (root == null)
            return root;

        // If the bin to be deleted is smaller than the root's bin,
        // then it lies in left subtree
        if (this.comparator.compare(value, root.bin) < 0)
            root.left = deleteNode(root.left, value);

            // If the bin to be deleted is greater than the root's bin,
            // then it lies in right subtree
        else if (this.comparator.compare(value, root.bin) > 0)
            root.right = deleteNode(root.right, value);

            // if bin is same as root's bin, then This is the node
            // to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {

                Node temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of the non-empty child

                temp = null;
            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.bin = temp.bin;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.bin);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void display() {
        display(root, "", "");
    }

    public Bin searchFirstBin(int size) {
        return searchFirstBin(size, root, null);
    }

    private Bin searchFirstBin(int size, Node node, Node min) {
        if (node == null) {
            if (min != null) {
                return min.bin;
            }
            return null;
        }
        if (!node.bin.fits(size)) {
            return searchFirstBin(size, node.right, min);
        }
        if (node.bin.getCapacityLeft() == size) {
            if (node.left != null && node.left.bin.getCapacityLeft() == size) {
                return searchFirstBin(size, node.left, null);
            } else {
                return node.bin;
            }
        }
        if (min == null || node.bin.getIndex() < min.bin.getIndex()) {
            min = node;
        }
        if (node.left == null) {
            return min.bin;
        }
        return searchFirstBin(size, node.left, min);
    }

    public Bin searchBestBin(int size) {
        return searchBestBin(size, root, null);
    }

    private Bin searchBestBin(int size, Node node, Node best) {
        if (node == null) {
            if (best != null) {
                return best.bin;
            }
            return null;
        }
        if (!node.bin.fits(size)) {
            return searchBestBin(size, node.right, best);
        }
        if (node.bin.getCapacityLeft() == size) {
            if (node.left != null && node.left.bin.getCapacityLeft() == size) {
                return searchBestBin(size, node.left, null);
            } else {
                return node.bin;
            }
        }
        if (best == null || abs(size - node.bin.getCapacityLeft()) < abs(size - best.bin.getCapacityLeft())) {
            best = node;
        }
        if (node.left == null) {
            return best.bin;
        }
        return searchBestBin(size, node.left, best);
    }

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

    private String makeString(char c, int k) {
        String s = "";
        for (int i = 0; i < k; i++) {
            s += c;
        }
        return s;
    }

    ////////////////////////////////////////////////////
    // Convenience methods to build a list of integer from a string
    ////////////////////////////////////////////////////

    private static List<Integer> read(String inputString) {
        List<Integer> list = new LinkedList<Integer>();
        Scanner input = new Scanner(inputString);
        while (input.hasNextInt())
            list.add(input.nextInt());
        input.close();
        return list;
    }

    private void display(Node t, String r, String p) {
        if (t == null) {
            System.out.println(r);
        } else {
            String rs = t.bin.toString();
            rs = "(" + t.height + ")" + rs;
            System.out.println(r + rs);
            if (t.left != null || t.right != null) {
                String rr = p + '|' + makeString('_', rs.length()) + ' ';
                display(t.right, rr, p + '|' + makeString(' ', rs.length() + 1));
                System.out.println(p + '|');
                display(t.left, rr, p + makeString(' ', rs.length() + 2));
            }
        }
    }
}