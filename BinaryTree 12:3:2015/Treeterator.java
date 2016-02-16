/**
Iterator class adapted to BinaryTree. Iterates in preorder.
*/
public class Treeterator implements java.util.Iterator {

    private BTnode root;
    private BTnode pastNode;
    private BTnode nextNode;
    private boolean inOrder;
    private boolean visitedRoot;

    /* Constructs new Treterator from given Binary Tree object */
    public Treeterator(BTnode root) {
        this.root = root;
        nextNode = root;
        inOrder = false;
        visitedRoot = false;
        if (nextNode == null) {
            return;
        }
    }

    /* Checks if Treeterator has a next value */
    public boolean hasNext() {
        return nextNode != null && nextNode.getValue() != null;
    }

    /* Iterates in preorder */
    public BTnode next() {
        pastNode = nextNode;
        if (nextNode.hasLeftSon()) {
            nextNode = nextNode.lSubTree;
        } else if (nextNode.hasRightSon()) {
            nextNode = nextNode.rSubTree;
        } else {
            while (true) {
                // System.out.println("Whiel! next " + nextNode);
                if (nextNode != root && nextNode.parent.rSubTree == nextNode) {
                    nextNode = nextNode.parent;
                } else if (nextNode != root && nextNode.parent.hasRightSon()) {
                    nextNode = nextNode.parent.rSubTree;
                    break;
                } else if (nextNode == root) {
                    nextNode = new BTnode();
                    break;
                } else {
                    nextNode = nextNode.parent;
                }
            }
        }
    return pastNode;
    }

    /* incompatible iterable method */
    public void remove() {
        throw new UnsupportedOperationException();
    } 
}