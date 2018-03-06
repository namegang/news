package com.news.web.mapper;

import java.util.HashMap;
import java.util.List;

import com.news.platform.mybatis.MyBatisRepository;
import com.news.web.pojo.WebFriendLink;

@MyBatisRepository
public interface WebFriendLinkMapper {
    int removeById(Integer id);

    int insert(WebFriendLink record);

    int insertSelective(WebFriendLink record);

    WebFriendLink getWebFriendLinkById(Integer id);
    
    WebFriendLink getWebFriendLinkBySym(String sym);

    int updateByPrimaryKeySelective(WebFriendLink record);

    int updateById(WebFriendLink record);
    
    List<WebFriendLink> listByPara(HashMap<Object, Object> para);
}