import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
A function to check if a list of 3n size of numbers can be partitioned into a list of n triples 
such that the sum of each triple was the same

ex.
given: [2,4,8,12,15,2,0,6,3,2,9,1] as 2 4 8 12 15 2 0 6 3 2 9 1

the answer is true because you can partition this list as follows:
[(4,3,9),(15,0,1),(8,2,6),(2,2,12)]

while the answer would be false for:
[6, -1, 8, 3455, 11, 7]

The answer is also false if the length of the input list is not a multiple of 3
*/
class ThreePartitionSolver {
    
    /** 
    Begins with quick checks for size of the list and numbers inside before running the algorithm
    */
    public static boolean threePartition(List<Integer> numbers) {
        if (numbers.size() == 3 || numbers.size() == 0) {
            return true;
        }
        if (numbers.size() % 3 == 0 && numbers.size() != 0) {
            Collections.sort(numbers, Collections.reverseOrder());
            if (numbers.get(0) == numbers.get(numbers.size()-1)) {
                return true;
            }
            if (isImpossible(numbers)) {
                System.out.println("it's impossible!");
                return false;
            }

            RemainderNode testNode = new RemainderNode(numbers);
            List<String> printList = new ArrayList<String>();

            return tailAlgorithm(testNode, 0, 1, testNode.possibleTrios, testNode.trioSum, printList);
        }
        return false;
    }

    /** 
    Algorithm has a trio and pairs it with it's remainder then runs the algorithms by
    creating the different trio-combinations of the remainder and comparing the sum to the trio's sum

    If the sums are equal the algorithm is called recursively with the trio created by the remainder
    */
    public static boolean tailAlgorithm(RemainderNode remainder, int index, int level, int possibleTrios, int goal, List<String> printList) {
        remainder.createCombinations(0,1,2);
        while(index < remainder.getTrioList().size()) {
            TrioNode currentTrio = remainder.getTrioList().get(index);
            if (currentTrio.getSum() == goal) {
                printList.add(currentTrio.toString());
                level++;
                if (level == possibleTrios) {
                printList.add(currentTrio.toString() + " Sum: " + currentTrio.getSum());
                System.out.println(printList);
                    return true;
                }
                else if (!currentTrio.noMoreSolutions){
                    return tailAlgorithm(currentTrio.getRemainder(), 0, level, possibleTrios, goal, printList);
                }
            }
            index++;
        }
        return false;
    }

    /**
    Method to check the sum between two trios
    */
    public static boolean checkSum(TrioNode og, TrioNode check) {
        return og.getSum() == check.getSum();
    }
    
    /**
    Method to check if finding a solution would be impossible because the largest number is too large
    */
    public static boolean isImpossible(List<Integer> numbers) {
        return numbers.get(0) + numbers.get(numbers.size()-1 ) + numbers.get(numbers.size()-1) > 
            numbers.get(1) + numbers.get(2) + numbers.get(3);
    }

    /**
    Method to take the user inputted args and create an Integer List to be compatible with threePartition()
    */
    public static List<Integer> toIntegerList(String[] args) {
        List<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < args.length; i++) {
            int temp = Integer.parseInt(args[i]);
            newList.add(temp);
        }
        return newList;
    }
    /**
    Method Main that outputs only the result of the algorithm
    */
    public static void main(String[] args) {
        System.out.println(threePartition(toIntegerList(args)));
    }
}