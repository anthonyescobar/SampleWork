public class DequeObject {
    
    private DequeObject left;
    private DequeObject right;
    private Object contains;

    public DequeObject() {
        left = null;
        right = null;
        contains = null;
    }

    public DequeObject( Object o ) {
        left = null;
        right = null;
        contains = o;
    }

    public void setLeft( DequeObject d ) {
        left = d;
    }

    public void setRight( DequeObject d ) {
        right = d;
    }

    public Object contains() {
        return contains;
    }

    public DequeObject getLeft() {
        return left;
    }

    public DequeObject getRight() {
        return right;
    }
}