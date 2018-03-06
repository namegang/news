package com.news.utils.comm;

import com.news.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;



public class PagePara {
	public PagePara(HttpServletRequest request) {
		Integer pageNumPara = RequestUtil.getInteger(request, "page");
		if (pageNumPara != null) {
			this.setPageNum(pageNumPara);
		}
		Integer pageSizePara = RequestUtil.getInteger(request, "rows");
		if (pageSizePara != null) {
			this.setPageSize(pageSizePara);
		}
		String sidx = RequestUtil.getString(request, "sidx");
		String sord = RequestUtil.getString(request, "sord");

		if (sidx!=null && !sidx.equals("")) {
			if (sord == null || sord.equals("")) {
				this.setOrderBy(sidx + " desc");
			} else {
				this.setOrderBy(sidx + " " + sord);
			}
		} else {
			this.setOrderBy("id desc");
		}

	}

	private int pageNum;
	private int pageSize;
	private String orderBy;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
