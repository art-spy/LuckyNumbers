import java.util.Arrays;
import java.util.List;

/**
 * Class for printing all menus and messages on the console in German language.
 */
public class ConsolePrintsDE {

    // private String currentMenu;

    /* Various Strings for printing all menus and messages on the console in German language */

    private final String hello =
            "\n--- Willkommen bei LuckyNumbers! --- ";

    private final String mainMenuTitle =
            "\n--- HAUPTMENÜ ---";

    private final String mainMenu =
            "\nUm eine neue Tippreihe für Lotto (6 aus 49) zu generieren, tippe \"l\" ein. \n" +
            "Um eine neue Tippreihe für Eurojackpot (5 aus 50 und 2 aus 10) zu generieren, tippe \"e\" ein. \n" +
            "Um deine Unglückszahlen zu bearbeiten, tippe \"u\" ein. \n" +
            "Um das Programm zu beenden, tippe \"q\" ein.";

    private final String lotto =
            "\nDeine Tippreihe für Lotto (6 aus 49) lautet:";
    private final String eurojackpot =
            "\nDeine Tippreihe für Eurojackpot lautet:";
    private final String ej5from50 =
            "5 aus 50: ";
    private final String ej2from10 =
            "2 aus 10: ";

    private final String unluckyNumbersTitle=
            "\n--- UNGLÜCKSZAHLEN Menü ---";

    private final String unluckyNumbersMenu =
            // "Um deine Unglückzahlen anzuzeigen, tippe \"u\" ein \n" +
            "\nUm eine Unglückzahl hinzuzufügen, tippe \"+\" ein \n" +
            "(es können bis zu sechs Unglückzahlen hinzugefügt werden). \n" +
            "Um alle Unglückzahlen zu löschen, tippe \"-\" ein. \n" +
            "Um ins Hauptmenü zurückzukehren, tippe \"h\" ein.";

    private final String noUnluckyNumbers =
            "\nEs wurden bisher keine Unglückszahlen gespeichert.";
    private final String currentUnluckyNumbers =
            "\nDeine bisherigen Unglückzahlen lauten:";
    private final String typeUnluckyNumbers =
            "\nTippe eine Unglückzahl zwischen 1 und 50 ein:";
    private final String unluckyNumbersDeleted =
            "\nDeine Unglückzahlen wurden gelöscht.";
    private final String unluckyNumbersSaved =
            "\nNeue Unglückzahl gespeichert.";

    private final String maxUnluckyNumbers1 =
            "\nEs können höchstens ";
    private final String maxUnluckyNumbers2 =
            " Unglückszahlen gespeichert werden.";

    private final String confirmSelection =
            "(Bestätige deine Eingabe mit der Enter-Taste)";
    private final String invalidInput =
            "Diese Eingabe ist leider Ungültig.";

    private final String goodbye =
            "\nAuf Wiedersehen, bis zum nächsten Mal :)";


    public ConsolePrintsDE() {
        // setCurrentMenu("MainMenu");
    }

    /**
     * Prints a welcome message on the console.
     */
    public void printHello(){
        System.out.println(hello);
    }

    /**
     * Prints the main menu title on the console.
     */
    public void printMainMenuTitle(){
        System.out.println(mainMenuTitle);
    }

    /**
     * Prints the main menu on the console.
     */
    public void printMainMenu() {
//        if(currentMenu != "MainMenu"){
//            System.out.println(mainMenuTitle);
//            setCurrentMenu("MainMenu");
//        }
        System.out.println(mainMenu);
        System.out.println(confirmSelection);
    }

    /**
     * Prints lucky numbers for Lotto (6 from 49) on the console.
     * @param numbers a list with six lucky numbers
     */
    public void printLotto(List<Integer> numbers) {
        System.out.println(
                lotto + "\n" +
                numbers);
    }

    /**
     * Prints lucky numbers for Eurojackpot (5 from 50 and 2 from 10) on the console.
     * @param ej5from50list a list with five lucky numbers
     * @param ej2from10list a list with two lucky numbers
     */
    public void printEurojackpot(List<Integer> ej5from50list, List<Integer> ej2from10list) {
        System.out.println(
                eurojackpot + "\n" +
                ej5from50 + ej5from50list + "\n" +
                ej2from10 + ej2from10list
        );
    }

    /**
     * Prints the unlucky numbers menu title on the console.
     */
    public void printUnluckyNumbersMenuTitle(){
        System.out.println(unluckyNumbersTitle);
    }

    /**
     * Prints the unlucky numbers menu on the console.
     */
    public void printUnluckyNumbersMenu() {
//        if(currentMenu != "UnluckyNumbersMenu"){
//            System.out.println(unluckyNumbersTitle);
//            setCurrentMenu("UnluckyNumbersMenu");
//        }
        System.out.println(unluckyNumbersMenu);
        System.out.println(confirmSelection);
    }

    /**
     * Prints user defined unlucky numbers on the console.
     * @param numbers a list with unlucky numbers
     */
    public void printCurrentUnluckyNumbers(List<Integer> numbers) {
        System.out.println(
                currentUnluckyNumbers + "\n" +
                numbers
        );
    }

    /**
     * Prints a message on the console that no unlucky numbers has been saved yet.
     */
    public void printNoUnluckyNumbers() {
        System.out.println(noUnluckyNumbers);
    }

    /**
     * Prints a message on the console to type an unlucky number.
     */
    public void printTypeUnluckyNumbers() {
        System.out.println(typeUnluckyNumbers);
    }

    /**
     * Prints a message on the console that all unlucky numbers has been deleted.
     */
    public void printUnluckyNumbersDeleted() {
        System.out.println(unluckyNumbersDeleted);
    }

    /**
     * Prints a message on the console that the maximum amount of unlucky numbers has been already saved.
     * @param maxUnluckyNumbers maximum amount of unlucky numbers that can be saved
     */
    public void printMaxUnluckyNumbers(int maxUnluckyNumbers) {
        System.out.println(maxUnluckyNumbers1 + maxUnluckyNumbers + maxUnluckyNumbers2);
    }

    /**
     * Prints a message on the console that all unlucky numbers has been saved.
     */
    public void printUnluckyNumbersSaved() {
        System.out.println(unluckyNumbersSaved);
    }

    /**
     * Prints a message on the console that the user input is invalid.
     */
    public void printInvalidInput() {
        System.out.println(invalidInput);
    }

    /**
     * Prints a message on the console to confirm the user selection with 'enter'.
     */
    public void printConfirmSelection(){
        System.out.println(confirmSelection);
    }

    /**
     * Prints a goodbye message on the console.
     */
    public void printGoodbye(){
        System.out.println(goodbye);
    }

}
