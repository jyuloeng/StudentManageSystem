package util;



public class StringUtil {
	//ÅĞ¶ÏÊÇ·ñÎª¿Õ
	public static boolean isEmpty(String str) {
		if("".equals(str)||str==null) {
			return true;
		}
		return false;
	}
	public static boolean isEmpty(Integer str) {
		if("".equals(str)||str==null) {
			return true;
		}
		return false;
	}
	//ÅĞ¶ÏÊÇ·ñÎª´¿ºº×Ö
	public static boolean isHanzi(String str) {
		int n=0;
		for(int i=0;i<str.length();i++) {
			n=(int)str.charAt(i);
			if(!(19968<=n && n<40869)) {
				return false;
			}
		}
		return true;
	}
	//ÅĞ¶ÏÊÇ·ñÎª´¿Êı×Ö
	public static boolean isNum(String str) {
		for(int i=0;i<str.length();i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
