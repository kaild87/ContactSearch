package com.helpshift.Utils;

/**
 * @author Kailash Dalmia
 */
public final class ValidationUtils {

	public static boolean validateString(String name){
		return name.length() <= 50;
	}

}
