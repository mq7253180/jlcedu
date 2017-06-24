package com.jlcedu.service;

import java.util.List;

import com.jlcedu.entity.Banner;
import com.jlcedu.entity.Navigation;

public interface WebsiteService {
	public List<Navigation> getNavigations(String clientType);
	public Navigation getNavigationById(Integer id);
	public Navigation getNavigationByMenuId(Integer menuId);
	public Navigation getNavigationByItemId(String itemId);
	public List<Banner> getBanners();
	
	public List<String> getSuperSlideImg();
}
