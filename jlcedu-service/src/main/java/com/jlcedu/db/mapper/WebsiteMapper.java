package com.jlcedu.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jlcedu.entity.Banner;
import com.jlcedu.entity.Item;
import com.jlcedu.entity.Navigation;
import com.jlcedu.entity.Menu;

@Repository
public interface WebsiteMapper {
	public List<Banner> selectBanners();
	public List<Navigation> selectNavigations(@Param("client_type")String clientType);
	public List<Menu> selectMenus();

	public Navigation selectNavigationById(@Param("id")Integer id);
	public List<Integer> selectMenuIdsByNavigationId(@Param("navigation_id")Integer navigationId);
	public List<Item> selectItemsByNavigationId(@Param("navigation_id")Integer navigationId);

	public Navigation selectNavigationsByMenuId(@Param("menu_id")Integer menuId);
	public List<Integer> selectMenuIdsByMenuId(@Param("id")Integer id);
	public List<Item> selectItemsByMenuId(@Param("menu_id")Integer menuId);

	public Navigation selectNavigationByItemId(@Param("item_id")String itemId);
	public List<Integer> selectMenuIdsByItemId(@Param("item_id")String itemId);
	public Item selectItemById(@Param("id")String id);

}
