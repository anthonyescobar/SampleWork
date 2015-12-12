/**
Node Class for BinaryTree
*/
public class BTnode {

    private Object value;
    public BTnode parent;
    public BTnode lSubTree;
    public BTnode rSubTree;

    /* Creates empty node */
    public BTnode() {
        value = null;
    }

    /* Creates node with given object */
    public BTnode(Object o) {
        value = o;
    }

    /* Sets value of node to given object */
    public void setValue(Object o) {
        value = o;
    }

    /* Returns value of node */
    public Object getValue() {
        return value;
    }

    /* Returns if this node has a right son */
    public boolean hasRightSon(){
        return rSubTree != null;
    }

    /* Returns if this node has a left son */
    public boolean hasLeftSon(){
        return lSubTree != null;
    }

    /* Returns if this node has a parent */
    public boolean hasParent() {
        return parent != null;
    }

    /* Sets the left son of this node to the given node */
    public void setLeftSon(BTnode n) {
        lSubTree = n;
        lSubTree.parent = this;
    }

    /* Sets the right son of this node to the given node */
    public void setRightSon(BTnode n) {
        rSubTree = n;
        rSubTree.parent = this;
    }

    /* Typical toString() method */
    public String toString() {
        return value + "";
    }
}