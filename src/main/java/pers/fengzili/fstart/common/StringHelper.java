package pers.fengzili.fstart.common;


/**
 *  字符串帮助类
 * @author fengzili
 * 2020-09-26 17:58:26
 */
public class StringHelper {

	public static int compareChars(String x, String y) {
		if (x == null || x.length() == 0) {
			if (y == null || y.length() == 0) {
				return 0;
			}
			return 1;
		}
		char[] xchars = x.toCharArray();
		char[] ychars = y.toCharArray();
		for(int i=0;i<Math.min(xchars.length, ychars.length);i++) {
			if(xchars[i]==ychars[i]) {
				continue;
			}
			else if(xchars[i]>ychars[i]) {
				return 1;
			}else {
				return -1;
			}
		}
		return xchars.length>ychars.length?1:0;
	}

}
