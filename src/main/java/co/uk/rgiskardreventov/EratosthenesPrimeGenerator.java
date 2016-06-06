package co.uk.rgiskardreventov;

/**
 * Created by justin on 6/6/16.
 */
public class EratosthenesPrimeGenerator implements PrimeGenerator {

    private int sieveSize;

    public EratosthenesPrimeGenerator(int sieveSize) {
        this.sieveSize = sieveSize;
    }

    public int[] generateFixedNumberOfPrimes(int n) {
        boolean[] sieve = initialiseSieve();
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
        if(primesFound >= n) {
            return primesInSieve(sieve, n);
        }
        throw new SieveToSmallException("The size of the sieve is too small to provide [" + n + "] primes. Try increasing the size of the sieve");
    }

    private int[] primesInSieve(boolean[] sieve, int n) {
        int[] primes = new int[n];
        int counter = 0;
        for(int i=0; i<sieve.length; i++) {
            if(sieve[i]) {
                primes[counter++] = i;
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

    private boolean[] initialiseSieve() {
        boolean[] sieve = new boolean[sieveSize];
        for(int i=0; i<sieve.length; i++) {
            sieve[i] = true;
        }
        return sieve;
    }
}
