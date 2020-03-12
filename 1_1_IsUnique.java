// 1.1 Is Unique
// Implement an algorithm to determine if a String has all unique characters.
// What if you cannot use additional data structures?

// Initial ideas
// 1. Sort algorithm first, then just linear search.
// 2. Place chars in hash table, stop at collision.

// Notes after looking at solution in the book
// - It'd be important to ask if chars were ASCII or Unicode. ASCII defines 128 chars. 
//      Unicode defines 0^21 chars
// - Use method #1 for Unicode, use method #2 for ASCII

class IsUnique {
    
    public static void main(String[] args) {
        
        printIsAllUniqueChars("dfgkaj");
        printIsAllUniqueChars("ajklerl");
        printIsAllUniqueChars("a");

    }

    // Print solutions and time (in nanoseconds) for each solution
    private static void printIsAllUniqueChars(String str) {
        
        long startTime = System.nanoTime();
        boolean result = isAllUniqueChars1(str);
        long endTime = System.nanoTime();

        System.out.println(str + ":" + result + " (" + 
            (endTime-startTime) + ")");

        startTime = System.nanoTime();
        result = isAllUniqueChars2(str);
        endTime = System.nanoTime();

        System.out.println(str + ":" + result + " (" + 
            (endTime-startTime) + ")\n");
    }


    // Solution 1: Sort algorithm first, then just linear search.
    public static boolean isAllUniqueChars1(String str) {
        
        if (str.length() < 2) 
            return true;

        // Sort the string
        str = StringMergeSort(str);

        // Walk through and look for duplicate chars
        char prev = str.charAt(0);
        for (int i=1; i<str.length(); i++) {
            
            if (prev == str.charAt(i))
                return false;

            prev = str.charAt(i);
        }

        return true;
    }


    // Solution 2: Place chars in hash table, stop at collision.
    // NOTE: Assumes ASCII, 128 possible unique chars
    public static boolean isAllUniqueChars2(String str) {
        
        char[] charArray = new char[128];

        // Iterate through the string and check our hashtable
        for (int i = 0; i < str.length(); i++) {
            
            char c = str.charAt(i);

            // If spot is occupied, this is a duplicate char
            if (charArray[(int)c] != '\u0000')
                return false;

            charArray[(int)c] = c;
        }

        return true;
    }


    public static String StringMergeSort(String str) {

        // Base case
        if (str.length() < 2)
            return str;

        // Find midway point, rounding down
        int q = (str.length()-1)/2;

        String left = StringMergeSort(str.substring(0, q+1)); 
        String right = StringMergeSort(str.substring(q+1, str.length()));

        return mergeSortedStrings(left, right);
    }


    private static String mergeSortedStrings(String left, String right) {

        // Determine longer and shorter Strings
        String longer, shorter;
        if (left.length() >= right.length()) {
            longer = left;
            shorter = right;
        }
        else {
            longer = right;
            shorter = left;
        }

        // Iterate through shorter String, and insert chars into longer String
        // Keep track of index in longer String
        int index = 0;
        for (int i=0; i<shorter.length(); i++) {
           
            char s = shorter.charAt(i);
            boolean isPlaced = false;

            // Iterate through longer String for insertion
            for (int j=index; j<longer.length(); j++) {

                char l = longer.charAt(j);

                // If l is bigger than s, place s in front
                if (l > s) {
                      longer = longer.substring(0, j) + s + longer.substring(j, longer.length());
                      index = j+1;
                      isPlaced = true;
                      break;
                }
            }

            // If String char belongs at the end
            if (!isPlaced) {
                longer = longer + s; 
                index = longer.length()-1;
            }
       }

       return longer;
    }
}