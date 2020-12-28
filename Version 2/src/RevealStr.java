import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * RevealStr containing RevealStr method.
 * Iteratively finds and replaces * in a given
 * string by using 2d arrays.
 *
 * @author Vaansh Lakhwara
 * @version 1.0
 */
public class RevealStr {
    /**
     * Main method.
     * Makes this class the driver class.
     *
     * @param args command line argument
     */
    public static void main(String[] args) {
        String temp;    //random variable that is generated
        PrintWriter pw = null;  //to write into out.txt
        double startTime, endTime, elapsed; //used to calculate time taken

        try {
            pw = new PrintWriter(new FileOutputStream("out.txt"));
            for (int i = 2; i <= 10; i += 2) {
                startTime = System.currentTimeMillis();
                temp = RandomStr(i);
                System.out.println(temp);
                RevealStr.RevealStr(temp);
                endTime = System.currentTimeMillis();
                elapsed = endTime - startTime;
                pw.println("Tested for " + i + " number of masks (*)...");
                pw.println("Time taken (in milliseconds): " + elapsed);
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("There was an error in creating out.txt, please try again...");
            System.out.println(e.getMessage());
        }

        pw.close(); //close printwriter
    }

    /**
     * RevealStr method.
     * Method that iteratively finds and replaces *
     * in a given string.
     *
     * @param str String to perform the actions on
     */
    public static void RevealStr(String str) {
        int n = 0;

        //for loop to find number of * in string
        for (int a = 0; a < str.length(); a++) {
            if (str.charAt(a) == '*') {
                n += 1;
            }
        }

        int[] indexes = new int[n];
        int ind = 0;

        //for loop to store indexes of string with * in array named indexes.
        for (int a = 0; a < str.length(); a++) {
            if (str.charAt(a) == '*') {
                indexes[ind] = a;
                ind++;
            }
        }

        int t = 0;
        int p = 0;
        int i = t;
        int power = 0;
        String[][] twod = new String[n][(int) Math.pow(2, n)];

        //2d array that fills in 0s exponentially, depending on the length of input string using three nested loops
        for (int j = 0; j < n; j++) {
            while (i < (int) Math.pow(2, n)) {
                for (i = t; i < (int) Math.pow(2, power) + t && i < (int) Math.pow(2, n); i++) {
                    twod[j][i] = "0";
                    p += 1;
                }
                t = 2 * p;
            }
            t = 0;
            p = 0;
            i = t;
            power += 1;
        }

        //fills in the rest of the 2d array with 1s
        for (int e = 0; e < n; e++) {
            for (int f = 0; f < (int) Math.pow(2, n); f++) {
                if (twod[e][f] == null) {
                    twod[e][f] = "1";
                }
            }
        }

        String store = "";
        String[] binary = new String[(int) Math.pow(2, n)];

        //converts the 2d array into 1d array by arranging them row-wise
        for (int w = 0; w < (int) Math.pow(2, n); w++) {
            for (int v = 0; v < n; v++) {
                store += twod[v][w];
            }
            binary[w] = store;
            store = "";
        }


        String output = str;
        System.out.println("Randomly generated string: " + output);
        System.out.println("Replacing all * in the string...");

        //prints the output of the 1d array in accordance to the input string
        for (int y = 0; y < binary.length; y++) {
            output = str;
            for (int z = 0; z < indexes.length; z++) {
                output = output.substring(0, indexes[z]) + binary[y].charAt(z) + output.substring(indexes[z] + 1, output.length());
            }
            System.out.println(output);
        }
        System.out.println("Process Completed!" + "\n");
    }

    /**
     * RandomStr method.
     * Creates and fills a string of length between 10 and
     * 100 with 1s and 0s, then places *s.
     *
     * @param stars number of * to place in created string
     * @return randomStr randomly generated string
     * containing 1s, 0s and randomly placed *s
     */
    public static String RandomStr(int stars) {
        String randomStr = "";
        Random random = new Random();
        int min = 10, max = 101;
        //generates random integer between 10 and 100, inclusive which is the length of the string
        int len = random.nextInt(max - min) + min;

        //fills us the string with 0s and 1s using a for loop
        for (int n = 0; n < len; n++) {
            int minBinary = 0, maxBinary = 2;
            int val = random.nextInt(maxBinary - minBinary) + minBinary;

            randomStr += String.valueOf(val);
        }

        //randomly places *s using a for loop
        for (int n = 0; n < stars; n++) {
            int minReplace = 0, maxBReplace = len;
            int val = random.nextInt(maxBReplace - minReplace) + minReplace;
            randomStr = randomStr.substring(0, val) + "*" + randomStr.substring(val, len + n);
        }

        //returning string with random length
        return randomStr;
    }
}