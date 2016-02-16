import java.util.ArrayList;
/** 
This program converts a given number from one base to another:
    <ul>
        <li>args[0] represents the starting number;</li>
        <li>args[1] represents the starting base;</li>
        <li>args[2] represents the target base.</li>
    </ul>

<b>Conversions to or from unity (base one) are not permitted. [Added 2015-09-19T17:13:00]</b></p>

<p>We require two or three arguments: the starting number will be specified using a special notation (below);
the starting and target bases must both be positive and will be given in decimal. The target base, if absent, defaults to decimal. 
Note that the "digits" in the starting number must be consistent with the starting base. All arithmetic is done using Java type <i>long</i>, whose 
largest value is <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html#MAX_VALUE">java.lang.Long.MAX_VALUE</a>.</p>

<p>In order to represent numbers from all possible bases, we employ a special notation:
a string like [d1][d2]...[dk] represents a k-digit number whose high-order digit has
value d1 (decimal), whose next digit has value d2 (decimal), etc. Here are some examples:
    <ul>
        <li>The number 10110 would be represented [1][0][1][1][0].
        <li>The number 3204124 would be represented [3][2][0][4][1][2][4].
        <li>The base three number that means forty-seven would be represented [1][2][0][2].
        <li>The hexadecimal number for which we ordinarily write B3C would be represented [11][3][12].
    </ul></p>
 
<p>So, to convert from 314 base five to base three, we would run <i>java BaseConverter [3][1][4] 5 3</i>.
To convert BBA326CF from hexadecimal to base twenty, we would run <i>java BaseConverter [11][11][10][3][2][6][12][15] 16 20</i>. 
If we run <i>java BaseConverter [6][14] 26 14</i>, the output should be <i>[12][2]</i>.</p>

<p>The above-described special notation should also be used for outputting the target number.</p>
*/
public class BaseConverter {

    private ArrayList<Long> number;
    private long inBase;
    private long outBase;
    /** 
    If 3 arguments are given the first accounts for the number to be converted, 
    the second is the base the number is represented in and the third is the base the 
    number shall be converted to.
    */
    public BaseConverter(ArrayList<Long> number, long inBase, long outBase) {
        this.number = number;
        this.inBase = inBase;
        this.outBase = outBase;
    }
    /** 
    If only 2 arguments are given, the outBase is considered to be 10.
    */
    public BaseConverter(ArrayList<Long> number, long inBase) {
        this.number = number;
        this.inBase = inBase;
        this.outBase = 10;
    }
    /** 
    Returns true if the dividing number is larger than the dividing number.
    */
    private static boolean tooSmallToDivide(long divisor, long divided) {
        return divisor > divided;
    }
    /** 
    Algorithim to take the values from the BaseConverter class and return an ArrayList<Long>
    of numbers representing the new number. The ArrayList however is given backwards than the
    desired answer.

    ArrayLists are used because the result of the new number is not a guaranteed length, and ArrayLists have
    methods to account for a varying size array.
    */
    public ArrayList<Long> convert() {
        long longZero = 0;
        ArrayList<Long> convertedNumber = new ArrayList<Long>();
        ArrayList<Long> numberTemp = new ArrayList<Long>(1);
        while(number.size() != 0) {
            int i = 0;
            int j = 0;
            numberTemp.clear();
            numberTemp.trimToSize();
            long divided = number.get(i);
            while (i < number.size()) {
                if (tooSmallToDivide(outBase, divided) && i < number.size()) {
                    i++;
                    if (i >= number.size()) {
                        break;
                    }
                    divided = divided * inBase + number.get(i);
                    if (j>0 && tooSmallToDivide(outBase, divided)) {
                        numberTemp.add(null); // I was getting a bug when I used add()
                        numberTemp.set(j, longZero); // caused the Arraylist to rapidly and
                        j++; // infinately add values. To avoid this I added a null then set the value in the List.
                    }
                } else { 
                    numberTemp.add(null);
                    numberTemp.set(j, divided/outBase);
                    j++;
                    divided = divided%outBase;
                }
            }
            convertedNumber.add(divided);
            number.clear();
            number.trimToSize();
            for (int z = 0; z < numberTemp.size(); z++) {
                number.add(numberTemp.get(z));
            }
        }
        return convertedNumber;
    }
    /**
    Used to test if values given can be used as a Long object.
    Adapted code from StackOverflow isInt() Spring Semester 2015.
    */
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
    /** 
    This method attempts to validate the command-line arguments. If they're
        okay, it returns true; otherwise, it returns false. 
    */
    public static boolean validArgs ( String[] args ) {
        // YOUR CODE GOES HERE

        if (args.length == 1 || args.length > 3) {
            return false;
        } else if (!isLong(args[1])) {
            return false;
        } else if (args.length == 3 && !isLong(args[2])) {
            return false;
        } else if (Long.parseLong(args[1]) < 2) {
            return false;
        } else if (args.length == 3 && Long.parseLong(args[2]) < 2) {
            return false;
        } else {
            int firstBound = 0;
            int secondBound = 0;
            while (secondBound < args[0].length()) {
                if (args[0].charAt(firstBound) == '[') {
                    firstBound++;
                    secondBound++;
                    while(args[0].charAt(secondBound) != ']' && secondBound < args[0].length()) {
                        secondBound++;
                    }
                }
                if (!isLong(args[0].substring(firstBound,secondBound))) {
                    return false;
                }
                if (Long.parseLong(args[0].substring(firstBound,secondBound)) >= Long.parseLong(args[1]) || Long.parseLong(args[0].substring(firstBound,secondBound)) < 0){
                    return false;
                }
                secondBound++;
                firstBound = secondBound;
            }
            return true;
        }
    }
    /**
    Converts answer in ArrayList format to the proper notation.
    */
    public static String answerToString(ArrayList<Long> convertedList) {
        String str = "";
        for (int i = convertedList.size()-1; i >= 0; i--) {
            str = str + "[" + convertedList.get(i) + "]";
        }
        return str;
    }    
    /** 
    This method calls validArgs() to check the command-line arguments and, if they're valid, 
        it takes care of the conversion and outputs the result. 
    */   
    public static void main ( String[] args ) {
        if ( ! validArgs ( args ) ) {
            System.out.println("Illegal Argument: args[0] must have integer values within brackets: '[]' " +
                "and those values must be smaller than args[1] which must also be an integer " + 
                "value. 0 or 1 are not a valid integers because they cannot be an appropriate base." + 
                "\nIn the case where no args[2] is given args[2] assumes the value of 10.\n\n");
            throw new IllegalArgumentException();
        }
        else {
            String s = args[0].replaceAll("\\[", "");
            String s2 = s.replaceAll("\\]", ",");

            String[] arrayS = s2.split(",");

            ArrayList<Long> longList = new ArrayList<Long>(0);
            for(int i = 0; i < arrayS.length; i++) {
                longList.add(Long.parseLong(arrayS[i]));
            }
            if (args.length == 2) {
            BaseConverter tester = new BaseConverter(longList, Long.parseLong(args[1]));
            System.out.println(answerToString(tester.convert()));
            } else {
            BaseConverter tester = new BaseConverter(longList, Long.parseLong(args[1]), Long.parseLong(args[2]) );
            System.out.println(answerToString(tester.convert()));
            }
        }
    }

}