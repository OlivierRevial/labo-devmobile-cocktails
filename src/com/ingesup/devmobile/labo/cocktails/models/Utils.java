package com.ingesup.devmobile.labo.cocktails.models;

import android.annotation.SuppressLint;

public class Utils {
	@SuppressLint("DefaultLocale")
	public static String getNormalizedString(String string) {
		return string.toLowerCase().replace(" ", "_").replace("'", "_").replace("-", "_").replace("�", "a").replace("�", "e").replace("�", "e");
	}
}
