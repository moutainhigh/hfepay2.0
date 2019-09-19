/*
 * Powered By [华付通]
 * Web Site: http://www.huaepay.com/
 * Since 2016 - 2016
 */package com.hfepay.scancode.commons.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hfepay.commons.base.annotation.Generated;
import com.hfepay.commons.dao.impl.MybatisEntityDAO;
import com.hfepay.scancode.commons.dao.ChannelBaseDAO;
import com.hfepay.scancode.commons.entity.ChannelBase;

@Repository(value="channelBaseDAO")
@Generated("2016-10-13 15:19:03")
public class ChannelBaseDAOImpl extends MybatisEntityDAO<ChannelBase, String> implements ChannelBaseDAO {

	/**
	 * @Title: updateStatus
	 * @Description: 状态更新
	 * @author: Ricky
	 * @param id
	 * @return: long
	 * @date CreateDate : 2016-10-13 15:19:03
	 */
	@Override
	public long updateStatus(String id,String status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", status);
		return getSqlSession().update(sqlId("updateStatus"),map);
	}

	@Override
	public List selectList(Map params) {
		return getSqlSession().selectList("selectList", params);
	}	
}