package tree;

import bin.Bin;

public class Node {

    public Node left, right;
    public int height = 1;
    public Bin bin;

    public Node(Bin val) {
        this.bin = val;
    }

}
