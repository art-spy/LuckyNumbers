import java.util.ArrayList;
import java.util.List;

/**
 * Interface for generating lucky numbers within a certain range excluding user defined unlucky numbers.
 * Also handles the editing (opening, adding, saving and deleting) of user defined unlucky numbers.
 */
public interface LuckyNumbersGeneratorInterface {

    /**
     * Returns a list with previously created random lucky numbers, excluding user defined unlucky numbers.
     * @return a list with previously created lucky numbers excluding user defined unlucky numbers.
     */
    public List<Integer> getLuckyNumbers();

    /**
     * Sets a list with lucky numbers as class variable.
     * @param numbers a list with lucky numbers
     */
    public void setLuckyNumbers(List<Integer> numbers);

    /**
     * Generates a list with a certain amount of random lucky numbers, excluding user defined unlucky numbers.
     * @param amount the amount of lucky numbers that should be created
     * @param min the smallest possible lucky number
     * @param max the biggest possible lucky number
     */
    public List<Integer> generateLuckyNumbers (int amount, int min, int max);

    /**
     * Sorts a list of numbers in descending order.
     * @param numbers a list of unsorted numbers
     * @return a list of sorted numbers in descending order
     */
    public List<Integer> sortNumbers(List<Integer> numbers);

    /**
     * Sets a list with unlucky numbers as class variable.
     * @param numbers a list with unlucky numbers
     */
    public void setUnluckyNumbers(List<Integer> numbers);

    /**
     * Returns a list with user defined unlucky numbers.
     * @return List<Integer> a list with user defined unlucky numbers
     */
    public List<Integer> getUnluckyNumbers();

    /**
     * Adds a new number to the list of unlucky numbers.
     * @param number a new unlucky number
     * @return boolean true if unlucky number could be added or is already in the list
     *                 false if max amount of unlucky numbers is already saved
     */
    public boolean addUnluckyNumber(int number);

    /**
     * Saves the list of unlucky numbers to a file.
     */
    public void saveUnluckyNumbers();

    /**
     * Opens a file with previously saved unlucky numbers.
     * If no file exists an empty file is created.
     */
    public void openUnluckyNumbers();

    /**
     * Deletes all previously saved unlucky numbers.
     */
    public void deleteUnluckyNumbers();
}

