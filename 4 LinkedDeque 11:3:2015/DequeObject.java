/**
Nodes for LinkedDeque which hold data and pointers to left and right nodes.
*/
public class DequeObject {
    
    private DequeObject left;
    private DequeObject right;
    private Object contains;

    /* Creates empty DequeObject */
    public DequeObject() {
        left = null;
        right = null;
        contains = null;
    }

    /* Creates DequeObject containing the Object specified */
    public DequeObject( Object o ) {
        left = null;
        right = null;
        contains = o;
    }

    /* Designates a DequeObject to the left of the current DequeObject */
    public void setLeft( DequeObject d ) {
        left = d;
    }

    /* Designates a DequeObject to the right of the current DequeObject */
    public void setRight( DequeObject d ) {
        right = d;
    }

    /* Returns the object within the DequeObject */
    public Object contains() {
        return contains;
    }

    /* Retruns the DequeObject to the Left of this DequeObject in the Deque */
    public DequeObject getLeft() {
        return left;
    }

    /* Retruns the DequeObject to the Right of this DequeObject in the Deque */
    public DequeObject getRight() {
        return right;
    }
}