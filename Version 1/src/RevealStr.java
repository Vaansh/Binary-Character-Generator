import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * RevealStr containing RevealStr method.
 * Recursively finds and replaces * in a given
 * string by calling itself until all masks
 * are replaced.
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
            for (int i = 2; i <= 100; i += 2) {
                startTime = System.currentTimeMillis();
                temp = RandomStr(i);
                System.out.println("Given string: " + temp);
                System.out.println("Replacing all * for given string...");
                RevealStr(temp, temp.length());
                System.out.println();
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
     * Method that recursively finds and
     * replaces * in a given string.
     *
     * @param str String to perform the actions on
     */
    public static void RevealStr(String str, int len) {
        int ind = str.indexOf('*');
        int lind = str.lastIndexOf('*');

        //when the first and last indexes of * are same, the output is printed
        if (ind == lind) {
            String str1 = str.substring(0, ind);
            String str2 = str.substring(ind + 1, len);
            String op1 = str1 + "0" + str2;
            String op2 = str1 + "1" + str2;

            System.out.println(op1);
            System.out.println(op2);
        } else {
            String str1 = str.substring(0, ind);
            String str2 = str.substring(ind + 1, len);
            String op1 = str1 + "0" + str2;
            String op2 = str1 + "1" + str2;
            RevealStr(op1, len);
            RevealStr(op2, len);
        }
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