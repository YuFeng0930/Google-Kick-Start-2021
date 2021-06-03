
import java.io.*;

public class Goodness {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numOfTests = Integer.parseInt(br.readLine()); 
        int stringlen;
        int criteria;
        String[] temp;
        String target;
        for (int i = 0; i < numOfTests; i++) {
            temp = br.readLine().split(" ");
            stringlen = Integer.parseInt(temp[0]);
            criteria = Integer.parseInt(temp[1]);
            target = br.readLine(); 
            pw.println("Case #" + (i + 1) + ": " + process(stringlen, criteria, target));
        }
        pw.close();
    }

    public static int process(int stringlen, int criteria, String target) {
        int counter = 0;
        Character c1;
        Character c2;
        for (int i = 1; i <= stringlen / 2; i++) {
            c1 = target.charAt(i - 1);
            c2 = target.charAt(stringlen - i);
            if (!c1.equals(c2)) {
                counter++;
            }
        }
        if (criteria - counter <= 0) {
            return counter - criteria;
        }
        return criteria - counter;
    }
}

/**
3
5 1
ABCBA
4 2
ABAA
6 1
ABCABC

 */
