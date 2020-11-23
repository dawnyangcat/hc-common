package pers.dawnyang.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.dawnyang.common.domain.PageReq;
import pers.dawnyang.common.domain.PageRes;

/**
 *
 * @author dawn 2020年7月24日 下午3:22:17
 *
 */

public class PageTools {

	public static <T> Page<T> getPage(PageReq<T> pageReq) {
		Page<T> page = new Page<>();
		page.setCurrent(pageReq.getCurrent());
		page.setSize(pageReq.getSize());
		return page;
	}

	public static <T> PageRes<T> getPageRes(IPage<T> iPage) {
		PageRes<T> pageRes = new PageRes<>();
		pageRes.setRecords(iPage.getRecords());
		pageRes.setTotal(iPage.getTotal());
		return pageRes;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
