public class BTnode {

    private Object value;
    public BTnode parent;
    public BTnode lSubTree;
    public BTnode rSubTree;
    // public boolean cursor;
    // private BTNode father;

    public BTnode() {
        value = null;
    }

    public BTnode(Object o) {
        value = o;
    }

    public void setValue(Object o) {
        value = o;
    }

    public Object getValue() {
        return value;
    }

    public boolean hasRightSon(){
        return rSubTree != null;
    }

    public boolean hasLeftSon(){
        return lSubTree != null;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public void setLeftSon(BTnode n) {
        lSubTree = n;
        lSubTree.parent = this;
    }

    public void setRightSon(BTnode n) {
        rSubTree = n;
        rSubTree.parent = this;
    }

    public String toString() {
        return value + "";
    }
}