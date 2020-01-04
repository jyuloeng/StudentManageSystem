package util;



public class StringUtil {
	//�ж��Ƿ�Ϊ��
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
	//�ж��Ƿ�Ϊ������
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
	//�ж��Ƿ�Ϊ������
	public static boolean isNum(String str) {
		for(int i=0;i<str.length();i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
