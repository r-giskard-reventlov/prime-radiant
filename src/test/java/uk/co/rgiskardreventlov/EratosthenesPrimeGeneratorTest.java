package uk.co.rgiskardreventlov;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by justin on 6/6/16.
 */
public class EratosthenesPrimeGeneratorTest {

    public static final int SIEVE_SIZE = 100000;
    public static final int L1_CACHE_SIZE_IN_KILOBYTES = 32;
    public static final int SEGMENT_SIZE_IN_BYTES = L1_CACHE_SIZE_IN_KILOBYTES * 1024;
    public static final int N_VALUE_OUTSIDE_SIEVE_RANGE = Integer.MAX_VALUE / 32;

    private final PrimeGenerator testSubject;

    public EratosthenesPrimeGeneratorTest() {
        testSubject = new EratosthenesPrimeGenerator(SEGMENT_SIZE_IN_BYTES, SIEVE_SIZE);
    }

    @Test
    public void generateFixedNumberOfPrimes_generateFirstPrime() throws Exception {
        int[] primes = testSubject.generateFixedNumberOfPrimes(1);
        int[] expected = new int[]{2};
        assertTrue(Arrays.equals(primes, expected));
    }

    @Test(expected=SieveTooSmallException.class)
    public void generateFixedNumberOfPrimes_sieveTooSmall() throws Exception {
        testSubject.generateFixedNumberOfPrimes(N_VALUE_OUTSIDE_SIEVE_RANGE);
    }

    @Test
    public void generateFixedNumberOfPrimes_generateFirstFourPrimes() throws Exception {
        int[] primes = testSubject.generateFixedNumberOfPrimes(4);
        int[] expected = new int[]{2, 3, 5, 7};
        assertTrue(Arrays.equals(primes, expected));
    }

    @Test
    public void generateFixedNumberOfPrimes_requestZeroPrimes() throws Exception {
        int[] primes = testSubject.generateFixedNumberOfPrimes(0);
        int[] expected = new int[]{};
        assertTrue(Arrays.equals(primes, expected));
    }

    @Test
    public void generateFixedNumberOfPrimes_request5000thPrime() throws Exception {
        int[] primes = testSubject.generateFixedNumberOfPrimes(5000);
        int lastPrime = primes[primes.length - 1];
        assertEquals(48611, lastPrime);
    }

}