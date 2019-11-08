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
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Igor Nunes a.k.a. thoga31
 */
public class WordNumeral {
    private static final boolean INITSCALE = false;
    
    private BigDecimal number;
    private ArrayList<Integer> parts;
    private Locale locale;
    private boolean smallscale;
    private String words;
    
    private void separateNumber() {
        this.parts.clear();
        BigDecimal[] division = new BigDecimal[2];
        division[0] = (new BigDecimal(0)).add(this.number);
        division[1] = new BigDecimal(0);
        final BigDecimal thousand = new BigDecimal(1000);
        final BigDecimal zero = new BigDecimal(0);
        while (!division[0].equals(zero)) {
            division = division[0].divideAndRemainder(thousand);
            parts.add(division[1].intValue());
        }
    }
    
    private String numberToWords() throws Exception {
        String s = "";
        if (this.parts.size() > 5) throw new Exception("Too big of a number **for now**.");
        for (int i = this.parts.size() - 1; i >= 0; i--) {
            if (this.parts.get(i) > 0) {
                Thousand t = (new Thousand(this.parts.get(i), i, this.smallscale)).setLocale(this.locale).apply();
                s += String.format("%s ", t.getWord());
            }
        }
        return s.substring(0, s.length()-1);
    }
    
    public WordNumeral apply() throws Exception {
        this.separateNumber();
        this.words = this.numberToWords();
        return this;
    }
    
    public BigDecimal getNumber() {
        return this.number;
    }
    
    public void setNumber(BigDecimal number) {
        this.number = number;
    }
    
    public void setNumber(String number) {
        this.number = new BigDecimal(number);
    }
    
    public WordNumeral withNumber(BigDecimal number) {
        this.setNumber(number);
        return this;
    }
    
    public WordNumeral withNumber(String number) {
        this.setNumber(number);
        return this;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public WordNumeral withLocale(Locale locale) {
        this.setLocale(locale);
        return this;
    }
    
    public void setSmallScale(boolean smallscale) {
        this.smallscale = smallscale;
    }
    
    public WordNumeral withSmallScale() {
        this.smallscale = true;
        return this;
    }
    
    public WordNumeral withoutSmallScale() {
        this.smallscale = false;
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s", this.number, this.words);
    }
    
    public WordNumeral() {
        this.number = new BigDecimal(0);
        this.locale = Locale.EN;
        this.words = "";
        this.smallscale = INITSCALE;
        this.parts = new ArrayList<>();
    }
    
    public WordNumeral(String number) {
        this.number = new BigDecimal(number);
        this.locale = Locale.EN;
        this.words = "";
        this.smallscale = INITSCALE;
        this.parts = new ArrayList<>();
    }
    
    public WordNumeral(BigDecimal number) {
        this.number = number;
        this.locale = Locale.EN;
        this.words = "";
        this.smallscale = INITSCALE;
        this.parts = new ArrayList<>();
    }
}
