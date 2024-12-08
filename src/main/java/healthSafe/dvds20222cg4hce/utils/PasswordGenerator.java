package healthSafe.dvds20222cg4hce.utils;

import java.util.Random;

public final class PasswordGenerator {
	
	private final static String MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String NUMBERS = "0123456789";
	private final static String MINUS = "abcdefghijklmnopqrstuvwxyz";
	private final static String ESPECIALS = "_-.&%@";
	private final static Integer LENGTH_PASSWORD = 12;
	private final static Integer LENGTH_CODE = 5;
	private final static  Random RND = new Random();
	
	private PasswordGenerator(){}
	
	public static String passwordGenerator() {
		
		StringBuilder allChars = new StringBuilder();
		allChars.append(MAYUS);
		allChars.append(MINUS);
		allChars.append(NUMBERS);
		allChars.append(ESPECIALS);
		
	    StringBuilder sb = new StringBuilder(LENGTH_PASSWORD);
	    
	    for (int i = 0; i < LENGTH_PASSWORD; i++) {
	        sb.append(allChars.charAt(RND.nextInt(allChars.length())));
	    }
	    return sb.toString();
	}
	
	public static String codigoVerificacion() {
		StringBuilder chars = new StringBuilder();
		chars.append(MAYUS);
		chars.append(NUMBERS);
		
	    StringBuilder sb = new StringBuilder(LENGTH_CODE);
	    
	    for (int i = 0; i < LENGTH_CODE; i++) {
	        sb.append(chars.charAt(RND.nextInt(chars.length())));
	    }
	    return sb.toString();
	}
}
