import java.util.ArrayList;
import java.util.Collections; // For sorting ArrayLists

class Solution {
    public static String caseSort(String s) {
        // Create ArrayLists to store uppercase and lowercase characters separately
        ArrayList<Character> uppercaseChars = new ArrayList<>();
        ArrayList<Character> lowercaseChars = new ArrayList<>();

        // 1. Separate characters by their case
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseChars.add(c);
            } else {
                lowercaseChars.add(c);
            }
        }

        // 2. Sort the separated character lists
        Collections.sort(uppercaseChars);
        Collections.sort(lowercaseChars);

        // Use a StringBuilder for efficient string construction
        StringBuilder result = new StringBuilder();

        // Maintain pointers for the sorted lists
        int upperPtr = 0;
        int lowerPtr = 0;

        // 3. Reconstruct the string based on the original case positions
        for (char originalChar : s.toCharArray()) {
            if (Character.isUpperCase(originalChar)) {
                // If original was uppercase, pick from sorted uppercase list
                result.append(uppercaseChars.get(upperPtr));
                upperPtr++;
            } else {
                // If original was lowercase, pick from sorted lowercase list
                result.append(lowercaseChars.get(lowerPtr));
                lowerPtr++;
            }
        }

        return result.toString();
    }
}