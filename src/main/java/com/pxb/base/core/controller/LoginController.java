/**
 * 
 */
package com.pxb.base.core.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxb.base.core.service.UserService;
import com.pxb.base.core.util.ImageCodeUtil;

/**
 * @ClassName: LoginController
 * @Description: 登录
 * @author panxiaobo
 * @date 2017年12月5日 下午5:42:50
 * 
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(ModelMap map, HttpServletRequest request) {

		return "login";

	}

	@RequestMapping(value = { "/", "/index" })
	public String index() {

		return "redirect:/pages/index";

	}

	@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectUrl = "redirect:/login?error";
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
			if (userService.checkUserCanLogin(userName, password)) {
				request.getSession().setAttribute("isLogin", true);
				redirectUrl = "redirect:admin/index";
			}
		}
		return redirectUrl;
	}

	@RequestMapping(value = "/images/imagecode")
	public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		Map<String, Object> map = ImageCodeUtil.getImageCode(60, 20, os);

		String simpleCaptcha = "simpleCaptcha";
		request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
		request.getSession().setAttribute("codeTime", new Date().getTime());

		try {
			ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
		} catch (IOException e) {
			return "";
		}
		return null;
	}

	@RequestMapping(value = "/checkcode")
	@ResponseBody
	public String checkcode(HttpServletRequest request, HttpSession session) throws Exception {
		String checkCode = request.getParameter("checkCode");
		Object cko = session.getAttribute("simpleCaptcha"); // 验证码对象
		if (cko == null) {
			request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
			return "验证码已失效，请重新输入！";
		}

		String captcha = cko.toString();
		Date now = new Date();
		Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
		if (StringUtils.isEmpty(checkCode) || captcha == null || !(checkCode.equalsIgnoreCase(captcha))) {
			request.setAttribute("errorMsg", "验证码错误！");
			return "验证码错误！";
		} else if ((now.getTime() - codeTime) / 1000 / 60 > 5) {// 验证码有效时长为5分钟
			request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
			return "验证码已失效，请重新输入！";
		} else {
			session.removeAttribute("simpleCaptcha");
			return "1";
		}
	}

}
