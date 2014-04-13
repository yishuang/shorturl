package org.hycoder.shorturl;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	private static Map<Integer, String> numToCharMap = new HashMap<Integer, String>();
	private static int BASE = 62;
	private static Map<String, Integer> charToNumMap = new HashMap<String, Integer>();
	private static String WEBAPP_BASEURL = "http://localhost:8080/shorturl";
	
	static {
		// 0-9
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			numToCharMap.put(cnt, String.valueOf(cnt++));
		}
		// A-Z
		for (char i = 'A'; i <= 'Z'; i++) {
			numToCharMap.put(cnt++, String.valueOf(i));
		}
		// a-z
		for (char i = 'a'; i <= 'z'; i++) {
			numToCharMap.put(cnt++, String.valueOf(i));
		}
		cnt = 0;
		for (int i = 0; i < 10; i++) {
			charToNumMap.put(String.valueOf(i), Integer.valueOf(cnt++));
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			charToNumMap.put(String.valueOf(i), cnt++);
		}
		for (char i = 'a'; i <= 'z'; i++) {
			charToNumMap.put(String.valueOf(i), cnt++);
		}
	}
	
	public static String getHostname() {
		return WEBAPP_BASEURL;
	}

	public static void printMap() {
		for (Map.Entry<Integer, String> entry : numToCharMap.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
		System.out.println("-------------");
		for (Map.Entry<String, Integer> entry : charToNumMap.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
	}

	public static String shortURL(int id) {
		StringBuilder res = new StringBuilder();
		while (id > 0) {
			int digit = id % BASE;
			res.append(numToCharMap.get(digit));
			id /= BASE;
		}
		while (res.length() < 6)
			res.append('0');
		return res.reverse().toString();
	}

	public static int convertToId(String sURL) {
		int i = 0, len = sURL.length(), id = 0;
		while (i < len) {
			Integer num = charToNumMap.get(String.valueOf(sURL.charAt(i++)));
			if (num == null)
				return -1;
			id = id * BASE + num;
		}
		return id;
	}

	public static void main(String[] args) {
		// printMap();
		int id = 72;
		String tinyURL = shortURL(id);
		System.out.println("input Id: " + id);
		System.out.println("converted to shortURL: " + tinyURL);
		System.out.println("converted back to id: " + convertToId(tinyURL));

		String regex = "[0-9a-zA-Z]{6}";
		if ("0012u4".matches(regex)) {
			System.out.println("matched");
		}
	}

}
