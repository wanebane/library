package com.rivaldy.shki.shared;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ApplicationFactory {
	
	private static volatile SecureRandom numberGenerator = null;
	private static final long MSB = 0x8000000000000000L;
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	
	public static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat FORMAT_DATETIME_TRIM = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
	
	private static ApplicationFactory instance = null;
	
	public static synchronized ApplicationFactory getInstance() {
		if(instance==null) instance = new ApplicationFactory();
		return instance;
	}
	
	public static String unique() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    } 
	
	public static String toSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input.trim()).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	}
	
	public String formatDate(String version, Date date) {
		String format = "";
		String versi = version.toLowerCase().trim();
		SimpleDateFormat sdf = null;
		if(versi == null) {
			sdf = FORMAT_DATE;
			format = sdf.format(date);
		} else if("full".equals(versi)) {
			sdf = FORMAT_DATETIME;
			format = sdf.format(date);
		} else if("trim".equals(versi)) {
			sdf = FORMAT_DATETIME_TRIM;
			format = sdf.format(date);
		}
		return format;
	}
	
	public Date strToDate(String strDate) {
		Date date = null;
		try {
			date = FORMAT_DATE.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String amountForDownload(Double amount) {
		DecimalFormat pattern = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setCurrencySymbol("");
		symbol.setMonetaryDecimalSeparator('.');
		symbol.setGroupingSeparator(',');
		pattern.setDecimalFormatSymbols(symbol);
		String result = "\t"+(pattern.format(amount)).replace(",", "");
		return result;
	}
	
	public static String formatAmount(Map<String, Object> map) {
		String result = "";
		String lang = ((String) map.get("lang")).toLowerCase().trim();
		String currency = ((String) map.get("currency")).toLowerCase().trim();
		double amount = (double) map.get("amount");
		char sepGroup = ',';
		char sepDec = '.';
		String fmtCur = "";
		if(lang!=null) {
			if("id".equals(lang)) {
				if("idr".equals(currency)) {
					sepGroup = '.';
					sepDec = ',';
					fmtCur = "Rp. ";
				} 
				else if("usd".equals(currency)) fmtCur = "$ ";
				else fmtCur = "";
				
			} else {
				if("idr".equals(currency)) fmtCur = "IDR ";
				else if("usd".equals(currency)) fmtCur = "USD ";
				else fmtCur = "";
			}
		}
		DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setCurrencySymbol(fmtCur);
		symbol.setMonetaryDecimalSeparator(sepDec);
		symbol.setGroupingSeparator(sepGroup);
		df.setDecimalFormatSymbols(symbol);
		result = df.format(amount);
		return result;
	}
}
