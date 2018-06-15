package com.mrbt.mvc.dao;

import java.util.List;

import com.mrbt.mvc.model.FundsBulletin;

public interface FundsBulletinMapper {
    
    List<FundsBulletin> queryNewestOne();
    
}