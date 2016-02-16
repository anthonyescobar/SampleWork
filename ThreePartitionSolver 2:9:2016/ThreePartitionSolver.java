import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class ThreePartitionSolver {

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

            testNode.createCombinations(0,1,2);

            for (int i = 0; i < testNode.getTrioList().size(); i++) {
                algorithm(testNode.getTrioList().get(i), testNode.getTrioList().get(i).getRemainder());
                if (algorithm(testNode.getTrioList().get(i), testNode.getTrioList().get(i).getRemainder())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean algorithm(TrioNode trio, RemainderNode remainder) {
        remainder.createCombinations(0,1,2);
        for (int i = 0; i < remainder.getTrioList().size(); i++) {
            if (checkSum(trio, remainder.getTrioList().get(i))) {
                if (remainder.getTrioList().get(i).getRemainder() == null) {
                    return true;
                }
                TrioNode temp = new TrioNode(remainder.getTrioList().get(i).contains(), 
                    remainder.getTrioList().get(i).getRemainder());
                algorithm(temp, temp.getRemainder());
                return true;
            }
        }
        return false;
    }

    public static boolean checkSum(TrioNode og, TrioNode check) {
        return og.getSum() == check.getSum();
    }
    
    public static boolean isImpossible(List<Integer> numbers) {
        return numbers.get(0) + numbers.get(numbers.size()-1 ) + numbers.get(numbers.size()-1) > 
            numbers.get(1) + numbers.get(2) + numbers.get(3);
    }

    public static List<Integer> toIntegerList(String[] args) {
        List<Integer> newList = new ArrayList<Integer>();
        for (int i = 0; i < args.length; i++) {
            int temp = Integer.parseInt(args[i]);
            newList.add(temp);
        }
        return newList;
    }

    public static void main(String[] args) {
        System.out.println(threePartition(toIntegerList(args)));
    }
}