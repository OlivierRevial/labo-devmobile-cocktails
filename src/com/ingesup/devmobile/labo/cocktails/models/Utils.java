package com.ingesup.devmobile.labo.cocktails.models;

import android.annotation.SuppressLint;

public class Utils {
	@SuppressLint("DefaultLocale")
	public static String getNormalizedString(String string) {
		return string.toLowerCase().replaceAll(" ", "_").replaceAll("'", "_");
	}
}
