package com.test.guest;

import java.util.List;

public interface GService {
	void insert(GVo vo);
	void update(GVo vo);
	void delete(GVo vo);
	
	List<GVo> list(GVo vo);
	int totalCount(GVo vo);
	GVo edit(GVo vo);
}
