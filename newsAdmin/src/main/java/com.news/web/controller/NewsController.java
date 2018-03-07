package com.news.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.utils.ConfigProp;
import com.news.utils.FileUpload;
import com.news.utils.RequestUtil;
import com.news.utils.comm.PagePara;
import com.news.utils.comm.ResultInfo;
import com.news.web.pojo.Comment;
import com.news.web.pojo.WebNews;
import com.news.web.service.WebNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/webNews")
public class NewsController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private WebNewsService newsService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mView = new ModelAndView("/web/news/index");
		String sym = RequestUtil.getString(request, "sym");
		mView.addObject("sym", sym);
		return mView;
	}

	@RequestMapping(value = "/save")
	public ModelAndView save() {
		ModelAndView mView = new ModelAndView("/web/news/save");
		Integer id = RequestUtil.getInteger(request, "id");
		String sym = RequestUtil.getString(request, "sym");
		WebNews news = new WebNews();

		if (id != 0) {
			news = newsService.getWebNewsById(id);
		} else {
			news.setCreateDate(new Date());
			news.setPublishDate(new Date());
			news.setSym(sym);
		}
		mView.addObject("m", news);
		return mView;
	}

	@RequestMapping(value = "/saveBySym")
	public ModelAndView saveBySym() {
		ModelAndView mView = new ModelAndView("/web/news/saveBySym");
		String sym = RequestUtil.getString(request, "sym");

		WebNews news = newsService.getWebNewsBySym(sym);
		if (news == null) {
			news = new WebNews();
			news.setCreateDate(new Date());
			news.setPublishDate(new Date());
			news.setSym(sym);
		}
		mView.addObject("m", news);
		return mView;
	}

	/**
	 * Func 获取所有的列表数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listPara", method = RequestMethod.POST)
	public Map<String, Object> listPara() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取以及排序信息
		PagePara pagePara = new PagePara(request);
		PageHelper.startPage(pagePara.getPageNum(), pagePara.getPageSize());
		PageHelper.orderBy(pagePara.getOrderBy());
		// 紧跟着的第一个select方法会被分页
		RequestUtil.printParameters(request);
		HashMap<Object, Object> para = new HashMap<>();
		para.put("title", RequestUtil.getInteger(request, "title"));
		para.put("sym", RequestUtil.getString(request, "sym"));
		List<WebNews> list = newsService.listByPara(para);
		// 分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
		@SuppressWarnings("resource")
		Page<WebNews> listPage = (Page<WebNews>) list;
		map.put("records", listPage.getTotal());
		map.put("page", listPage.getPageNum());
		map.put("total", listPage.getPages());
		map.put("rows", list);
		return map;
	}

	/**
	 * Func 添加、编辑页面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultInfo save(WebNews news) {
		ResultInfo resultInfo = new ResultInfo();
		try {

			if (news.getId() != null) {
				newsService.updateById(news);
			} else {
				newsService.insert(news);
			}

		} catch (Exception ex) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg(ex.getMessage());
		}
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResultInfo uploadFile(@RequestParam("imgFile") CommonsMultipartFile file) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 转为流
			InputStream inputStream = file.getInputStream();
			// 获取上传的全名
			String originalFilename = file.getOriginalFilename();
			// 截取后4位
			String suffix = originalFilename.substring(originalFilename.length() - 4, originalFilename.length());

			String timepoint = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String dateFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());

			// HashMap<String, Object> result =
			// FtpUploadUtil.uploadFile("/file/image/"+dateFolder,
			// timepoint+suffix, inputStream);
			// 项目部署的路径
			String path = "";
			if (ConfigProp.getConfig("useFtp").equals("false")) {
				path = this.getClass().getClassLoader().getResource("/").getPath().replace("WEB-INF/classes/", "");
			}
			resultInfo = FileUpload.uploadFile(path, "file/image/" + dateFolder + "/", timepoint + suffix, inputStream);

		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResultInfo remove() {
		ResultInfo resultInfo = new ResultInfo(true, "删除成功");
		Integer id = RequestUtil.getInteger(request, "id");
		if (id != null) {
			try {
				newsService.removeById(id);
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg(e.getMessage());
			}
		}
		return resultInfo;
	}


	@ResponseBody
	@RequestMapping("/addClickCount")
	public ResultInfo addClickCount() {
		ResultInfo resultInfo = new ResultInfo(true, "添加成功");
		Integer id = RequestUtil.getInteger(request, "id");
		WebNews news = newsService.getWebNewsById(id);
		if(news != null){
			newsService.addClickCount(id);
		}
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping("/editStts")
	public ResultInfo editStts() {
		ResultInfo resultInfo = new ResultInfo(true, "修改成功");
		Integer id = RequestUtil.getInteger(request, "id");
		Integer stts = RequestUtil.getInteger(request, "stts");
		WebNews news = newsService.getWebNewsById(id);
		if(news != null && stts != null){
			newsService.editStts(id, stts);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("修改失败");
		}
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping("/addCommont")
	public ResultInfo addCommont(Comment comment) {
		ResultInfo resultInfo = new ResultInfo(true, "添加成功");
		// TODO 判断评论是否包含名字词汇
		boolean flag = true;
		comment.setCreateDate(new Date());
		if(flag){
			newsService.addComont(comment);
		} else {
			resultInfo.setMsg("评论包含铭感词汇");
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping("/delCommont")
	public ResultInfo delCommont() {
		ResultInfo resultInfo = new ResultInfo(true, "添加成功");
		Integer cmtId = RequestUtil.getInteger(request, "cmtId");
		Comment cmt = newsService.getCommentById(cmtId);
		if(cmt != null){
			newsService.delCommont(cmtId);
		} else {
			resultInfo.setMsg("不存在");
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}

}
