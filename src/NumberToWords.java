/*
 * Copyright (C) 2019 Igor Nunes a.k.a. thoga31
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package numbertowords;

import java.io.*;
import numbertowords.NumberLocale.Locale;

/**
 *
 * @author Igor Nunes a.k.a. thoga31
 */
public class NumberToWords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // testThousand();
        testNumber();
    }
    
    private static void testThousand() {
        try {
            int n;
            do {
                System.out.println("Assuming n * 10^9");
                System.out.printf("n? ");
                n = Integer.valueOf(((new BufferedReader(new InputStreamReader(System.in))).readLine()).trim()).intValue();
                Thousand t1 = (new Thousand(n, 3, true )).setLocale(Locale.EN).apply();
                Thousand t2 = (new Thousand(n, 3, true )).setLocale(Locale.PT).apply();
                Thousand t3 = (new Thousand(n, 3, false)).setLocale(Locale.PT).apply();
                System.out.printf("Small scale, EN: %s\n", t1);
                System.out.printf("Small scale, PT: %s\n", t2);
                System.out.printf("  Big scale, PT: %s\n", t3);
                System.out.printf("\n");
            } while (n > 0);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private static void testNumber() {
        try {
            String n;
            do {
                System.out.println("Testing (almost) any number:");
                System.out.printf("n? ");
                n = ((new BufferedReader(new InputStreamReader(System.in))).readLine()).trim();
                System.out.printf("%s -> \n", n);
                WordNumeral w = (new WordNumeral(n)).apply();
                System.out.printf("%s\n", w);
            } while (!n.equals("0"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
