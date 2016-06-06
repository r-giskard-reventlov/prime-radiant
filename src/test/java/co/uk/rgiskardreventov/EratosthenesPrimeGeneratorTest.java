package co.uk.rgiskardreventov;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;

/**
 * Created by justin on 6/6/16.
 */
public class EratosthenesPrimeGeneratorTest {

    private final PrimeGenerator testSubject;

    public EratosthenesPrimeGeneratorTest() {
        testSubject = new EratosthenesPrimeGenerator(10);
    }

    @Test
    public void generateFixedNumberOfPrimes_generateFirstPrime() throws Exception {
        int[] primes = testSubject.generateFixedNumberOfPrimes(1);
        int[] expected = new int[]{2};
        assertTrue(Arrays.equals(primes, expected));
    }

}