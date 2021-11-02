import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

/**
 * Class for generating random lucky numbers within a certain range excluding user defined unlucky numbers.
 * Also handles the editing (opening, adding, saving and deleting) of user defined unlucky numbers.
 * Implements the 'LuckyNumbersGeneratorInterface'.
 */
public class LuckyNumbersGenerator implements LuckyNumbersGeneratorInterface {

    /* A List with randomly generated lucky numbers */
    private List<Integer> luckyNumbers;
    /* A List with user defined unlucky numbers */
    private List<Integer> unluckyNumbers;
    /* File that stores user defined unlucky numbers */
    private final File unluckyNumbersFile;
    /* Maximum amount of unlucky numbers that can be defined and stored */
    private final int maxUnluckyNumbers;

    /**
     * Creates a lucky numbers generator instance that considers a maximum amount of 6 unlucky numbers.
     */
    public LuckyNumbersGenerator(){
        this(6);
    }

    /**
     * Creates a lucky numbers generator instance that considers a user defined maximum amount of unlucky numbers.
     * @param unluckyNumberAmount maximum amount of unlucky numbers that can be stored
     */
    public LuckyNumbersGenerator(int unluckyNumberAmount){
        this.luckyNumbers = new ArrayList<Integer>();
        this.unluckyNumbers = new ArrayList<Integer>();
        this.unluckyNumbersFile = new File("unluckynumbers.txt");
        this.maxUnluckyNumbers = unluckyNumberAmount;
        this.openUnluckyNumbers();

        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Instance created.");
    }

    /**
     * Returns a list with previously created random lucky numbers, excluding user defined unlucky numbers.
     * @return a list with previously created lucky numbers excluding user defined unlucky numbers.
     */
    @Override
    public List<Integer> getLuckyNumbers() {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Returning lucky numbers " + this.luckyNumbers);
        return new ArrayList<Integer>(luckyNumbers);

    }

    /**
     * Sets a list with lucky numbers as class variable.
     * @param numbers a list with lucky numbers
     */
    @Override
    public void setLuckyNumbers(List<Integer> numbers) {
        this.luckyNumbers = new ArrayList<Integer>(numbers);
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Set lucky numbers to " + this.luckyNumbers);
    }

    /**
     * Generates a list with a certain amount of random lucky numbers, excluding user defined unlucky numbers.
     * @param amount the amount of lucky numbers that should be created
     * @param min the smallest possible lucky number
     * @param max the biggest possible lucky number
     */
    @Override
    public List<Integer> generateLuckyNumbers(int amount, int min, int max) {
        Random random = new Random();
        int randomInt;
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 1; i <= amount; i++){
            do{
                randomInt = random.nextInt((max - min) + 1) + min;
            }
            while (numbers.contains(randomInt) || this.unluckyNumbers.contains(randomInt)) ;
            numbers.add(randomInt);
            Collections.sort(numbers);
        }
        this.setLuckyNumbers(numbers);

        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Lucky Numbers generated: " + numbers);
        return numbers;

    }

    /**
     * Sorts a list of numbers in descending order.
     * @param numbers a list of unsorted numbers
     * @return a list of sorted numbers in descending order
     */
    @Override
    public List<Integer> sortNumbers(List<Integer> numbers) {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": List before sorting: " + numbers);
        Collections.sort(numbers);

        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Returning list after sorting: " + numbers);
        return new ArrayList<Integer>(numbers);
    }

    /**
     * Sets a list with unlucky numbers as class variable.
     * @param numbers a list with unlucky numbers
     */
    @Override
    public void setUnluckyNumbers(List<Integer> numbers) {
        this.unluckyNumbers = new ArrayList<Integer>(numbers);
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Set unlucky numbers to " + numbers);
    }

    /**
     * Returns a list with user defined unlucky numbers.
     * @return List<Integer> a list with user defined unlucky numbers
     */
    @Override
    public List<Integer> getUnluckyNumbers() {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Returning list with unlucky numbers: " + this.unluckyNumbers);
        return new ArrayList<Integer>(unluckyNumbers);
    }

    /**
     * Adds a new number to the list of unlucky numbers.
     * @param number a new unlucky number
     * @return boolean true if unlucky number could be added or is already in the list
     *                 false if max amount of unlucky numbers is already saved
     */
    @Override
    public boolean addUnluckyNumber(int number) {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Trying to add '" + number + "' to unlucky numbers" );
        if(this.unluckyNumbers.contains(number)){
            return true;
        }
        else if(this.unluckyNumbers.size() < this.maxUnluckyNumbers){
            this.unluckyNumbers.add(number);
            Collections.sort(unluckyNumbers);
            this.saveUnluckyNumbers();
            return true;
        }
        else {
            MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Couldn't add '" + number + "' to unlucky numbers" );
            return false;
        }
    }

    /**
     * Opens a file with previously saved unlucky numbers.
     * If no file exists an empty file is created.
     */
    @Override
    public void openUnluckyNumbers() {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Trying to open and read file with unlucky numbers.");

        try {
            if (this.unluckyNumbersFile.exists()) {
                Scanner myReader = new Scanner(this.unluckyNumbersFile);
                int i = 0;
                while (myReader.hasNextLine() && i < this.maxUnluckyNumbers) {
                                           // && !myReader.nextLine().isEmpty()
                    int intNextLine = Integer.parseInt(myReader.nextLine());
                    if( !unluckyNumbers.contains(intNextLine) ) {
                        this.unluckyNumbers.add(intNextLine);
                    }
                    i++;
                }
                myReader.close();
                MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Reading unlucky numbers from file succeeded: " + this.unluckyNumbers);
                saveUnluckyNumbers();
            } else {
                this.saveUnluckyNumbers();
            }
        }
        catch (IOException e) {
            MyLogger.log(Level.WARNING, LuckyNumbersGenerator.class.getName() + ": \n" + e);
            e.printStackTrace();
        }
    }

    /**
     * Saves the list of unlucky numbers to a file.
     */
    @Override
    public void saveUnluckyNumbers() {
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Trying to save file with unlucky numbers: " + this.unluckyNumbers);

        StringBuilder unluckyNumbersString = new StringBuilder();
        for (int i: this.unluckyNumbers){
            // unluckyNumbersString.concat(String.valueOf(i));
            unluckyNumbersString.append(i);
            if( (this.unluckyNumbers.indexOf(i) + 1) != this.unluckyNumbers.size()){
                // unluckyNumbersString.concat("\n");
                unluckyNumbersString.append("\n");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(this.unluckyNumbersFile);
            myWriter.write(unluckyNumbersString.toString());
            myWriter.close();

            MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Wrote following String for file with unlucky numbers: \n\"" + unluckyNumbersString + "\"");
        }
        catch (IOException e) {
            MyLogger.log(Level.WARNING, LuckyNumbersGenerator.class.getName() + ": \n" + e);
            e.printStackTrace();
        }
    }

    /**
     * Deletes all previously saved unlucky numbers.
     */
    @Override
    public void deleteUnluckyNumbers() {
        this.unluckyNumbers.clear();
        MyLogger.log(Level.INFO, LuckyNumbersGenerator.class.getName() + ": Cleared list with unlucky numbers: " + this.unluckyNumbers);
        this.saveUnluckyNumbers();
    }
}
