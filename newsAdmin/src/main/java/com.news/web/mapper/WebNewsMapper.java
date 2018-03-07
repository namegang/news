package com.news.web.mapper;

import com.news.platform.mybatis.MyBatisRepository;
import com.news.web.pojo.Comment;
import com.news.web.pojo.WebNews;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface WebNewsMapper {
    int removeById(Integer id);

    int insert(WebNews record);

    int insertSelective(WebNews record);

    WebNews getWebNewsById(Integer id);
    
    WebNews getWebNewsBySym(String sym);

    int updateByPrimaryKeySelective(WebNews record);

    int updateById(WebNews record);
    
    List<WebNews> listByPara(HashMap<Object, Object> para);

    int addClickCount(Integer id);

    int editStts(Integer id, Integer stts);

    void addComont(Comment comment);
}