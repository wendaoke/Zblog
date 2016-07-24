package com.zblog.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserUtils {
	public enum Browser {
		IE9("MSIE 9.0", 1), IE8("MSIE 8.0", 2), IE7("MSIE 7.0", 3), IE6("MSIE 6.0", 4), MAXTHON("Maxthon", 5), QQ("QQBrowser", 6), GREEN("GreenBrowser", 7), SE360("360SE", 8), FIREFOX("Firefox", 9), OPERA(
				"Opera", 10), CHROME("Chrome", 11), SAFARI("Safari", 12), WEIXIN("MicroMessenger", 13), OTHER("Other", 14);
		private String name;
		private int index;

		private Browser(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public  String getName(int index) {
			for (Browser b : Browser.values()) {
				if (b.getIndex() == index) {
					return b.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public int getIndex() {
			return index;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}



	public static String checkBrowse(String userAgent) {
		for (Browser b : Browser.values()) {
			if (regex(b.getName(), userAgent)) {
				return b.getName();
			}
		}
		return Browser.OTHER.getName();
	}
	
	public static boolean checkWeixin(String userAgent){
		if(StringUtils.isBlank(userAgent)){
			return false;
		}
		if(userAgent.contains(Browser.WEIXIN.getName())){
			return true;
		}else{
			return false;
		}
	}

	public static boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	public static void main(String[] args){
		String browser = BrowserUtils.checkBrowse("Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
		System.out.println(browser);
	}
}
