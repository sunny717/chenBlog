/**
 * 
 */
package com.pxb.base.app.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pxb.base.app.util.Constants;
import com.pxb.base.core.util.CollectionUtil;

/**
 * @ClassName: IndexController
 * @Description: 博客展示主页面
 * @author panxiaobo
 * @date 2017年12月7日 下午3:57:52
 * 
 */
@Controller
@RequestMapping("/pages")
public class IndexController {

	@RequestMapping("index")
	public String index(ModelMap map, HttpServletRequest req) {

		Integer page = req.getParameter("page") == null ? 0 : (Integer.parseInt(req.getParameter("page")) - 1);
		Integer size = Constants.SIZE;
//		Pageable pages = new PageRequest(page == null ? 0 : page, size, new Sort(Sort.Direction.DESC, "id"));
//
//		Page<Content> linksPage = contentRepository.findAll(pages);
//		List<Content> contents = linksPage.getContent();
//		List<Metas> cs = getCategoryMetas();
//
//		String basepath = req.getContextPath();
//		for (Content c : contents) {
//			log.info("转换后的分类字符串------" + show_categories(req, c.getCategories()));
//			c.setCategories(show_categories(req, c.getCategories()));
//			c.setThumb_img(gen_thumb(c));
//		}
		List contents=CollectionUtil.newArrayList();
		map.put("totals", 1);
		map.put("page", page + 1);
		map.put("articles", contents);
		map.put("icons", Constants.ICONS);
		// map.put("footas",getRecentArticles());

		return this.render("index");
	}

	private String render(String viewName) {
		return new StringBuilder().append("pages/").append(viewName).toString();
	}

}
