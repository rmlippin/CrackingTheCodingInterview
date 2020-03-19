// 1.3 URLify
// Write a method to replace all spaces in a string with "%20". You may assume
// that the string has sufficient space at the end to hold the additional
// characters, and that you are given the "true" length of the string. 
// Note: For Java, use a character array so that you can perform this
// operation in place.

// Initial ideas
// 1. Simple, linear walkthrough and replace using original array and new array

class URLify {

    public static void main(String[] args) {
        printStringToURL("Mr John Smith");
        printStringToURL("NoSpacesHere");
        printStringToURL("");
        printStringToURL(" ");
        printStringToURL("  Back  To  Back  Spaces  ");
    }

    private static void printStringToURL(String str) {
        String output = stringToURL(str);
        System.out.println(str + "|" + output);
    }

    private static String stringToURL(String str) {
        char[] origArray = str.toCharArray();

        int spaceCount = 0;
        for (int i=0; i<origArray.length; i++) {
            if (origArray[i] == ' ')
                spaceCount++;
        }

        int newLength = origArray.length + spaceCount*2;
        char[] newArray = new char[newLength];

        int counter = 0;
        for (int i=0; i<origArray.length; i++) {
            if (origArray[i] != ' ') 
                newArray[i + counter] = origArray[i];
            else {
                newArray[i + counter] = '%';
                newArray[i + counter + 1] = '2';
                newArray[i + counter + 2] = '0';
                counter += 2;
            }
        }

        return new String(newArray);
    }

}