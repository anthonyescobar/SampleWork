public class Treeterator implements java.util.Iterator {

    private BTnode root;
    private BTnode pastNode;
    private BTnode nextNode;
    private boolean inOrder;
    private boolean visitedRoot;

    public Treeterator(BTnode root) {
        this.root = root;
        nextNode = root;
        inOrder = false;
        visitedRoot = false;
        if (nextNode == null) {
            return;
        }
    }

    public Treeterator(BTnode root, boolean t) {
        this.root = root;
        nextNode = root;
        inOrder = true;
        visitedRoot = false;
        if (nextNode == null) {
            return;
        }
    }

    public boolean hasNext() {
        return nextNode != null && nextNode.getValue() != null;
    }

    public BTnode next() {
        if (inOrder) {
            // move as far left as possible
            // if (nextNode.hasLeftSon()) {
            //     while (nextNode.hasLeftSon()) {
            //         nextNode = nextNode.lSubTree;
            //         pastNode = nextNode;
            //     }
            // } else if (nextNode.hasRightSon()) {
            //     nextNode = nextNode.rSubTree;
            //     while (nextNode.hasLeftSon()) {
            //         nextNode = nextNode.lSubTree;
            //         pastNode = nextNode;
            //     }
            // } else if (nextNode.hasParent() && nextNode.parent.lSubTree == nextNode) {
            //     nextNode = nextNode.parent;
            //     pastNode = nextNode;
            //     System.out.println("\nFUCK");
            // }
            // return pastNode;
            return new BTnode();
        } else {
            pastNode = nextNode;
            // System.out.println("........" + pastNode);
            if (nextNode.hasLeftSon()) {
                nextNode = nextNode.lSubTree;
                // System.out.println("nextNode " + nextNode);
            } else if (nextNode.hasRightSon()) {
                nextNode = nextNode.rSubTree;
                // System.out.println("nextNode " + nextNode);
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
        }
        return pastNode;
    }

    public void remove() {
        //implement... if supported.
        throw new UnsupportedOperationException();
    } 
}