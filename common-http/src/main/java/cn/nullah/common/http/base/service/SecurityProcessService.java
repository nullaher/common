package cn.nullah.common.http.base.service;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.nullah.common.http.api.SecurityContext;
import cn.nullah.common.http.base.exception.SecurityProcessException;

@Service
public class SecurityProcessService {

	Logger logger = LoggerFactory.getLogger(getClass());

	Md5PasswordEncoder md5Encoder=new Md5PasswordEncoder();

	public void process(@Valid SecurityContext context) {
		Integer strategyCode = context.getEncryptStrategy();
		if (SecurityContext.SIGN_MD5_ENCRYPT_NO.equals(strategyCode))
			return;
		int encryptType = strategyCode%10;
		if (encryptType == 1) {
		} else if (encryptType == 0) {
			logger.debug("不加密");
		} else {
			throw new SecurityProcessException("不支持的加密类型");
		}
		int strategyType = strategyCode/10;
		if (strategyType == 1) {
//			JSONObject jsonData = (JSONObject) JSONObject.toJSON(context);
		} else {
			throw new SecurityProcessException("不支持的验签方式");
		}
		logger.info("认证成功");
	}
}
