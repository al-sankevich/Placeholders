package al.sankevich.placeholders.dtos;

/**
 * Contains all required about {@code placeholder}'s part for parsing.
 *
 * @param name        the component's name.
 * @param endingChars the characters, which can mean the end of the component in the {@code source}.
 */
public record Component(
        String name,
        char... endingChars
) {

    /**
     * Checks if the provided {@code symbol} is in list of this component's {@code ending chars}.
     *
     * @param symbol the character required to be checked.
     * @return {@code true} if the provided {@code symbol} is not an ending char for this component, otherwise {@code false}.
     */
    public boolean isNotEnd(char symbol) {
        for (char reservedChar : endingChars) {
            if (symbol == reservedChar) {
                return false;
            }
        }

        return true;
    }
}
