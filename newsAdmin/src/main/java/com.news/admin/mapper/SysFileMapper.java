package com.news.admin.mapper;


import com.news.admin.pojo.SysFile;
import com.news.platform.mybatis.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface SysFileMapper {
    int removeById(Integer id);

    int insert(SysFile record);
	
    int insertSelective(SysFile record);

    SysFile getFileById(Integer id);

    int updateByPrimaryKeySelective(SysFile record);

    int updateById(SysFile record);
    
    List<SysFile> listByPara(HashMap<Object, Object> para);
}