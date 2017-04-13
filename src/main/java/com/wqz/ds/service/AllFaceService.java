package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.AllFace;

public interface AllFaceService
{
	Boolean insert(AllFace allFace);
	Boolean delete(Integer id);
	List<AllFace> select(Integer start,Integer size);
}
