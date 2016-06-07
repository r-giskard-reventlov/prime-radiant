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

    private String singleValueTable() {
        return
                "|   | 2 \n" +
                "| 2 | 4 \n";

    }

}