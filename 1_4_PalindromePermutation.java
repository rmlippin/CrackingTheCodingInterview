// 1.4 Palindrome Permutation
// Given a string, write a function to check if it is a permutation of a
// palindrome. A palindrome is a word or phrase that is the same forwards
// and backwards. A permutation is a rearrangement of letters. The
// palindrome does not need to be limited to just dictionary words.
// Example: "taco cat"

// Initial ideas:
// - Need to remove whitespace
// 1. Hashtable to count instances of letters. 
//      - odd # of letters can have 1 char that occurs odd # of times ("racecar")
//      - even # of letters must have even # of times ("abccba")
//      - Note: assumes ASCII
// - Sorting doesn't really do any good here

class PalindromePermutation {

    public static void main(String[] args) {
        printIsPlaindromePermutation("aabbccccd");
        printIsPlaindromePermutation("racecar");
        printIsPlaindromePermutation("x cf xf c");
        printIsPlaindromePermutation("a     a");
        printIsPlaindromePermutation("notapalindrome");
    }

    private static void printIsPlaindromePermutation(String str) {
        boolean output = isPalindromePermutation(str);
        System.out.println(str + " : " + output);
    }

    private static boolean isPalindromePermutation(String str) {
        
        str = str.replaceAll("\\s", "");

        // Define hash to count char occurrences
        int[] charHash = new int[128];

        // Populate hash which char occurrences
        for (int i=0; i<str.length(); i++) {
            charHash[(int)str.charAt(i)]++;
        }

        boolean oddNumFound = false;
        for (int i=0; i<charHash.length; i++) {
            if (charHash[i] % 2 == 1) {
                // Odd number in char hash

                // If we've already encountered an odd number, this isn't a
                // palindrome
                if (oddNumFound)
                    return false;
                
                // Otherwise, mark that we found an odd number
                oddNumFound = true;     
            } 
        }

        return true;
    }

}