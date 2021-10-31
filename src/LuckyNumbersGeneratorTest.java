import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LuckyNumbersGeneratorTest {

    static LuckyNumbersGenerator luckyNumbersGenerator;
    static File unluckyNumbersFile;

    static Integer[][] unsortedIntArrays = { {13, 50, 45, 1, 37, 8},
                                             {37, 45, 0, 17, 46, 22, 25, 6, 50, 8} };
    static Integer[][] sortedIntArrays =   { {1, 8, 13, 37, 45, 50},
                                             {0, 6, 8, 17, 22, 25, 37, 45, 46, 50} };

    static ArrayList<Integer> unsortedList1_6numbers;
    static ArrayList<Integer> sortedList1_6numbers;
    static String sortedString1_6numbers;

    static ArrayList<Integer> unsortedList2_10numbers;
    static ArrayList<Integer> sortedList2_10numbers;
    static ArrayList<Integer> sortedList2_6numbers;
    static String sortedString2_10numbers;
    static String sortedString2_6numbers;

    public LuckyNumbersGeneratorTest(){

    }

    @BeforeAll
    static void setUpBeforeAll(){
        luckyNumbersGenerator = new LuckyNumbersGenerator();
        // luckyNumbersGenerator.openUnluckyNumbers();
        unluckyNumbersFile = new File("unluckynumbers.txt");
        setUpDeleteUnluckyNumbers();

        unsortedList1_6numbers = new ArrayList<Integer>( Arrays.asList(unsortedIntArrays[0]) );
        unsortedList2_10numbers = new ArrayList<Integer>( Arrays.asList(unsortedIntArrays[1]) );
        sortedList1_6numbers = new ArrayList<Integer>( Arrays.asList(sortedIntArrays[0]) );
        sortedList2_10numbers = new ArrayList<Integer>( Arrays.asList(sortedIntArrays[1]) );
        sortedList2_6numbers = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++) {
            sortedList2_6numbers.add(sortedIntArrays[1][i]);
        }

        sortedString1_6numbers = "1\n8\n13\n37\n45\n50";
        sortedString2_10numbers = "0\n6\n8\n17\n22\n25\n37\n45\n46\n50";
        sortedString2_6numbers = "0\n6\n8\n17\n22\n25";

        System.out.println("Directly after setUpBeforeAll Method: ");
        System.out.println("Sorted list #1 with 6 numbers: " + sortedList1_6numbers);
    }

    @AfterAll
    static void tearDownAfterAll() {
        // luckyNumbersGenerator.deleteUnluckyNumbers();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    static void setUpDeleteUnluckyNumbers(){
        if (unluckyNumbersFile.exists()) {
            unluckyNumbersFile.delete();
        }

        luckyNumbersGenerator.setUnluckyNumbers(new ArrayList<Integer>());
    }

    static String filetoString(){
        String s = "";
        try {
            if (unluckyNumbersFile.exists()) {
                Scanner myReader = new Scanner(unluckyNumbersFile);
                while (myReader.hasNextLine()) {
                    s = s + myReader.nextLine();
                    if(myReader.hasNextLine()){
                        s = s + "\n";
                    }
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Test
    void getLuckyNumbers() {
        luckyNumbersGenerator.setLuckyNumbers(sortedList1_6numbers);

        assertAll("Method should return an ArrayList with lucky numbers",
                () -> assertEquals(sortedList1_6numbers.getClass(), luckyNumbersGenerator.getLuckyNumbers().getClass()),
                () -> assertTrue(luckyNumbersGenerator.getLuckyNumbers().containsAll(sortedList1_6numbers))
        );

        System.out.println("Predefined sorted list #1: " + sortedList1_6numbers);
        System.out.println("Set list with lucky numbers" + luckyNumbersGenerator.getLuckyNumbers());
    }

    @Test
    @Disabled
    void setLuckyNumbers() {
        // see getLuckyNumbers() test
    }

    @RepeatedTest(100)
    void generateLuckyNumbers(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() <= 1 ){
            // setUpDeleteUnluckyNumbers();
            luckyNumbersGenerator.setUnluckyNumbers(sortedList1_6numbers);
            luckyNumbersGenerator.saveUnluckyNumbers();
        }

        Random random = new Random();
        final int randomMin = random.nextInt((20 - 1) + 1) + 1;
        final int randomMax = random.nextInt((50 - 31) + 1) + 31;
        final int randomAmount = random.nextInt((10 - 5) + 1) + 5;
        final ArrayList<Integer> luckyNumbers = new ArrayList<>(
                luckyNumbersGenerator.generateLuckyNumbers(randomAmount,randomMin,randomMax)
        );

        // ArrayList<Integer> lottoNumbers = new ArrayList<>();
        // lottoNumbers = this.luckyNumbersGenerator.generateLuckyNumbers(6,1,49);
        final int minNumber = Collections.min(luckyNumbers);
        final int maxNumber = Collections.max(luckyNumbers);

        System.out.println(repetitionInfo);
        System.out.println("Predefined sorted list #1 with 6 numbers: " + sortedList1_6numbers);
        System.out.println("Min lucky number should be: " + minNumber);
        System.out.println("Max lucky number should be: " + maxNumber);
        System.out.println("Amount of lucky numbers: " + randomAmount);
        System.out.println("Set unlucky Numbers: " + luckyNumbersGenerator.getUnluckyNumbers());
        System.out.println("Generated lucky Numbers: " + luckyNumbersGenerator.getLuckyNumbers());
        assertAll("Should generate " + randomAmount + " lucky numbers between " + randomMin + " and " + randomMax,
                () -> assertEquals(randomAmount, luckyNumbers.size()),
                () -> assertTrue( minNumber >= randomMin && minNumber <= randomMax),
                () -> assertTrue( maxNumber <= randomMax && maxNumber >= randomMin),
                () -> assertFalse( luckyNumbersGenerator.getLuckyNumbers().containsAll(sortedList1_6numbers))
        );
    }

    @Test
    void sortNumbers() {

        final ArrayList<Integer> generatedSortedList1 = (ArrayList<Integer>) luckyNumbersGenerator.sortNumbers(unsortedList1_6numbers);
        final ArrayList<Integer> generatedSortedList2 = (ArrayList<Integer>) luckyNumbersGenerator.sortNumbers(unsortedList2_10numbers);

        System.out.println("Predefined sorted list #1: " + sortedList1_6numbers);
        System.out.println("Predefined generated list #1: " + generatedSortedList1);
        System.out.println("Predefined sorted list #2: " + sortedList2_10numbers);
        System.out.println("Predefined generated list #2: " + generatedSortedList2);

        assertAll("Generated sorted List should be same a predefined sorted list",
                () -> assertEquals(sortedList1_6numbers, generatedSortedList1),
                () -> assertEquals(sortedList2_10numbers, generatedSortedList2)
        );

    }

    @Test
    void setUnluckyNumbers() {
        luckyNumbersGenerator.setUnluckyNumbers(sortedList1_6numbers);

        assertAll("Method should return an ArrayList with unlucky numbers",
                () -> assertEquals(sortedList1_6numbers.getClass(), luckyNumbersGenerator.getUnluckyNumbers().getClass()),
                () -> assertTrue(luckyNumbersGenerator.getUnluckyNumbers().containsAll(sortedList1_6numbers))
        );

        System.out.println("Predefined sorted list #1: " + sortedList1_6numbers);
        System.out.println("Set list with unlucky numbers" + luckyNumbersGenerator.getLuckyNumbers());
    }

    @Test
    @Disabled
    void getUnluckyNumbers() {
        // see setUnluckyNumbers() test
    }

    @RepeatedTest(10)
    void addUnluckyNumber(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() == 1){
            setUpDeleteUnluckyNumbers();
        }

        boolean addUnluckyNumberBol = luckyNumbersGenerator.addUnluckyNumber(repetitionInfo.getCurrentRepetition());

        System.out.println("Current repetition: " + repetitionInfo.getCurrentRepetition());
        System.out.println("Unlucky numbers: " + luckyNumbersGenerator.getUnluckyNumbers() );

        if(repetitionInfo.getCurrentRepetition() < 7){
            assertTrue( addUnluckyNumberBol );
        }
        else if (repetitionInfo.getCurrentRepetition() >= 7){
            assertFalse( addUnluckyNumberBol );
        }
    }

    @Test()
    void openUnluckyNumbers() {
        setUpDeleteUnluckyNumbers();
        luckyNumbersGenerator.openUnluckyNumbers();

        assertAll("Empty File with unlucky numbers exists and is readable",
                () -> assertTrue(unluckyNumbersFile.exists()),
                () -> assertTrue(unluckyNumbersFile.isFile()),
                () -> assertTrue(unluckyNumbersFile.canRead()),
                () -> assertTrue(unluckyNumbersFile.canWrite()),
                () -> assertEquals("", filetoString())
        );

        System.out.println("Content of file (should be empty): \n" + filetoString());

        try {
            FileWriter myWriter = new FileWriter(unluckyNumbersFile);
            myWriter.write(sortedString2_10numbers);
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        luckyNumbersGenerator.openUnluckyNumbers();

        assertAll("File with unlucky numbers exists and is readable",
                () -> assertTrue(unluckyNumbersFile.exists()),
                () -> assertTrue(unluckyNumbersFile.isFile()),
                () -> assertTrue(unluckyNumbersFile.canRead()),
                () -> assertTrue(unluckyNumbersFile.canWrite()),
                () -> assertTrue(luckyNumbersGenerator.getUnluckyNumbers().size() == 6)
        );

        System.out.println("Content of edited file: \n" + filetoString());
        System.out.println("Set unlucky Numbers: " + luckyNumbersGenerator.getUnluckyNumbers());

        for (int i = 0; i < luckyNumbersGenerator.getUnluckyNumbers().size(); i++){
            assertEquals(sortedList2_10numbers.get(i), luckyNumbersGenerator.getUnluckyNumbers().get(i));
        }

    }

    @Test()
    void saveUnluckyNumbers() {
        setUpDeleteUnluckyNumbers();

        luckyNumbersGenerator.setUnluckyNumbers(sortedList2_10numbers);
        luckyNumbersGenerator.saveUnluckyNumbers();

        final String finalTempString = filetoString();

        System.out.println("String from file: " + "\"" + finalTempString + "\"");
        System.out.println("String from predefined Array: "+ "\"" + sortedString2_10numbers+ "\"");

        assertAll("File with unlucky number should be saved",
                () -> assertTrue(unluckyNumbersFile.exists()),
                () -> assertTrue(unluckyNumbersFile.isFile()),
                () -> assertTrue(unluckyNumbersFile.canRead()),
                () -> assertTrue(unluckyNumbersFile.canWrite()),
                () -> assertEquals(sortedList2_10numbers.size(), luckyNumbersGenerator.getUnluckyNumbers().size()),
                () -> assertEquals(sortedString2_10numbers, finalTempString)
        );

    }

    @Test
    void deleteUnluckyNumbers() {
        luckyNumbersGenerator.setUnluckyNumbers(sortedList1_6numbers);
        luckyNumbersGenerator.saveUnluckyNumbers();
        luckyNumbersGenerator.deleteUnluckyNumbers();

        assertAll("Unlucky Numbers should be deleted fom file and ArrayList",
                () -> assertTrue(unluckyNumbersFile.exists()),
                () -> assertTrue(unluckyNumbersFile.isFile()),
                () -> assertTrue(unluckyNumbersFile.canRead()),
                () -> assertTrue(unluckyNumbersFile.canWrite()),
                () -> assertEquals("", filetoString()),
                () -> assertEquals(0, luckyNumbersGenerator.getUnluckyNumbers().size())
        );
        System.out.println("Content of file: " + filetoString());
        System.out.println("Set unlucky numbers list: " + luckyNumbersGenerator.getUnluckyNumbers());

    }
}