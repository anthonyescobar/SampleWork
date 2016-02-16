import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class TrioNode {
    private List<Integer> contains;
    private RemainderNode remainder;
    private int sum;

	public TrioNode(List<Integer> contains) {
        this.contains = contains;
        sum = contains.get(0) + contains.get(1) + contains.get(2);
    }

    public TrioNode(List<Integer> contains, RemainderNode remainder) {
        this.contains = contains;
        this.remainder = remainder;
        sum = contains.get(0) + contains.get(1) + contains.get(2);
    }

    public List<Integer> contains() {
        return contains;
    }

    public RemainderNode getRemainder() {
        return remainder;
    }

    public String remainderAsString() {
        return remainder.contains().toString();
    }

    public int getSum() {
        return sum;
    }
}