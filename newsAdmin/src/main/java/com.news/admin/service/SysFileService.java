package com.news.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SysFileService {
	@Resource
	SysFileMapper fileMapper;

	public int removeById(Integer id) {
		return fileMapper.removeById(id);
	}

	public int insert(SysFile record) {
		return fileMapper.insert(record);
	}
	public SysFile getFileById(Integer id) {
		return fileMapper.getFileById(id);
	}

	public int updateById(SysFile record) {
		return fileMapper.updateById(record);
	}

	public List<SysFile> listByPara(HashMap<Object, Object> para) {
		return fileMapper.listByPara(para);
	}
}
