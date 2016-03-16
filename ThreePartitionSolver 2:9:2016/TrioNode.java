import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
TrioNode is a Node Object for ThreePartition Solver

Instance Variables
    contains: The three numbers representing the TrioNode
    remainder: The numbers not within the contains left over when creating the trio
    sum: sum of contains
*/
class TrioNode {
    private List<Integer> contains;
    private RemainderNode remainder;
    private int sum;
    public boolean noMoreSolutions;

    public TrioNode(List<Integer> contains) {
        this.contains = contains;
        sum = contains.get(0) + contains.get(1) + contains.get(2);
        noMoreSolutions = true;
    }

    /**
    Initializes a TrioNode object
    */
    public TrioNode(List<Integer> contains, RemainderNode remainder) {
        this.contains = contains;
        this.remainder = remainder;
        sum = contains.get(0) + contains.get(1) + contains.get(2);
        noMoreSolutions = false;
    }

    /**
    Method to return private instance variable contains
    */
    public List<Integer> contains() {
        return contains;
    }

    /**
    Method to return the private instance variable remainder
    */
    public RemainderNode getRemainder() {
        return remainder;
    }
    /**
    Method to return private instance variable sum
    */
    public int getSum() {
        return sum;
    }

    /**
    Method to convert the TrioNode into a user friendly output
    */
    public String toString() {
        return "(" + contains.get(0) + ", " + contains.get(1) + ", " + contains.get(2) + ")";
    }
}