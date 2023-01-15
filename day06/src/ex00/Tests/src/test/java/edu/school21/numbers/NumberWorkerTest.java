package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 47, 53, 67})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 10, 100, 680})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-500, -301 -1, 0, 1})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void isSumCorrect(int number, int sum) {
        Assertions.assertEquals(numberWorker.digitsSum(number), sum);
    }

}