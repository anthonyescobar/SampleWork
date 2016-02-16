import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class RemainderNode {
    private List<Integer> contains;
    private List<TrioNode> trioList;

    public RemainderNode() {
    }

    public RemainderNode(List<Integer> contains) {
        this.contains = contains;
        trioList = new ArrayList<TrioNode>();
    }

    public List<TrioNode> getTrioList() {
        return trioList;
    }

    public void printTrioList() {
        for (int i = 0; i < trioList.size(); i++) {
            System.out.println(trioList.get(i).contains());   
        }
    }

    public List<Integer> contains() {
        return contains;
    }

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
            firstNum++;
            secondNum = firstNum+1;
            thirdNum = secondNum+1;
        }
        if (firstNum == contains.size()-2) {
            return;
        }
        this.createCombinations(firstNum, secondNum, thirdNum);
    }

}