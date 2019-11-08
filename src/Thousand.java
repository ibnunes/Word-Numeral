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

import numbertowords.NumberLocale.Locale;

/**
 *
 * @author Igor Nunes a.k.a. thoga31
 */
public class Thousand {
    final private static boolean INITSCALE = false;
    private int number;
    private String words;
    private int thousand;
    private boolean smallscale;
    private Locale locale;
    
    private String numberToWords() {
        String s = "";
        int n = this.number;
        
        if (n == 0)
            return NumberLocale.getUnit(n, this.locale);
        
        while (n != 0) {
            for (int h = 100; h > 0; h /= 10) {
                for (int d = 9; d > 0; d--) {
                    int div = h * d;
                    int quoc = (int) (n / div);
                    if (quoc > 0) {
                        switch (h) {
                            case 100:
                                s += String.format("%s ", NumberLocale.getHundred(d, this.locale));
                                break;
                            case 10:
                                if ((d != 1) || (n == 10))
                                    s += String.format("%s ", NumberLocale.getDozen(d, this.locale));
                                else {
                                    s += String.format("%s ", NumberLocale.getTen(n % 10, this.locale));
                                    div = n;
                                }
                                break;
                            case 1:
                                s += String.format("%s ", NumberLocale.getUnit(d, this.locale));
                                break;
                        }
                        n -= div;
                    }
                }
            }
        }
        
        return s.substring(0, s.length()-1);
    }
    
    public Thousand apply() {
        this.words =
                this.numberToWords() +
                (
                    (this.number == 0) ?
                        ("") :
                        (" " + NumberLocale.getThousand(this.thousand, this.smallscale, this.locale))
                );
        return this;
    }
    
    public Thousand setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }
    
    public Thousand setNumber(int number) throws Exception {
        checkNumber(number);
        this.number = number;
        return this;
    }
    
    public String getWord() {
        return this.words;
    }
    
    @Override
    public String toString() {
        return String.format("%d: %s", this.number, this.words);
    }
    
    public Thousand() {
        this.locale = Locale.EN;
        this.number = 0;
        this.thousand = 0;
        this.smallscale = INITSCALE;
    }
    
    public Thousand(int number) throws Exception {
        checkNumber(number);
        this.locale = Locale.EN;
        this.number = number;
        this.thousand = 0;
        this.smallscale = INITSCALE;
    }
    
    public Thousand(int number, int thousand, boolean smallscale) throws Exception {
        checkNumber(number);
        this.locale = Locale.EN;
        this.number = number;
        this.thousand = thousand;
        this.smallscale = smallscale;
    }
    
    private static void checkNumber(int number) throws Exception {
        if (number >= 1000) throw new Exception("Number equal or greater than 1000.");
        if (number < 0) throw new Exception("Number less than 0.");
    }
}
