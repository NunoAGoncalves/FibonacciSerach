public class FibonacciSearch {

    /**
     * The FibonacciNumber method returns the smallest Fibonacci number greater than or equal to the parameter
     * number. It also includes the two preceding fibonacci numbers (fibMinus1 and fibMinus2) creating an int array.
     * @param number
     * @return
     */
    public static int[] FibonacciNumber(int number) {
        int fibM = 1, fibM2 = 0, fibM1 = 1;

        // Adds the two previous fibonacci numbers iteratively until it finds one that is greater than or equal to number
        while (fibM < number + 1) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM1 + fibM2;
        }

        int[] currentFibonacciNumberPlusPreceding = new int[]{fibM, fibM1, fibM2};
        return currentFibonacciNumberPlusPreceding;
    }

    /**
     * The FibonacciSearch method is the main bulk of the program. It searches a sorted array by dividing the array into
     * unequal subarray and discarding those subarrays according. The method is similar to binary search in that it uses a divide and conquer strategy.
     * @param array
     * @param x
     * @return
     */
    public static int FibonacciSearch(int[] array, int x) {

        int[] currentFibonacciNumberPlusPreceding = FibonacciNumber(array.length);

        int fibM = currentFibonacciNumberPlusPreceding[0];
        int fibM1 = currentFibonacciNumberPlusPreceding[1];
        int fibM2 = currentFibonacciNumberPlusPreceding[2];

        int offset = -1;

        while (fibM > 1) {
            // Math.min returns the smallest of the two integers. Allows us to check whether offset + fibM2 is a valid location
            int i = Math.min(offset + fibM2, array.length - 1);

            // If array[i] is greater than x, discard all elements
           if (array[i] > x) {
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            }
            else if (array[i] < x) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            }
            else {
               return i + 1;
           }
        }

        if (fibM1 == 1 && array[offset + 1] == x) {
            return fibM1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 10, 11, 12};
        System.out.println(FibonacciSearch(sortedArray, 4));
    }
}
