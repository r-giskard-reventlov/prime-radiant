package uk.co.rgiskardreventlov;

import java.util.Arrays;

/**
 * Created by justin on 6/6/16.
 */
public class EratosthenesPrimeGenerator implements PrimeGenerator {

    private int segmentSizeBytes;
    private int sieveSize;

    public EratosthenesPrimeGenerator(int segmentSizeBytes, int sieveSize) {
        this.segmentSizeBytes = segmentSizeBytes;
        this.sieveSize = sieveSize;
    }

    public int[] initialSievePrimes() {
        int upperBound = (int) Math.floor(Math.sqrt(sieveSize)) + 1;
        boolean[] sieve = initialisedSieve(upperBound);
        sieve[0] = false;
        sieve[1] = false;
        for(int i=2; i*i<sieve.length; i++) {
            if(sieve[i]) {
                for(int j=i+i; j<sieve.length; j+=i) {
                    sieve[j] = false;
                }
            }
        }
        int primesFound = countPrimesInSieve(sieve);
        int[] primes = primesInSieve(sieve, primesFound, 0);
        return primes;
    }

    private int[] primesInSieve(boolean[] sieve, int n, int offset) {
        int[] primes = new int[n];
        int counter = 0;
        for(int i=0; i<sieve.length; i++) {
            if(sieve[i]) {
                primes[counter++] = i + offset;
            }
            if(counter >= n) break;
        }
        return primes;
    }

    private int countPrimesInSieve(boolean[] sieve) {
        int counter = 0;
        for(int i=0; i<sieve.length; i++) {
            if(sieve[i]) {
                counter++;
            }
        }
        return counter;
    }

    private boolean[] initialisedSieve(int n) {
        boolean[] sieve = new boolean[n];
        for(int i=0; i<sieve.length; i++) {
            sieve[i] = true;
        }
        return sieve;
    }

    private boolean foundEnoughInInitialPrimes(int n, int[] initialPrimes) {
        return initialPrimes.length > n;
    }

    private int addToFoundPrimes(int[] to, int[] from, int startIndex) {
        int counter = 0;
        for(int i=startIndex; i<from.length+startIndex; i++) {
            if(i + 1 > to.length) return startIndex + counter;
            to[i] = from[i-startIndex];
            counter += 1;
        }
        return startIndex + from.length;
    }

    private int getSmallestMultipleOfPrime(int start, int prime) {
        int smallestMultipleOfPrime = ((int) Math.floor(start/prime)) * prime;
        if(smallestMultipleOfPrime < start) smallestMultipleOfPrime += prime;
        return smallestMultipleOfPrime;
    }

    public int[] generateFixedNumberOfPrimes(int n) {
        int[] foundPrimes = new int[n];
        int[] initialPrimes = initialSievePrimes();
        if(foundEnoughInInitialPrimes(n, initialPrimes)) {
            return Arrays.copyOfRange(initialPrimes, 0, n);
        }
        int foundPrimeCount = addToFoundPrimes(foundPrimes, initialPrimes, 0);
        int start = (int) Math.floor(Math.sqrt(sieveSize)) + 1;
        int end = start + segmentSizeBytes;
        if(end >= sieveSize) end = sieveSize;
        while(start < sieveSize && foundPrimeCount < n) {
            boolean[] sieveSegment = initialisedSieve(end - start);
            for(int i=0; i<initialPrimes.length; i++) {
                int prime = initialPrimes[i];
                int smallestMultipleOfPrime = getSmallestMultipleOfPrime(start, prime);
                for(int j=smallestMultipleOfPrime; j<end; j+=prime) {
                    sieveSegment[j-start] = false;
                }
            }
            int sieveSegmentPrimeCount = countPrimesInSieve(sieveSegment);
            int[] sieveSegmentPrimes = primesInSieve(sieveSegment, sieveSegmentPrimeCount, start);
            foundPrimeCount = addToFoundPrimes(foundPrimes, sieveSegmentPrimes, foundPrimeCount);
            start  = start + segmentSizeBytes;
            end = start + segmentSizeBytes;
        }
        if(foundPrimeCount < n) {
            throw new SieveTooSmallException("The size of the sieve is too small to provide [" + n + "] primes. Try increasing the size of the sieve");
        }
        return foundPrimes;
    }
}
