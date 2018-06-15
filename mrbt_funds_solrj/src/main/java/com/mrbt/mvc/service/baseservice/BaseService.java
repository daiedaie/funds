package com.mrbt.mvc.service.baseservice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface BaseService {
	public final static Logger log = LogManager.getLogger(BaseService.class);

	public void finish();

	public void rollback();

}
