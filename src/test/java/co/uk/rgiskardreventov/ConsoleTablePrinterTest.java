package co.uk.rgiskardreventov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by justin on 6/7/16.
 */
public class ConsoleTablePrinterTest {

    private final TablePrinter testSubject;
    private final ByteArrayOutputStream outContent;

    public ConsoleTablePrinterTest() {
        this.testSubject = new ConsoleTablePrinter();
        this.outContent = new ByteArrayOutputStream();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printMultiplicationTable_singleValue() throws Exception {
        int[] values = new int[]{2};
        testSubject.printMultiplicationTable(values);
        assertEquals(singleValueTable(), outContent.toString());
    }

    @Test
    public void printMultiplicationTable_noValues() throws Exception {
        int[] values = new int[]{};
        testSubject.printMultiplicationTable(values);
        assertEquals(noValuesTable(), outContent.toString());
    }

    @Test
    public void printMultiplicationTable_fiveValues() throws Exception {
        int[] values = new int[]{2, 3, 5, 7, 11};
        testSubject.printMultiplicationTable(values);
        assertEquals(fiveValueTable(), outContent.toString());
    }

    private String singleValueTable() {
        return
                "|   | 2 \n" +
                "| 2 | 4 \n";
    }

    private String noValuesTable() {
        return "";
    }

    private String fiveValueTable() {
        return
                "|     |   2 |   3 |   5 |   7 |  11 \n" +
                "|   2 |   4 |   6 |  10 |  14 |  22 \n" +
                "|   3 |   6 |   9 |  15 |  21 |  33 \n" +
                "|   5 |  10 |  15 |  25 |  35 |  55 \n" +
                "|   7 |  14 |  21 |  35 |  49 |  77 \n" +
                "|  11 |  22 |  33 |  55 |  77 | 121 \n";
    }

}