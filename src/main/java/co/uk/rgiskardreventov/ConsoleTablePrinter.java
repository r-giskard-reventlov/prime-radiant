package co.uk.rgiskardreventov;

/**
 * Created by justin on 6/7/16.
 */
public class ConsoleTablePrinter implements TablePrinter {

    public void printMultiplicationTable(int[] values) {
        if(values.length == 0) {
            return;
        }
        int spacingRequired = calculateCellWidthBasedOnLastValue(values[values.length - 1]);
        printHeaderRow(values, spacingRequired);
        printBody(values, spacingRequired);
    }

    private int calculateCellWidthBasedOnLastValue(int value) {
        long lastValue = (long)value;
        long lastValueTableValue = lastValue * lastValue;
        return String.valueOf(lastValueTableValue).length();
    }

    private void printEntry(int spacing, int val) {
        int spacingWithPadding = spacing + 1;
        String format = "|%" + spacingWithPadding + "d ";
        System.out.print(String.format(format, val));
    }

    private void printEntry(int spacing) {
        int spacingWithPadding = spacing + 1;
        String format = "|%" + spacingWithPadding + "s ";
        System.out.print(String.format(format, ""));
    }

    private void printBody(int[] values, int spacingRequired) {
        for(int i=0; i<values.length; i++) {
            for(int j=0; j<values.length; j++) {
                if(j == 0) {
                    printEntry(spacingRequired, values[i]);
                }
                printEntry(spacingRequired, values[i] * values[j]);
            }
            System.out.print("\n");
        }
    }

    private void printHeaderRow(int[] values, int spacingRequired) {
        printEntry(spacingRequired);
        for(int i=0; i<values.length; i++) {
            printEntry(spacingRequired, values[i]);
        }
        System.out.print("\n");
    }
}
