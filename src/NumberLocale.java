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

/**
 *
 * @author Igor Nunes a.k.a. thoga31
 */
public class NumberLocale {
    static public enum Locale {
        EN, PT
    }
    
    final static private byte INDEX_EN = 0;
    final static private byte INDEX_PT = 1;
    
    static private byte LocaleToByte(Locale locale) {
        switch (locale) {
            case EN: return INDEX_EN;
            case PT: return INDEX_PT;
            default: return -1;
        }
    }
    
    final static private String[][] UNITS =
        {
            {"zero" , "zero"  },
            {"one"  , "um"    },
            {"two"  , "dois"  },
            {"three", "três"  },
            {"four" , "quatro"},
            {"five" , "cinco" },
            {"six"  , "seis"  },
            {"seven", "sete"  },
            {"eight", "oito"  },
            {"nine" , "nove"  }
        };
    
    final static private String[][] TENS =
        {
            {"eleven"   , "onze"     },
            {"twelve"   , "doze"     },
            {"thirteen" , "treze"    },
            {"fourteen" , "catorze"  },
            {"fifteen"  , "quinze"   },
            {"sixteen"  , "dezasseis"},
            {"seventeen", "dezassete"},
            {"eighteen" , "dezoito"  },
            {"nineteen" , "dezanove" }
        };
    
    final static private String[][] DOZENS =
        {
            {"ten"    , "dez"      },
            {"twenty" , "vinte"    },
            {"thirty" , "trinta"   },
            {"fourty" , "quarenta" },
            {"fifty"  , "cinquenta"},
            {"sixty"  , "sessenta" },
            {"seventy", "setenta"  },
            {"eighty" , "oitenta"  },
            {"ninety" , "noventa"  }
        };
    
    final static private String[][] HUNDREDS =
        {
            {"one hundred"  , "cem"         },
            {"two hundred"  , "duzentos"    },
            {"three hundred", "trezentos"   },
            {"four hundred" , "quatrocentos"},
            {"five hundred" , "quinhentos"  },
            {"six hundred"  , "seiscentos"  },
            {"seven hundred", "setecentos"  },
            {"eight hundred", "oitocentos"  },
            {"nine hundred" , "novecentos"  }
        };
    
    final static private String[][] THOUSANDS =
        {
            {""                , ""                },
            {"thousand"        , "mil"             },
            {"million"         , "milhão"          },
            {"thousand million", "milhar de milhão"},
            {"billion"         , "bilião"          },
            {"trillion"        , "trilião"         }            
        };
    
    static String getUnit(int unit, Locale locale) {
        return UNITS[unit][LocaleToByte(locale)];
    }
    
    static String getTen(int ten, Locale locale) {
        return TENS[ten-1][LocaleToByte(locale)];
    }
    
    static String getDozen(int dozen, Locale locale) {
        return DOZENS[dozen-1][LocaleToByte(locale)];
    }
    
    static String getHundred(int hundred, Locale locale) {
        return HUNDREDS[hundred-1][LocaleToByte(locale)];
    }
    
    static String getThousand(int thousand, Locale locale) {
        return getThousand(thousand, false, locale);
    }
    
    static String getThousand(int thousand, boolean smallscale, Locale locale) {
        if (!smallscale)
            return THOUSANDS[thousand][LocaleToByte(locale)];
        else
            return THOUSANDS[(thousand < 3) ? thousand : thousand+1][LocaleToByte(locale)];
    }
}
