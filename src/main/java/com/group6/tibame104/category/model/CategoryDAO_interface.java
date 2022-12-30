package com.group6.tibame104.category.model;

import java.util.List;

//import com.emp.model.EmpVO;

public interface CategoryDAO_interface {

	public List<CategoryVO> getbyProductName(String productName);
	
	public CategoryVO getbyProductID(Integer productID);

	public List<CategoryVO> getbyProductSecID(Integer productSecID);

	public List<CategoryVO> getbyProductMainID(Integer productMainID);

	public List<CategoryVO> getAll();

	public List<String> getAllCategory();
	
	public void updateStar(Integer productID);

}
