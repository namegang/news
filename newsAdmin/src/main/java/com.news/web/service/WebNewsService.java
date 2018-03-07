package com.news.web.service;

import com.news.web.mapper.WebNewsMapper;
import com.news.web.pojo.Comment;
import com.news.web.pojo.WebNews;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class WebNewsService {
	@Resource
	WebNewsMapper newsMapper;
	public int removeById(Integer id) {
		return newsMapper.removeById(id);
	}

	public int insert(WebNews record) {
		return newsMapper.insert(record);
	}

	public int insertSelective(WebNews record) {
		return newsMapper.insertSelective(record);
	}

	public WebNews getWebNewsById(Integer id) {
		return newsMapper.getWebNewsById(id);
	}
    
	public WebNews getWebNewsBySym(String sym) {
		return newsMapper.getWebNewsBySym(sym);
	}

	public int updateByPrimaryKeySelective(WebNews record)
	{
		return newsMapper.updateByPrimaryKeySelective(record);
	}

	public int updateById(WebNews record) {
		return newsMapper.updateById(record);
	}
    
	public List<WebNews> listByPara(HashMap<Object,Object> para){
		return newsMapper.listByPara(para);
	}

	public int addClickCount(Integer id) {
		return newsMapper.addClickCount(id);
	}

	public int editStts(Integer id, Integer stts) {
		return newsMapper.editStts(id, stts);
	}

	public void addComont(Comment comment) {
		newsMapper.addComont(comment);
	}

	public Comment getCommentById(Integer cmtId) {
		return newsMapper.getCommentById(cmtId);
	}

	public void delCommont(Integer cmtId) {
		newsMapper.delCommont(cmtId);
	}
}
