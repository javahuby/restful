package com.test.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GServiceimpl implements GService{
	
	@Autowired
	private GDao dao;
	
	GServiceimpl() {
		System.out.println("===> GServiceimpl 확인");
	}

	@Override
	public void insert(GVo vo) {
		dao.insert(vo);
	}

	@Override
	public void update(GVo vo) {
		dao.update(vo);
	}

	@Override
	public void delete(GVo vo) {
		dao.delete(vo);
	}

	@Override
	public List<GVo> list(GVo vo) {
		if (vo.getCh2() != null) {
			if ( !vo.getCh2().substring(0).equals("'")) {
				String ch2 = '%' + vo.getCh2() + '%';
				vo.setCh2(ch2);	
			}else {
				String ch2 = vo.getCh2() ;
				vo.setCh2(ch2);	
			}
        }
		return dao.list(vo);
}

	@Override
	public int totalCount(GVo vo) {
		return dao.totalCount(vo);
	}

	@Override
	public GVo edit(GVo vo) {
		return dao.edit(vo);
	}
}