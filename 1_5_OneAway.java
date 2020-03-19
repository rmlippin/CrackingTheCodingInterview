// 1.5 One Away
// There are three types of edits that can be performed on strings:
//  1. Insert a character
//  2. Remove a character
//  3. Replace a character
// Given two strings, write a function to check if they are one edit
// (or zero edits) away.

// Initial thoughts:
// 1. Use length to determine which edit to check for
// 2. #1 and #2 can be checked the same way by just swapping the strings
// 3. Hash table <-- this is bulky
//      abcd   abc       abc    abcd         abcd   abce         abcd    abcd
//      11110  11100     11100  11110        11110  11101        11110   11110
//      diff: 00010      diff: 000(-1)0      diff: 0001(-1)      diff: 00000

// After reading solution:
// It is possible to combine the two methods isOneReplace and isOneInsert
// so it's all one loop. I did this in isOneEditTogether
// Note: this isn't necessarily better, but it'd be useful to point out that
// they can be combined because it allows some code reuse.

class OneAway {

    public static void main(String[] args) {
        printIsOneEdit("pale", "ple");
        printIsOneEdit("pales", "pale");
        printIsOneEdit("pale", "bale");
        printIsOneEdit("pale", "bake");
        printIsOneEdit("", "a");
        printIsOneEdit("same", "same");
        printIsOneEdit("diff", "diffff");
        printIsOneEdit("hello", "hollos");
    }

    private static void printIsOneEdit(String str1, String str2) {
        boolean output = isOneEdit(str1, str2);
        System.out.println(str1 + "|" + str2 + ": " + output);

        output = isOneEditTogether(str1, str2);
        System.out.println(str1 + "|" + str2 + ": " + output);
    }

    private static boolean isOneEdit(String str1, String str2) {
        
        if (str1.length() == str2.length())
            return isOneReplace(str1, str2);
        else if (str1.length() == str2.length() + 1)
            return isOneInsert(str2, str1);
        else if (str2.length() == str1.length() + 1)
            return isOneInsert(str1, str2);
        else
            return false;
    }

    // Checks if str1 and str2 only differ at one char.
    private static boolean isOneReplace(String str1, String str2) {

        // Note: also returns true if strings are the same

        // We know the lengths are the same
        boolean diffFound = false;
        for (int i=0; i<str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (diffFound)
                    return false;
                diffFound = true;
            }
        }

        return true;
    }

    // Checks if only one char was inserted into shorter string to get the
    // longer string.
    private static boolean isOneInsert(String shorter, String longer) {
        
        boolean diffFound = false;
        int indexShort = 0;
        int indexLong = 0;
        
        while (indexShort < shorter.length() && indexLong < longer.length()) {
            if (shorter.charAt(indexShort) != longer.charAt(indexLong)) {

                if (diffFound)
                    return false;
                else {
                    diffFound = true;
                    indexLong++;
                    continue;
                }
            }

            indexLong++;
            indexShort++;
        }

        return true;
    }

    // Checks if str1 and str2 are one or zero edits away
    private static boolean isOneEditTogether(String str1, String str2) {
        
        String shorter, longer;
        if (str1.length() == str2.length() + 1 || str1.length() == str2.length()) {
            longer = str1;
            shorter = str2;
        }
        else if (str1.length() + 1 == str2.length()) {
            longer = str2;
            shorter = str1;
        }
        else 
            return false;

        boolean diffFound = false;
        int indexShort = 0;
        int indexLong = 0;

        while (indexShort < shorter.length() && indexLong < longer.length()) {
            if (shorter.charAt(indexShort) != longer.charAt(indexLong)) {
                if (diffFound)
                    return false;
                else {
                    diffFound = true;

                    indexLong++;

                    // This was added from the isOneInsert loop
                    if (shorter.length() == longer.length())
                        indexShort++;

                    continue;
                }
            }

            indexLong++;
            indexShort++;
        }

        return true;

    }

}