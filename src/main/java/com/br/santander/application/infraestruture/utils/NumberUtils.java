package com.br.santander.application.infraestruture.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {
	
	public static String formatCurrencyNumber(BigDecimal value ) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return nf.format(value);
	}

}
