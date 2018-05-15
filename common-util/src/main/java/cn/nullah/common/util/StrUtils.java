/**
 * StrUtils.java Copyright ©2016 http://www.nullah.cn All Rights Reserved
 */
package cn.nullah.common.util;

/**
 * @author zxy@nullah.cn
 * @note ...
 */
public class StrUtils {
	
	
	/**
	 * 字母转换数字,统一按大写字母处理 例,a:1,27:AA
	 * @param cloNum
	 * @return
	 */
	public static int letterToNum(String letter){
		int str = 0;
		if(letter.matches("\\d*")){
			return Integer.valueOf(letter);
		}else{
			letter = letter.toUpperCase();// 转换大写
			for(int i = 0; i < letter.length(); i++){
				int v = letter.charAt(i) - 64;
				str = str * 26 + v;
			}
		}
		return str;
	}
	
	/**
	 * @note 简单十进制转换26进制
	 * @param n
	 * @return
	 */
	public static String num2System26(int n){
		String s = "";
		while(n > 0){
			int m = n % 26;
			if(m == 0) m = 26;
			s = (char) (m + 64) + s;
			n = (n - m) / 26;
		}
		return s;
	}
	
	public static String fmtJsonStr(String jsonStr){
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		for(int i = 0; i < jsonStr.length(); i++){
			char c = jsonStr.charAt(i);
			if(level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)){
				jsonForMatStr.append(getLevelStr(level));
			}
			switch(c){
				case '{' :
				case '[' :
					jsonForMatStr.append(c + "\n");
					level++;
					break;
				case ',' :
					jsonForMatStr.append(c + "\n");
					break;
				case '}' :
				case ']' :
					jsonForMatStr.append("\n");
					level--;
					jsonForMatStr.append(getLevelStr(level));
					jsonForMatStr.append(c);
					break;
				default :
					jsonForMatStr.append(c);
					break;
			}
		}
		
		return jsonForMatStr.toString();
		
	}
	
	private static String getLevelStr(int level){
		StringBuffer levelStr = new StringBuffer();
		for(int levelI = 0; levelI < level; levelI++){
			levelStr.append("\t");
		}
		return levelStr.toString();
	}
	
	final static char[] digits = {'Z' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'A' , 'B' ,
	    'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' , 'M' , 'N' , 'Y' , 'P' , 'Q' , 'R' , 'S' ,
	    'T' , 'U' , 'V' , 'W' , 'X' , 'O' , '0'};
	
	/**
	 * @note 进制转换
	 * @param n 10进制数字
	 * @return
	 */
	public static String num2System34(long n){
		return numStr2ScaleStr(n , 34);
	}
	
	public static Long system34toNum(String n){
		return scaleStr2num(n , 34);
	}
	
	
	/**
	 * @note 进制转换
	 * @param i 10进制数字
	 * @param radix x进制数字,最大36
	 * @return
	 */
	private static String numStr2ScaleStr(long i , int radix){
		if(radix < Character.MIN_RADIX || radix > digits.length) radix = 10;
		if(radix == 10) return Long.toString(i);
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		if(!negative){
			i = -i;
		}
		while(i <= -radix){
			buf[charPos--] = getDigits(radix)[(int) (-(i % radix))];
			i = i / radix;
		}
		buf[charPos] = getDigits(radix)[(int) (-i)];
		
		if(negative){
			buf[--charPos] = '-';
		}
		return new String(buf , charPos , (65 - charPos));
	}
	
	private static long scaleStr2num(String s , int radix) throws NumberFormatException{
		if(s == null){
			throw new NumberFormatException("null");
		}
		
		if(radix < Character.MIN_RADIX){
			throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
		}
		if(radix > Character.MAX_RADIX){
			throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
		}
		
		long result = 0;
		boolean negative = false;
		int i = 0 , len = s.length();
		long limit = -Long.MAX_VALUE;
		long multmin;
		int digit;
		
		if(len > 0){
			char firstChar = s.charAt(0);
			if(firstChar < '0'){ // Possible leading "+" or "-"
				if(firstChar == '-'){
					negative = true;
					limit = Long.MIN_VALUE;
				}else if(firstChar != '+') throw new NumberFormatException("For input string: \"" + s + "\"");
				
				if(len == 1) // Cannot have lone "+" or "-"
				    throw new NumberFormatException("For input string: \"" + s + "\"");
				i++;
			}
			multmin = limit / radix;
			while(i < len){
				// Accumulating negatively avoids surprises near MAX_VALUE
				char sc = s.charAt(i++);
				if(radix==34){
					if(sc == 'Z') sc = '0';
					else if(sc == 'Y') sc = 'O';
				}
				digit = Character.digit(sc , radix);
				if(digit < 0){
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				if(result < multmin){
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				result *= radix;
				if(result < limit + digit){
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				result -= digit;
			}
		}else{
			throw new NumberFormatException("For input string: \"" + s + "\"");
		}
		return negative ? result : -result;
	}
	
	static char[] getDigits(int radix){
		// if(radix == digits.length){
		return digits;
		// }else if(radix == digits.length - 2){
		// return ArrayUtils.removeElements(digits , new char[]{'0' , 'o'});
		// // return ArrayUtils.addAll(digits , digitsUpLatter);
		// }else if(radix == digits.length ){
		// return ArrayUtils.addAll(digits , digitsUpLatter);
		// }else{
		// return digits;
		// }
	}
	
	public static void main(String[] args){
		// int n = 796;
		// System.out.println(letterToNum(num2System26(n)));
		String phone = "2161206175035275";
		String bin34 = numStr2ScaleStr(Long.valueOf(phone) , 34);
		System.out.println(bin34);
		phone = String.valueOf(scaleStr2num(bin34 , 34));
		System.out.println(phone);
		// System.out.println(ConvertTo10(bin34));
	}
	
}
