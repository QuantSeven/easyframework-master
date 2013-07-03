package com.easyframework.web.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.easyframework.web.security.exception.IncorrectCaptchaException;
import com.easyframework.web.security.token.CaptchaUsernamePasswordToken;
import com.google.code.kaptcha.Constants;

/**
 * 代码说明： 添加 captchaParam 变量，为的是页面表单提交验证码的参数名可以进行灵活配置。 doCaptchaValidate 方法中，验证码校验使用了框架 KAPTCHA 所提供的 API。
 * 
 * 扩展 FormAuthenticationFilter 类，首先覆盖 createToken 方法，以便获取 CaptchaUsernamePasswordToken 实例； 
 * 然后增加验证码校验方法 doCaptchaValidate； 最后覆盖 Shiro 的认证方法 executeLogin，在原表单认证逻辑处理之前进行验证码校验。
 * 
 * @author QuantSeven
 * 
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	// 创建 Token
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
	}

	// 验证码校验
	protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {

		String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
	}

	// 认证
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);

		try {
			//doCaptchaValidate((HttpServletRequest) request, token);

			Subject subject = getSubject(request, response);
			subject.login(token);

			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

}
