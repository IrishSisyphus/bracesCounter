import java.util.Map;

public class BracesCounter {

    public static final int INVALID = -1;

    public static final Map<String, String> BRACKETS_MAP = Map.of(
            "(", ")",
            "[", "]",
            "{", "}"
    );

    public static int count(String braces) {
        if(isUnevenNumberOfBraces(braces)) {
            return INVALID;
        }

        int count = 0;
        int stringLowerIndex = 0;
        while (stringLowerIndex < braces.length()) {
            String openingCharacterType = braces.substring(stringLowerIndex, stringLowerIndex + 1);

            if (!BRACKETS_MAP.containsKey(openingCharacterType)) {
                return INVALID;
            }

            String matchingClosingCharacter = BRACKETS_MAP.get(openingCharacterType);

            int stringUpperIndex = findCorrespondingClosingBracket(braces,
                    openingCharacterType,
                    matchingClosingCharacter,
                    stringLowerIndex + 1);

            if (stringUpperIndex == INVALID) {
                return INVALID;
            }

            // is Upper Index the next index after the Lower Index
            if (stringUpperIndex == stringLowerIndex + 1) {
                count += 1;
            } else {
                count += 1 + count(braces.substring(stringLowerIndex + 1, stringUpperIndex));
            }

            stringLowerIndex = stringUpperIndex + 1;
        }

        return  count;
    }

    public static int findCorrespondingClosingBracket(String braces,
                                                      String matchingOpeningCharacter,
                                                      String matchingClosingCharacter,
                                                      int index
    ) {
        int numberOfMatchingCharactersOfSameOpeningType = 0;
        while(index < braces.length()) {
            if(braces.substring(index, index + 1).equals(matchingOpeningCharacter)) {
                numberOfMatchingCharactersOfSameOpeningType++;
            }

            if(braces.charAt(index) == matchingClosingCharacter.charAt(0)) {
                if(numberOfMatchingCharactersOfSameOpeningType == 0) {
                    return index;
                }

                numberOfMatchingCharactersOfSameOpeningType --;
            }
            index++;
        }
        return INVALID;
    }

    public static boolean isUnevenNumberOfBraces(String braces) {
        return braces.length() % 2 != 0;
    }
}
