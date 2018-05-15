package cn.nullah.validation;

import java.util.ResourceBundle;

public class CodeMsg {
	private static final String CODE_PROR_NAME = "code-msg";

	/** 参数验证 */

	public static ResourceBundle bundle = ResourceBundle.getBundle(CODE_PROR_NAME);

	public static String getCodeMsg(String code) {
		// prop =
		// PropertiesLoaderUtils.loadAllProperties(CODE_PROR_NAME+".properties");
		if (bundle.containsKey(code))
			return bundle.getString(code);
		return null;
	}
}
