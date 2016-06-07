package uk.co.rgiskardreventlov;

/**
 * Created by justin on 6/7/16.
 */
public class PrimePrinter {

    public static final int SIEVE_SIZE = Integer.MAX_VALUE / 32;
    public static final int L1_CACHE_SIZE_IN_KILOBYTES = 32;
    public static final int SEGMENT_SIZE_IN_BYTES = L1_CACHE_SIZE_IN_KILOBYTES * 1024;

    private final PrimeGenerator primeGenerator;
    private final TablePrinter tablePrinter;

    public PrimePrinter(PrimeGenerator primeGenerator, TablePrinter tablePrinter) {
        this.primeGenerator = primeGenerator;
        this.tablePrinter = tablePrinter;
    }

    public void printMultiplicationTableForFirstNPrimes(int n) {
        int[] primes = primeGenerator.generateFixedNumberOfPrimes(n);
        tablePrinter.printMultiplicationTable(primes);
    }

    public static void main(String[] args) {
        int n = nthPrime(args);
        PrimeGenerator pg = new EratosthenesPrimeGenerator(SEGMENT_SIZE_IN_BYTES, SIEVE_SIZE);
        TablePrinter tp = new ConsoleTablePrinter();
        PrimePrinter pp = new PrimePrinter(pg, tp);
        pp.printMultiplicationTableForFirstNPrimes(n);
    }

    private static int nthPrime(String[] args) {
        if(args.length < 1) {
            throw new ValidationException("You must supply the number of primes to generate. " + printUsage());
        } else {
            try {
                Integer n = Integer.parseInt(args[0]);
                if(n < 1) {
                    throw new ValidationException("Number of primes to generate is not in the correct range. " + printUsage());
                }
                return n;
            } catch (NumberFormatException e) {
                throw new ValidationException("You must a numerical value of n. " + printUsage());
            }
        }
    }

    private static String printUsage() {
        return "usage: prime-radiant <n>, n is the natural number of primes to generate where n > 1 AND n < number of primes in sieve";
    }
}
