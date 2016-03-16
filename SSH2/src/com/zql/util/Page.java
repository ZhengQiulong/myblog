package com.zql.util;

import java.util.List;

public class Page<T> {
	private Integer pagsize=10;
	private Integer currentPage=1;
	private Integer number;
	private List<T> values;
	public Page(Integer pagsize,Integer currentPage,Integer number)
	{
		this.pagsize=pagsize;
		this.currentPage=currentPage;
		this.number=number;
	}
	
	public Page(Integer pagsize,Integer currentPage)
	{
		this.pagsize=pagsize;
		this.currentPage=currentPage;
	}
	
	public Integer getPagsize() {
		return pagsize;
	}
	public void setPagsize(Integer pagsize) {
		this.pagsize = pagsize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public List<T> getValues() {
		return values;
	}
	public void setValues(List<T> values) {
		this.values = values;
	}
	

}
