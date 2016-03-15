public class MakeOptimalChange {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {
            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.");
                System.out.println();
                printUsage();
                return;
            }

            String[] denominationStrings = args[0].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.");
                    System.out.println();
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.");
                        System.out.println();
                        printUsage();
                        return;
                    }
                }
            }

            Tally change = makeOptimalChange(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            printUsage();
        }
    }

    public static Tally makeOptimalChange(int[] denominations, int amount) {
        Tally[][] mantraContainer = new Tally[denominations.length][amount+1];
        for (int i = 0; i < denominations.length; i++) {
            mantraContainer[i][0] = new Tally(denominations.length);
        }
        for (int i = 0; i < denominations.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if(canMaybeMakeChange(denominations[i], j)) {
                    mantraContainer[i][j] = new Tally(denominations.length);
                    mantraContainer[i][j].setElement(i, 1);
                    mantraContainer[i][j] = addTheseTallies(mantraContainer[i][j], mantraContainer[i][j-denominations[i]]);
                    if (i > 0) {
                        mantraContainer[i][j] = checkforBetter(mantraContainer[i-1][j], mantraContainer[i][j]);
                    }
                } else {
                    mantraContainer[i][j] = Tally.IMPOSSIBLE;
                    if (i > 0) {
                        mantraContainer[i][j] = checkforBetter(mantraContainer[i-1][j], mantraContainer[i][j]);
                    }           
                }
            }
        }
        return mantraContainer[denominations.length-1][amount];
    }

    private static boolean canMaybeMakeChange(int denomination, int change) {
        return denomination <= change;
    }

    private static Tally addTheseTallies(Tally a, Tally b) {
        if(!a.isImpossible() && !b.isImpossible()) {
            return a.add(b);
        }
        return Tally.IMPOSSIBLE;
    }

    private static Tally checkforBetter(Tally above, Tally current) {
        if (above.isImpossible()) {
            return current;
        } else if (current.isImpossible() && !above.isImpossible()) {
            return above;
        } else if (above.total() <= current.total()) {
            return above;
        }
        return current;
    }

    private static void printUsage() {
        System.out.println("Usage: java MakeOptimalChange <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}
