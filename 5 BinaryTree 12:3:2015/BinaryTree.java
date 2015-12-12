/**

*/
public class BinaryTree implements java.lang.Iterable {
    
    private int size;
    private BTnode root;
    private BTnode cursor;


    /* Constructs an empty Binary Tree */
    public BinaryTree(){
        size = 0;
    }

    /* Constructs a Binary Tree with the given Object as the Root */
    public BinaryTree(Object obj) {
        size = 1;
        root = new BTnode(obj);
        cursor = root;
    }

    /* Returns the root of the BinaryTree */
    public BTnode getRoot() {
        return root;
    }

    /** returns true iff the tree contains an object equivalent to obj */
    public boolean contains(Object obj) {
        Treeterator iterator = new Treeterator(root);
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    } 

    /** returns true iff obj is a similar binary tree- i.e., obj must have identical structure (only) */
    public boolean similar(Object obj) {
        if (!(obj instanceof BinaryTree)) {
            return false;
        }
        BinaryTree that = (BinaryTree) obj;
        if (that.size() != this.size()) {
            return false;
        }
        if (this.equals(that)) {
            return false;
        }
        return similarHelper(this.root, that.root);
    }

    // adapted from http://stackoverflow.com/questions/741900/binary-trees-question-checking-for-similar-shape
    private boolean similarHelper(BTnode one, BTnode two) {
        if (one.hasRightSon() && two.hasRightSon() && one.hasLeftSon() && two.hasLeftSon()) {
            return similarHelper(one.rSubTree, two.rSubTree) && 
                similarHelper(one.lSubTree, two.lSubTree);
        } else if (one.hasRightSon() && two.hasRightSon()) {
            return similarHelper(one.rSubTree, two.rSubTree);
        } else if (one.hasLeftSon() && two.hasLeftSon()) {
            return similarHelper(one.lSubTree, two.lSubTree); 
        } else if ((!one.hasRightSon() && !two.hasRightSon()) || 
                (!one.hasLeftSon() && !two.hasLeftSon())) {
            return true;
        } 
        else {
            return false;
        }
    }   

    /** returns true iff obj is an equivalent binary tree- i.e., obj 
        must have identical structure and equivalent objects */
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryTree)) {
            return false;
        }
        BinaryTree that = (BinaryTree) obj;
        if (that.size() != this.size()) {
            return false;
        }
        Treeterator thisIterator = new Treeterator(root);
        Treeterator thatIterator = new Treeterator(that.getRoot());

        while(thisIterator.hasNext() && thatIterator.hasNext()) {
            if (thisIterator.next().getValue() != thatIterator.next().getValue()) {
                return false;
            }
        }
        return true;
    }   
    /** should do the obvious thing- and the same for public int size() and public int hashCode() */
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        Treeterator iterator = new Treeterator(root);
        size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
            // System.out.println("FUCK");
        }
        return size;
    }

    /* Imcomplete hashcode generator */
    public int hashCode() {
        Treeterator iterator = new Treeterator(root);
        int count = 0;
        int extra = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getValue() instanceof String) {
                count += 23;
            } else if (iterator.next().getValue() instanceof Integer) {
                count += 12;
            } else {
                extra += 17;
                count++; 
            }
            iterator.next();
        }
        int hashCount = 1;
        int hashResult = 13;
        return 31 * hashResult + hashCount + size() + (count + extra)/4;
    }

    /** should return a preorder iterator (called "Treeterator") over the tree, 
        whereas public Iterator inOrder() should return an inorder iterator over the tree */
    public Treeterator iterator() {
        return new Treeterator(root);
    }

    // For constructing trees, we will exploit the notion of a cursor that can 
    // be made to refer to different nodes. To support this idea, your class 
    // will need to implement the following instance methods, all of which return a success flag:


    /** returns false if this is an empty tree */
    public boolean putCursorAtRoot() {
        if (size != 0) {
            cursor = root;
            return true;
        } else {
            return false;
        }
    }   

    public boolean putCursorAtLeftSon() {
        if (cursor.hasLeftSon()) {
            cursor = cursor.lSubTree;
            return true;
        } else {
            return false;
        }
    }

    public boolean putCursorAtRightSon() {
        if (cursor.hasRightSon()) {
            cursor = cursor.rSubTree;
            return true;
        } else {
            return false;
        }
    }

    public boolean putCursorAtFather() {
        if (cursor == root) {
            return false;
        } else {
            cursor = cursor.parent;
            return true;
        }
    }

    /** returns false if a left son already exists */
    public boolean attachLeftSonAtCursor(Object obj) {
        if (cursor.hasLeftSon()) {
            return false;
        } else {
            cursor.setLeftSon(new BTnode(obj));
            return true;
        }
    }

    /** returns false if a right son already exists */
    public boolean attachRightSonAtCursor(Object obj) {
        if (cursor.hasRightSon()) {
            return false;
        } else {
            cursor.setRightSon(new BTnode(obj));
            return true;
        }
    }

    /** removes everything that descends from the cursor, including the node 
    to which the cursor refers, then relocates the cursor to the root node, 
    returning true iff something (including the cursor) changed */
    public boolean pruneFromCursor() {
        BinaryTree t = this;
        if (cursor != null) {
            cursor.setValue(null);
        }
        if (this.equals(t)) {
            return false;
        }
        return true;
    }

    public static void printPreOrder(BinaryTree t) {
        Treeterator iterator = t.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + " ");
        }
        System.out.println("");
    }

    public static BinaryTree createTestTree() {
        BinaryTree t = new BinaryTree("a");

        t.attachLeftSonAtCursor("b");
        t.putCursorAtLeftSon();

        t.attachLeftSonAtCursor("d");
        t.putCursorAtLeftSon();

        t.putCursorAtFather();
        t.attachRightSonAtCursor("e");
        t.putCursorAtRightSon();

        t.attachRightSonAtCursor("f");
        t.putCursorAtLeftSon();

        t.putCursorAtRoot();

        t.attachRightSonAtCursor("c");
        t.putCursorAtRightSon();

        t.attachLeftSonAtCursor("g");
        t.putCursorAtLeftSon();

        t.attachRightSonAtCursor("i");
        t.putCursorAtFather();

        t.attachRightSonAtCursor("h");
        t.putCursorAtRightSon();

        t.attachRightSonAtCursor("j");

        return t;
    }


    public static void main(String args[]) {
        BinaryTree test = createTestTree();
        BinaryTree copy = createTestTree();
        printPreOrder(test);
        System.out.println("size(): " + test.size());
        System.out.println(".contains(\"c\"): " + test.contains("c"));
        System.out.println("test.equals(not a binary tree): " + test.equals("not a binary tree"));
        System.out.println("test.equals(copy): " + test.equals(copy));

        copy.cursor.setValue("z");
        printPreOrder(test);
        printPreOrder(copy);
        System.out.println("test.equals(copy): " + test.equals(copy));

        System.out.println("similar() : " + test.similar(copy));

        System.out.println("cursor.getValue(): " + test.cursor.getValue());
        test.pruneFromCursor();
        System.out.print(".pruneFromCursor(): ");
        printPreOrder(test);
        System.out.println("size(): " + test.size());
        System.out.println("test.equals(copy): " + test.equals(copy));

        BinaryTree emptyTest = new BinaryTree();
        printPreOrder(emptyTest);
        System.out.println(".isEmpty(): " + emptyTest.isEmpty());
        System.out.println("size(): " + emptyTest.size());
        System.out.println(".contains(\"c\"): " + emptyTest.contains("c"));
        System.out.println(".hashCode(): " + emptyTest.hashCode());

    }
}