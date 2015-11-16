/**     

    <b>Note: Corrections have been made to the return types for both toArray() methods. (2015-10-13).</b>

    An object of this class represents a number list, i.e., an ordered collection
    of integers, each of Java class <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html">Long</a>, 
    with duplicates permitted. Be sure to read the Java documentation on
    <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html">interface java.util.Collection</a>.

*/

public class NumberList implements java.util.Collection {

    private Long[] longList;
    private int hashCount;
    private int hashResult;

    /** Constructs an empty number list. */
    public NumberList(){
        longList = new Long[0];
        hashCount = 0;
        hashResult = 0;
    }


    /** Constructs a number list from an array of Longs. */
    public NumberList( Long[] l ){
        longList = new Long[l.length];
        for (int i = 0; i < l.length; i++) {
            longList[i] = l[i];
        }
        hashCount = 0;
        hashResult = 0;
    }
    
    
     /** Used to test if values given can be used as a Long object.
    Adapted code from StackOverflow isInt() Spring Semester 2015. */
    public static boolean isLong(String s) {
        try { 
            Long.parseLong(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }


    /** Increases by one the number of instances of the given element in this collection. */
    public boolean add ( Object obj ) {
        if (!isLong(obj.toString())) {
            //throw new IllegalArgumentException("values to be added must be of integer value");
            return false; // doing this will run the program, ignoring botched arguments.           
        } else {
            Long toAdd = Long.parseLong(obj.toString());
            Long[] copyLongList = new Long[longList.length];
            System.arraycopy(longList, 0, copyLongList, 0, longList.length);
            longList = new Long[longList.length+1];
            longList[longList.length-1] = toAdd;
            System.arraycopy(copyLongList, 0, longList, 0, copyLongList.length);
            return true;
        }
    }
    

    /** Adds all of the elements of the given number list to this one. */
    public boolean addAll ( java.util.Collection c  ) {
        Object[] arrayC = c.toArray();
        for (int i = 0; i < arrayC.length; i++) {
            this.add(arrayC[i]);
        }
        return false;
    }
 

    /** Removes all of the elements from this collection. */
    public void clear () {
        // Commented out code will wipe the NumberList but keep the locations as null
        // for (int i = 0; i < longList.length; i++) {
        //     longList[i] = null;
        // }
        longList = new Long[0];
    }
 

    /** Returns true iff this number list contains at least one instance of the specified element. */
    public boolean contains ( Object obj ) {
        if (!isLong(obj.toString())) {
            //throw new IllegalArgumentException("values to be added must be of integer value");
            return false; // doing this will run the program, ignoring botched arguments.           
        } else {
            Long longObj = Long.parseLong(obj.toString());
            for (Long listItem : longList) {
                if (listItem == longObj) {
                    return true;
                }
            }
        }
        return false;
    }
 


    /** Returns true iff this number list contains at least one instance of each element 
        in the specified list. Multiple copies of some element in the argument do not
        require multiple copies in this number list. */
    public boolean containsAll ( java.util.Collection c ) {
        Object[] arrayC = c.toArray();
        for (int i = 0; i < arrayC.length; i++) {
            if (!this.contains(arrayC[i])) {
                return false;
            }     
        }
        return true;
    }


    /** Compares the specified object with this collection for equality. */
    public boolean equals ( Object obj ) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof NumberList)) {
            return false;
        } else {
            NumberList that = (NumberList) obj;
            if (this.sizeIncludingDuplicates() != that.sizeIncludingDuplicates()) {
                return false;
            }
            for (int i = 0; i < this.sizeIncludingDuplicates(); i++) {
                if (this.longList[i] != that.longList[i]) {
                    return false;
                }
            }
        }
        return true;
    }
 



    /** Returns the hashcode value for this collection. */
    public int hashCode () {
        hashCount = 1;
        hashResult = 13;
        for (int i = 0; i < longList.length; i++) {
            hashCount += (int)(longList[i] * i + (i+1));
        }
        return 31 * hashResult + hashCount + longList.length;
    }



    /** Returns true if this collection contains no elements. */
    public boolean isEmpty () {
        return this.size() == 0;
    }



    /** Returns an iterator over the elements in this collection. Replicated elements should
        be "iterated over" just once. */
    public java.util.Iterator iterator () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }



    /** Removes a single instance of the specified element from 
        this collection, if it is present. */
    public boolean remove ( Object obj ) {
        if (!isLong(obj.toString())) {
            //throw new IllegalArgumentException("values to be added must be of integer value");
            return false; // doing this will run the program, ignoring botched arguments.           
        } else {
            for (int i = 0; i < longList.length; i++) {
                if (longList[i] == Long.parseLong(obj.toString())) {
                    Long[] copyLongList = new Long[longList.length];
                    System.arraycopy(longList, 0, copyLongList, 0, longList.length);
                    longList = new Long[longList.length-1];
                    int k = 0;
                    for (int j = 0; j < longList.length; j++) {
                        if (k == i && i != copyLongList.length-1) {
                            k++;
                        }
                        longList[j] = copyLongList[k];
                        k++;
                    }
                    return true;
                }
            }
            return false;
        }
    }



    /** Removes all of this collection's elements that are also contained 
        in the specified collection. */
    public boolean removeAll ( java.util.Collection c ) {
        Object[] arrayC = c.toArray();
        for (int i = 0; i < arrayC.length; i++) {
            while (this.contains(arrayC[i])){
                this.remove(arrayC[i]);
            }
        }
        return false;
    }




    /** Retains only the elements in this collection that are contained in the specified collection. 
         In other words, removes from this collection all of its elements that are not contained in the 
         specified collection. */
    public boolean retainAll ( java.util.Collection c ) {
        NumberList retainedLongList = new NumberList();
        retainedLongList.addAll(c);
        for (int i = 0; i < longList.length; i++) {
            if (!retainedLongList.contains(longList[i])) {
                this.remove(longList[i]);
                i--;
            }
        }
        return true;
    }


    /** Returns the number of elements in this number list, including duplicates. */
    public int sizeIncludingDuplicates () {
        return longList.length;
    }
    
    

    /** Returns a Long[] containing all of the elements in this collection, not including duplicates. */
    public Long[] toArray () {
        NumberList helperList = new NumberList();
        for (int i = 0; i < longList.length; i++) {
            if (!helperList.contains(longList[i])) {
                helperList.add(longList[i]);
            }
        }
        return helperList.longList;
    }



    /** Not supported for this class. */
    public Object[] toArray ( Object[] obj ) {
        throw new UnsupportedOperationException();
    }




    /** Returns the number of elements in this number list, not including duplicates. */
    public int size () {
        int counter = 0;
        NumberList sizeHelperList = new NumberList();
        for (int i = 0; i < longList.length; i++) {
            if (!sizeHelperList.contains(longList[i])) {
                sizeHelperList.add(longList[i]);
                counter++;
            }
        }
        return counter;
    }




    /** Returns the number of instances of the given element in this number list. */
    public int count ( Object obj ) {
        if (!isLong(obj.toString())) {
            //throw new IllegalArgumentException("values to be added must be of integer value");
            return 0; // doing this will run the program, ignoring botched arguments.           
        } else {
            int count = 0;
            Long m = Long.parseLong(obj.toString());
            if (this.contains(obj)) {
                for(Long n : longList) {
                    if(n == m){
                        count++;
                    }
                }
            }
            return count;
        }
    }
    

    
    /** This returns a stringy version of this number list. */
    public String toString () { // overrides Object.toString()
        String str = "";
        for (int i = 0; i<longList.length; i++) {
            str = str + longList[i] + " ";
        }
        return "[ " + str + "]";
    }


    
    /** This so-called "static factory" returns a new number list comprised of the numbers in the specified array.
        Note that the given array is long[], not Long[]. */
    public static NumberList fromArray ( long[] l ) {
        Long[] capitalL = new Long[l.length];
        for (int i = 0; i < l.length; i++) {
            capitalL[i] = Long.parseLong(l[i] + "");
        }
        return new NumberList(capitalL);
    }

    
    /** This main method is just a comprehensive test program for the class. */
    public static void main ( String[] args ) {
        Long[] l = new Long[10];
        Long[] m = new Long[10];
        for (int i = 0; i < l.length; i++) {  
            l[i] = Long.parseLong(i + "");
            m[i] = Long.parseLong(i + "");
        }
        NumberList nums = new NumberList(l);
        NumberList nums2 = new NumberList(m);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nThis is an example of the NumberedList Collection\n:: Anthony Escobar" +
            "\n\n(assume all methods are being applied to the NumberedList \"mod\")");

        System.out.println("\norig: " + nums2.toString() + ".hashCode(): " + nums2.hashCode() + 
            "\nmod : " + nums.toString() + ".hashCode(): " + nums.hashCode() + "\nmod.equals(orig): " + nums.equals(nums2));
        
        System.out.println("\n.toString(): " + nums.toString());
        nums.add("1");
        System.out.println(".add(1): " + nums.toString());
        System.out.println(".contains(4): " + nums.contains(4));
        nums.remove(4);
        System.out.println(".remove(4): " + nums.toString());
        System.out.println(".contains(4): " + nums.contains(4));
        System.out.println(".count(1): " + nums.count(1));
        System.out.println(".toArray(): " + java.util.Arrays.toString(nums.toArray()));
        System.out.println(".isEmpty(): " + nums.isEmpty());
        System.out.println(".size(): " + nums.size());
        System.out.println(".sizeIncludingDuplicates(): " + nums.sizeIncludingDuplicates());

        System.out.println("\norig: " + nums2.toString() + ".hashCode(): " + nums2.hashCode() + 
            "\ntmod : " + nums.toString() + ".hashCode(): " + nums.hashCode() + "\nmod.equals(orig): " + nums.equals(nums2));        

        java.util.ArrayList<Object> c = new java.util.ArrayList<Object>();
        c.add(1);
        c.add(7);
        c.add(8);
        nums.retainAll(c);
        System.out.println("\n.retainAll(1,7,8): " + nums.toString());
        c.remove(2);
        nums.removeAll(c);
        System.out.println(".removeAll(1,7): " + nums.toString());

        System.out.println(".remove(4): " + nums.toString());
        nums.add(1);
        System.out.println(".add(1): " + nums.toString());
        System.out.println(".size(): " + nums.size());
        System.out.println(".hashCode(): " + nums.hashCode());


        nums.clear();
        System.out.println("\n.clear(): " + nums.toString());
        System.out.println(".hashCode(): " + nums.hashCode()); // YES! I know it gives 404. That was on purpose. (404 no data)
        System.out.println(".isEmpty(): " + nums.isEmpty());
        System.out.println(nums.toString() + " size: " + nums.size());

    }
}