import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


/**
RemainderNode is a node object for ThreePartitionSolver, 
however it is intended to be used with a TrioNode only.

Instance Variables
    contains: Integer List of numbers // numbers remaining after the TrioNode was created
    trioList: TrioNode List to hold trios that can be made with contains
*/
class RemainderNode {
    private List<Integer> contains;
    private List<TrioNode> trioList;
    private int sum = 0;
    public int trioSum = 0;
    public int possibleTrios = 0;

    /**
    Creates a Remainder Node object, pre-calculating the sums
    */
    public RemainderNode(List<Integer> contains) {
        this.contains = contains;
        trioList = new ArrayList<TrioNode>();
        for (int i = 0; i < contains.size(); i++) {
            sum += contains.get(i);
        }
        possibleTrios = contains.size()/3;
        trioSum = sum/possibleTrios;
    }

    /**
    Method to retrieves the TrioList
    */
    public List<TrioNode> getTrioList() {
        return trioList;
    }

    /**
    Method to print the TrioList to the console
    */
    public void printTrioList() {
        for (int i = 0; i < trioList.size(); i++) {
            System.out.println(trioList.get(i).contains());   
        }
    }

    /**
    Method to retrieves the data inside the Remainder Node
    */
    public List<Integer> contains() {
        return contains;
    }

    /**
    Method to create a new Remainder Node, removing three numbers from the original node
    */
    public static RemainderNode createRemainder(List<Integer> oldRem, List<Integer> toRemove) {
        List<Integer> old = new ArrayList<Integer>();
        for (int i = 0; i < oldRem.size(); i++) {
            old.add(oldRem.get(i));
        }
        old.remove(toRemove.get(0));
        old.remove(toRemove.get(1));
        old.remove(toRemove.get(2));
        return new RemainderNode(old);
    }

    /**
    Method to create all possible combinations from the numbers within the Remainder Node.
    Each combination is put into a TrioNode and the TrioNodes are organized within the
    Remainder Node as a List
    */
    public void createCombinations(int firstNum, int secondNum, int thirdNum) {
        List<Integer> temp = new ArrayList<Integer>();
        
        if (contains.size() == 3) {
            temp.add(contains.get(firstNum));
            temp.add(contains.get(secondNum));
            temp.add(contains.get(thirdNum));

            TrioNode tempTrioNode = new TrioNode(temp);

            this.trioList.add(tempTrioNode);
            return;
        }

        temp.add(contains.get(firstNum));
        temp.add(contains.get(secondNum));
        temp.add(contains.get(thirdNum));

        TrioNode tempTrioNode = new TrioNode(temp, createRemainder(contains, temp));

        this.trioList.add(tempTrioNode);

        thirdNum++;

        if (thirdNum == contains.size()) {
            secondNum++;
            thirdNum = secondNum+1;
        }
        if (secondNum == contains.size()-1) {
            // System.out.println("CC RETURN");
            return;
        }
        this.createCombinations(firstNum, secondNum, thirdNum);
    }

}