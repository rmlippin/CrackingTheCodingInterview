// 1.6 String Compression
// Implement a method to perform basic string compression using the counts of
// repeated characters. For example, the string "aabcccccaaa" would become
// "a2b1c5a3". If the compressed string would not become smaller than the
// original string, your method should return the original string. You can
// assume the string has only uppercase and lowercase letters (a-z). 

// Initial ideas
// 1. Linear flow through string, check what chars are the same. 
// 2. Use while loop that'll exit if the output becomes longer than the
// input, and exits when input string end is reached. 
// - When new char is encountered, append it to the string
// - If followed by repeats, skip append until new char or end of string is encountered

// aabcccccaaa:
// start (1)
// a==a  (2)
// a!=b a2 (1)
// b!=c a2b1 (1)
// c==c a2b1 (2)
// c==c a2b1 (3)
// c==c a2b1 (4)
// c==c a2b1 (5)
// c!=a a2b1c5 (1)
// a==a a2b1c5 (2)
// a==a a2b1c5 (3)
// end a2b1c5a3

// After reading solution:
// Use StringBuilder because concatenating strings can get expensive
// Check if final string will be too long BEFORE you compress

class StringCompression {

    public static void main(String[] args) {
        printCompressString("aabcccccaaa");
        printCompressString("aabcc");
        printCompressString("a");
        printCompressString("ab");
        printCompressString("abcde");
        printCompressString("");
    }

    private static void printCompressString(String str) {
        System.out.println(str + "|" + compressString(str));
    }

    private static int getFinalCompressLength(String str) {

        if (str.length() == 0)
            return 0;
        if (str.length() == 1)
            return 2;

        char currChar = str.charAt(0);
        int lengthCount = 2;

        for (int i=1; i<str.length(); i++) {
            if (str.charAt(i) != currChar) {
                lengthCount += 2;
                currChar = str.charAt(i);
            }
        }

        return lengthCount;
    }

    private static String compressString(String str) {

        // Make sure length is at least 2
        if (str.length() < 2)
            return str;
        
        // Check final length before hand O(n)
        if (getFinalCompressLength(str) >= str.length())
            return str;

        int index = 1;
        int currCharCount = 1;
        char currChar = str.charAt(0);
        StringBuilder output = new StringBuilder();

        while (index < str.length()) {

            // If our output string is getting too long, return original string
            if (output.length() >= str.length())
                return str;

            if (str.charAt(index) == currChar) {
                // If char matches, count
                currCharCount++;
            }
            else {
                // Otherwise, concat the previous info and update
                output.append(Character.toString(currChar));
                output.append(currCharCount);
                currChar = str.charAt(index);
                currCharCount = 1;
            }

            index++;
        }

        // Concat last info
        output.append(Character.toString(currChar));
        output.append(currCharCount);

        String outputString = output.toString();

        // Check length again
        if (outputString.length() >= str.length())
            return str;

        return outputString;
    }
}