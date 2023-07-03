package com.msg.prep.utils;

import java.util.regex.Pattern;

public class MessageUtils {

	public static boolean isNotNullorEmptyString(String str) {
		if (str == null || str.isEmpty() || str.trim().isEmpty()) {
			return false;

		}
		return true;

	}

	public static boolean isNumeric(String string) {
		String regex = "[0-9]+[\\.]?[0-9]*";
		return Pattern.matches(regex, string);
	}

	public static boolean isAlphaNumeric(String string) {
		String regex = "^[a-zA-Z0-9]*$";
		return Pattern.matches(regex, string);
	}

	public static boolean isAlphaNumericSpecial(String string) {
		String regexSpecialCharacters = "^[a-zA-Z0-9]*[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]*[a-zA-Z0-9]*[\\s]*[a-zA-Z]*";

		return Pattern.matches(regexSpecialCharacters, string);
	}
}
