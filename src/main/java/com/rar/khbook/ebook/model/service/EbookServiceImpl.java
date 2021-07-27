package com.rar.khbook.ebook.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rar.khbook.ebook.model.dao.EbookDao;
import com.rar.khbook.ebook.model.vo.EbookDatabind;

@Service
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookDao dao;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertBookDataIntoDatabase(Map param) {
		int result = dao.insertBookDataIntoDatabase(session, param);
		return result;
	}
	
	@Override
	public List<EbookDatabind> search(Map param) {
		List<EbookDatabind> list = dao.search(session, param);
		return list;
	}
	
}
