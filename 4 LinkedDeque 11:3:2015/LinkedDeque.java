/**
This program is a double ended queue using nodes (DequeObjects) to hold data.
*/
public class LinkedDeque {

    private int size;
    private DequeObject left;
    private DequeObject right;
    
    /** Creates empty LinkedDeque */
    public LinkedDeque() {
        size = 0;
        left = new DequeObject();
        right = new DequeObject();
    }

    /** Creates new DequeObject and places it on the left side of the LinkedDeque */
    public void insertLeft( Object o ) {
        DequeObject temp = new DequeObject();
        if (o instanceof DequeObject) {
            temp = (DequeObject) o;
        } else {
            temp = new DequeObject(o);
        }
        if (size == 0) {
            temp.setLeft(temp);
            temp.setRight(temp);
            left = temp;
            right = temp;
        } else {
            temp.setRight(left);
            left.setLeft(temp);
            left = temp;
            left.setLeft(right);
            if (size == 1) {
                right.setRight(left);
            }
        }
        size++;
    }

    /** Creates new DequeObject and places it on the right side of the LinkedDeque */
    public void insertRight( Object o ) {
        DequeObject temp = new DequeObject();
        if (o instanceof DequeObject) {
            temp = (DequeObject) o;
        } else {
            temp = new DequeObject(o);
        }
        if (size == 0) {
            temp.setLeft(temp);
            temp.setRight(temp);
            left = temp;
            right = temp;
        } else {
            temp.setLeft(right);
            right.setRight(temp);
            right = temp;
            right.setRight(left);
            if (size == 1) {
                left.setLeft(right);
            }
        }
        size++;
    }

    /** Removes one DequeObject from the left of the LinkedDeque */
    public void deleteLeft () {
        if (size == 0) {
            throw new UnsupportedOperationException();
            // return;
        } else {
            left = left.getRight();
            left.setLeft(right);
            right.setRight(left);
            // if (size == 1) {
            //     left = new DequeObject();
            //     right = new DequeObject();
            // }
        }
        size--;
    }

    /** Removes one DequeObject from the left of the LinkedDeque */
    public void deleteRight () {
        if (size == 0) {
            throw new UnsupportedOperationException();
            // return;
        } else {
            right = right.getLeft();
            right.setRight(left);
            left.setLeft(right);
            // if (size == 1) {
            //     left = new DequeObject();
            //     right = new DequeObject();
            // }
        }
        size--;
    }

    /** returns the left element without modifiying the deque */
    public Object left () {
        if (size == 0) {
            throw new UnsupportedOperationException();
        }
        return left.contains();
    }
    /** returns the right element without modifiying the deque */
    public Object right () {
        if (size == 0) {
            throw new UnsupportedOperationException();
        }
        return right.contains();
    }

    /** Returns the size of the LinkedDeque */
    public int size () {
        return size;
    }

    public String toString () {
        DequeObject current = left;
        String s = "";
        if (size == 0) {
            return s;
        } else {
            do {
                System.out.println("FUCK " + current.contains() + "...");
                s = s + "[" + current.contains() + "]";
                current = current.getRight();
            } while (current != left);
            return s;
        }
    }

    public static void main ( String[] args ) {
        LinkedDeque deque = new LinkedDeque();
        deque.insertLeft("object2");
        System.out.println("\nafter insertLeft(\"object2\"): \nleft():" + deque.left() + " right():" + deque.right());
        System.out.println(deque.toString());

        deque.insertLeft("object1");
        System.out.println("\nafter insertLeft(\"object1\"): \nleft():" + deque.left() + " right():" + deque.right());
        System.out.println(deque.toString());

        deque.insertRight("object3");
        System.out.println("\nafter insertRight(\"object3\"): \nleft():" + deque.left() + " right():" + deque.right());
        System.out.println(deque.toString());

        deque.deleteLeft();
        System.out.println("\nafter deleteLeft(): \nleft():" + deque.left() + " right():" + deque.right());   
        System.out.println(deque.toString());

        deque.deleteRight();
        System.out.println("\nafter deleteRight() \nleft():" + deque.left() + " right():" + deque.right());   
        System.out.println(deque.toString());

        deque.deleteRight();
        // System.out.println("\nafter deleteRight() \nleft():" + deque.left() + " right():" + deque.right()); // calling left() or right() on an empty LinkedDeque throws Exception
        System.out.println(deque.toString());

        // deque.deleteRight(); throws exception because there aren't objects in the LinkedDeque to delete
        System.out.println("\nafter deleteRight() on an empty LinkedDeque: " + deque.toString());


        int x = 100;
        for (int i = 0; i < x; i++) {
            deque.insertRight("object" + i);
        }
        System.out.println("\nAfter adding " + x +  " objects from the right: (3s)\n" + deque.toString());
        
        int y = x-1;
        for (int i = 0; i < x - 1; i++) {
            deque.deleteLeft();
        }
        System.out.println("\nAfter deleting " + y + " objects from the left:\n" + deque.toString());

        deque.insertLeft("");
        deque.insertRight("");
        DequeObject node = new DequeObject("Premade DequeObject");
        deque.insertLeft(node);
        // System.out.println("\n" + deque.left());
    }
}