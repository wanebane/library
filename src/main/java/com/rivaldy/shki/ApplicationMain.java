package com.rivaldy.shki;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

public class ApplicationMain {
	
	private static volatile SecureRandom numberGenerator = null;
	private static final long MSB = 0x8000000000000000L;
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

//	public static void main(String[] args) {
//		String angka = "123456789012345";
//		System.out.println("hasilnya : "+unique(angka));
//		System.out.println("lengtj : "+unique(angka).length());
//		System.out.println("hasil lagi : "+toSlug("Base64.getDecoder().decode(enkrip.getBytes())".replace(".", " ")));
//		
//		String uni = unique(angka);
//		String enkrip = Base64.getEncoder().encodeToString(uni.getBytes());
//		System.out.println("enkrip : "+enkrip);
//		System.out.println("enkrip length : "+enkrip.length());
//		
//		byte[] dekrip = Base64.getDecoder().decode(enkrip.getBytes());
//		String decode = new String(dekrip);
//		System.out.println("dekrip : "+decode);
//		System.out.println("dekrip length : "+decode.length());
//		
//		String originalInput = "test input";
//		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//		
//		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//		String decodedString = new String(decodedBytes);
//		
//		System.out.println("enkrip baru : "+encodedString);
//		System.out.println("dekrip baru : "+decodedString);
//		
//		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println("hasil hhtpstatus : "+status);
//		
//		String testAlpha = "M29x";
//		Integer intAlpha = Integer.valueOf(testAlpha.replaceAll("[a-zA-Z]", ""));
//		System.out.println("hasil intAlpha : "+intAlpha);
//		
//	}
	
	public static String unique(String id) {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        return Long.toHexString(MSB | Long.valueOf(id) | ng.nextLong()) + Long.toHexString(MSB | Long.valueOf(id) | ng.nextLong());
    } 
	
	public static String toSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	}
}
