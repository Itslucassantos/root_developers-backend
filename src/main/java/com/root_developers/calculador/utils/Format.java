package com.root_developers.calculador.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Format {

    public static double formatar(double valor) {
        Locale localeUs = new Locale("en", "US");
        DecimalFormat decimalFormat = new DecimalFormat(".##", new DecimalFormatSymbols(localeUs));
        return Double.parseDouble(decimalFormat.format(valor));
    }

}
