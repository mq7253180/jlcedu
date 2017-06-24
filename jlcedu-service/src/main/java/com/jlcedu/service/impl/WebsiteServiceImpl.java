package com.jlcedu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcedu.db.mapper.WebsiteMapper;
import com.jlcedu.entity.Banner;
import com.jlcedu.entity.Item;
import com.jlcedu.entity.Navigation;
import com.jlcedu.entity.Menu;
import com.jlcedu.service.WebsiteService;
import com.quincy.core.annotation.Cache;
import com.quincy.core.annotation.ReadOnly;

@Service
public class WebsiteServiceImpl implements WebsiteService {
	@Autowired
	private WebsiteMapper websiteMapper;

	@Cache(expire=20)
	@ReadOnly
	public List<Navigation> getNavigations(String clientType) {
		List<Navigation> navigations = websiteMapper.selectNavigations(clientType);
		List<Menu> allMenus = websiteMapper.selectMenus();
		for(Navigation navigation:navigations) {
			Integer id = navigation.getId();
			List<Integer> menuIds = new ArrayList<Integer>();
			for(Menu menu:allMenus) {
				if(menu.getNavigationId()==id) {
					menuIds.add(menu.getId());
				}
			}
			navigation.setMenuIds(menuIds);
		}
		return navigations;
	}

	@Cache(expire=20)
	@ReadOnly
	public Navigation getNavigationById(Integer id) {
		Navigation navigation = websiteMapper.selectNavigationById(id);
		List<Integer> menuIds = websiteMapper.selectMenuIdsByNavigationId(id);
		List<Item> items = websiteMapper.selectItemsByNavigationId(id);
		navigation.setMenuIds(menuIds);
		navigation.setItems(items);
		return navigation;
	}

	@Cache(expire=20)
	@ReadOnly
	public Navigation getNavigationByMenuId(Integer menuId) {
		Navigation navigation = websiteMapper.selectNavigationsByMenuId(menuId);
		List<Integer> menuIds = websiteMapper.selectMenuIdsByMenuId(menuId);
		List<Item> items = websiteMapper.selectItemsByMenuId(menuId);
		navigation.setMenuIds(menuIds);
		navigation.setItems(items);
		return navigation;
	}

	@Cache(expire=20)
	@ReadOnly
//	@Transactional(rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public Navigation getNavigationByItemId(String itemId) {
		Navigation navigation = websiteMapper.selectNavigationByItemId(itemId);
		List<Integer> menuIds = websiteMapper.selectMenuIdsByItemId(itemId);
		Item item = websiteMapper.selectItemById(itemId);
		navigation.setMenuIds(menuIds);
		navigation.setItem(item);
		return navigation;
	}

	@Cache(expire=20)
	@ReadOnly
	public List<Banner> getBanners() {
		return websiteMapper.selectBanners();
	}

	private static List<String> SUPER_SLIDE_IMG = new ArrayList<String>(6);
	static {
		SUPER_SLIDE_IMG.add("/upload/index/635507712060011250_1.jpg");
		SUPER_SLIDE_IMG.add("/upload/index/635507712329386250_2.jpg");
		SUPER_SLIDE_IMG.add("/upload/index/635507712491886250_3.jpg");
		SUPER_SLIDE_IMG.add("/upload/index/635507712630948750_4.jpg");
		SUPER_SLIDE_IMG.add("/upload/index/635507712762042500_5.jpg");
		SUPER_SLIDE_IMG.add("/upload/index/635507712898917500_6.jpg");
	}

	public List<String> getSuperSlideImg() {
		return SUPER_SLIDE_IMG;
	}

}
