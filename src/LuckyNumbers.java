import java.util.Scanner;
import java.util.logging.Level;

/**
 * The main UI functionality  of the Lucky Numbers program.
 * Shows the menu and messages, proceeds user input.
 * Uses the 'ConsolePrints' class for printing the menu and messages on the console.
 * Uses the 'LuckyNumberGenerator' for generating random lucky numbers within a certain range
 * excluding user defined unlucky numbers.
 */
public class LuckyNumbers {

    /* Arrays with basic conditions for generating lucky numbers for Lotto (6from49),
    * Eurojackpot (5from50) and Eurojackpot (2 from 10):
    * first int: amount of lucky numbers that should be generated
    * second int: lowest lucky number
    * third int: greatest lucky number
    */
    private static final int[] lotto                  = {6, 1, 49};
    private static final int[] ej5from50              = {5, 1, 50};
    private static final int[] ej2from10              = {2, 1, 10};

    /* largest amount of unlucky numbers that can be stored */
    private static final int maxUnluckyNumbersAmount  = 6;
    /* lowest unlucky number that can be stored */
    private static final int minUnluckyNumber         = 1;
    /* largest unlucky number that can be stored */
    private static final int maxUnluckyNumber         = 50;

    /* last lottery ("lotto" or "eurojackpot") that lucky numbers were generated for */
    private String lastLottery;

    /* current menu ("MainMenu" or "UnluckyNumbersMenu") that lucky numbers were generated for */
    private String currentMenu;

    /* instance of ConsolePrints that is used for printing the menu and other messages on the terminal */
    private ConsolePrintsDE consolePrints;
    /* instance of LuckyNumbersGenerator that is used for generating random lucky numbers
    excluding user defined unlucky numbers */
    private LuckyNumbersGenerator luckyNumbersGenerator;

    /**
     * Constructor that initialises the object variables.
     */
    public LuckyNumbers(){
        // MyLogger.log(Level.INFO, this.getClass().getName() + this.getClass().getEnclosingMethod().getName() + ": Creating instance.");
        this.lastLottery = "lotto";
        this.consolePrints = new ConsolePrintsDE();
        this.luckyNumbersGenerator = new LuckyNumbersGenerator(maxUnluckyNumbersAmount);
        this.currentMenu = "";

        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Instance created.");

        this.run();

    }

    /**
     * Creates an object of this LuckyNumbers class.
     */
    public static void main(String[] args) {
        LuckyNumbers luckyNumbers = new LuckyNumbers();

    }

    /**
     * Runs the program. Shows the already stored unlucky numbers and the main menu on the console.
     */
    private void run(){
        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Printing welcome screen and main menu.");

        consolePrints.printHello();
        this.printUnluckyNumbers();
        this.printMainMenuUI();
    }

    /**
     * Prints the main menu (by using the 'ConsolePrints' class), handles the user input
     * and prints lucky numbers excluding user defined unlucky numbers (by using the 'LuckyNumbersGenerator' class)
     */
    private void printMainMenuUI(){
        Scanner scanner = new Scanner(System.in);
        try {
            if(!currentMenu.equals("MainMenu")){
                consolePrints.printMainMenuTitle();
                this.currentMenu = "MainMenu";
            }
            consolePrints.printMainMenu();
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Main menu printed, user input expected.");

            String input = scanner.nextLine().toLowerCase();
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": User Input '" + input + "'." );

            switch (input) {
                case "l":
                    this.printLottoNumbers();
                    this.printMainMenuUI();
                    break;
                case "e":
                    this.printEurojackpotNumbers();
                    this.printMainMenuUI();
                    break;
                case "u":
                    this.printUnluckyNumbersMenuUI();
                    break;
                case "q":
                    consolePrints.printGoodbye();

                    MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Closing program." );
                    System.exit(0);
                    break;
                default:
                    MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Invalid user input." );
                    consolePrints.printInvalidInput();
                    this.printMainMenuUI();
            }
        }
        catch (Exception e) {
            // java.util.InputMismatchException e
            MyLogger.log(Level.WARNING, LuckyNumbers.class.getName() + ": \n" + e);

            consolePrints.printInvalidInput();
            consolePrints.printMainMenu();
            // scanner.nextLine();
        }
    }

    /**
     * Prints lucky numbers for Lotto (6 from 49) excluding user defined unlucky numbers
     * (by using the 'ConsolePrints' and 'LuckyNumbersGenerator' class).
     */
    private void printLottoNumbers(){
        consolePrints.printLotto(
                luckyNumbersGenerator.generateLuckyNumbers(lotto[0], lotto[1], lotto[2])
        );
        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Lucky numbers for Lotto printed." );
        // this.printMainMenuUI();
    }

    /**
     * Prints lucky numbers for Eurojackpot (5 from 50 and 2 from 10) excluding user defined unlucky numbers
     * (by using the 'ConsolePrints' and 'LuckyNumbersGenerator' class).
     */
    private void printEurojackpotNumbers(){
        consolePrints.printEurojackpot(
                luckyNumbersGenerator.generateLuckyNumbers(ej5from50[0], ej5from50[1], ej5from50[2]),
                luckyNumbersGenerator.generateLuckyNumbers(ej2from10[0], ej2from10[1], ej2from10[2])
        );
        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Lucky numbers for Eurojackpot printed." );
        // this.printMainMenuUI();
    }

    /**
     * Prints the unlucky numbers menu (by using the 'ConsolePrints' class) and handles the user input.
     */
    private void printUnluckyNumbersMenuUI(){
        Scanner scanner = new Scanner(System.in);
        try {
            if(!currentMenu.equals("UnluckyNumbersMenu")){
                consolePrints.printUnluckyNumbersMenuTitle();
                this.currentMenu = "UnluckyNumbersMenu";
            }
            this.printUnluckyNumbers();
            consolePrints.printUnluckyNumbersMenu();
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Unlucky numbers menu printed, user input expected.");

            String input = scanner.nextLine().toLowerCase();
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": User input '" + input + "'." );

            switch (input) {
//                case "u":
//                    this.showUnluckyNumbers();
//                    this.printUnluckyNumbersMenuUI();
//                    break;
                case "+":
                    this.addUnluckyNumbersUI();
                    this.printUnluckyNumbersMenuUI();
                    break;
                case "-":
                    this.deleteUnluckyNumbers();
                    this.printUnluckyNumbersMenuUI();
                    break;
                case "h":
                    this.printMainMenuUI();
                    break;
                default:
                    MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Invalid user input." );
                    consolePrints.printInvalidInput();
                    this.printUnluckyNumbersMenuUI();
            }
        }
        catch (Exception e) {
            // java.util.InputMismatchException e
            MyLogger.log(Level.WARNING, LuckyNumbers.class.getName() + ": \n" + e);

            consolePrints.printInvalidInput();
            consolePrints.printUnluckyNumbersMenu();
            scanner.nextLine();
        }
    }

    /**
     * Prints the user defined unlucky numbers (by using the 'ConsolePrints' and 'LuckyNumbersGenerator' class)
     * and handles the user input.
     */
    private void printUnluckyNumbers(){
        if( luckyNumbersGenerator.getUnluckyNumbers().isEmpty() ) {
            consolePrints.printNoUnluckyNumbers();
        }
        else {
            consolePrints.printCurrentUnluckyNumbers(
                    luckyNumbersGenerator.getUnluckyNumbers()
            );
        }
        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Printed unlucky numbers." );
    }

    /**
     * Lets the user add a new unlucky number (by using the 'ConsolePrints' and 'LuckyNumbersGenerator' class)
     * and handles the user input.
     */
    private void addUnluckyNumbersUI(){
        Scanner scanner = new Scanner(System.in);
        try {
            consolePrints.printTypeUnluckyNumbers();
            consolePrints.printConfirmSelection();
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Unlucky number from user expected." );

            int input = Integer.parseInt(scanner.nextLine());
            MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": User input '" + input + "'." );

            if(input >= minUnluckyNumber && input <= maxUnluckyNumber){
                if( luckyNumbersGenerator.addUnluckyNumber(input) ){
                    consolePrints.printUnluckyNumbersSaved();
                    // this.showUnluckyNumbers();
                    // this.printUnluckyNumbersMenuUI();
                }
                else {
                    consolePrints.printMaxUnluckyNumbers(maxUnluckyNumbersAmount);
                    // this.printUnluckyNumbersMenuUI();
                }
            }
            else {
                consolePrints.printInvalidInput();
                this.addUnluckyNumbersUI();
                // this.printUnluckyNumbersMenuUI();
            }
        }
        catch (Exception e) {
            // java.util.InputMismatchException e
            MyLogger.log(Level.WARNING, LuckyNumbers.class.getName() + ": \n" + e);

            consolePrints.printInvalidInput();
            consolePrints.printTypeUnluckyNumbers();
            scanner.nextLine();
        }
    }

    /**
     * Deletes all unlucky numbers (by using the 'ConsolePrints' and 'LuckyNumbersGenerator' class)
     * and handles the user input.
     */
    private void deleteUnluckyNumbers(){
        luckyNumbersGenerator.deleteUnluckyNumbers();
        consolePrints.printUnluckyNumbersDeleted();

        MyLogger.log(Level.INFO, LuckyNumbers.class.getName() + ": Printed message 'Unlucky numbers deleted'." );
    }
}
