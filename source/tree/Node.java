package tree;

import base.Bin;

public class Node {

    public Node left, right;
    public int height = 1;
    public Bin bin;

    public Node(Bin val) {
        this.bin = val;
    }

}
