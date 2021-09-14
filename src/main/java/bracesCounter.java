import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bracesCounter {

    public static final int INVALID = -1;

    public enum BracketType {
        NORMAL,
        SQUARE,
        CURLY
    }

    public static final List<String> OPENINGBRACKETSLIST = new ArrayList<>(Arrays.asList("(","[","{"));
    public static final List<String> CLOSINGBRACKETSLIST = new ArrayList<>(Arrays.asList(")","]","}"));

    public static int count(String braces) {
        if(isUnevenNumberOfBraces(braces)) {
            return INVALID;
        }

        int count = 0;
        int stringLowerIndex = 0;
        while (stringLowerIndex < braces.length()) {
            int openingCharacterType = getOpeningBraceType(braces.charAt(stringLowerIndex));
            if (openingCharacterType != INVALID) {
                String matchingOpeningCharacter = OPENINGBRACKETSLIST.get(openingCharacterType);
                String matchingClosingCharacter = CLOSINGBRACKETSLIST.get(openingCharacterType);
                int stringUpperIndex = findCorrespondingClosingBracket(braces,
                        matchingOpeningCharacter,
                        matchingClosingCharacter,
                        stringLowerIndex + 1);
                if (stringUpperIndex != INVALID) {
                    // is Upper Index the next index after the Lower Index
                    if (stringUpperIndex == stringLowerIndex + 1) {
                        count += 1;
                     } else {
                        count += 1 + count(braces.substring(stringLowerIndex + 1, stringUpperIndex));
                    }
                    stringLowerIndex = stringUpperIndex + 1;
                } else {
                    return INVALID;
                }
            } else {
                return INVALID;
            }
        }
        return  count;
    }

    public static int findCorrespondingClosingBracket(String braces,
                                                      String matchingOpeningCharacter,
                                                      String matchingClosingCharacter,
                                                      int index) {
        int numberOfMatchingCharactersOfSameOpeningType = 0;
        while(index < braces.length()) {
            if(braces.charAt(index) == matchingOpeningCharacter.charAt(0)) {
                numberOfMatchingCharactersOfSameOpeningType++;
            }
            if(braces.charAt(index) == matchingClosingCharacter.charAt(0)) {
                if(numberOfMatchingCharactersOfSameOpeningType == 0) {
                    return index;
                } else {
                    numberOfMatchingCharactersOfSameOpeningType --;
                }
            }
            index++;
        }
        return INVALID;
    }

    public static boolean isUnevenNumberOfBraces(String braces) {
        return braces.length() % 2 != 0;
    }

    public static int getOpeningBraceType(char brace) {
        switch(brace) {
            case '(': return BracketType.NORMAL.ordinal();
            case '[': return BracketType.SQUARE.ordinal();
            case '{': return BracketType.CURLY.ordinal();
            default: return INVALID;
        }
    }
}
