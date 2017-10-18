package com.quincy.core.db;

import com.quincy.core.entity.Router;

public class ShardingRouterHolder {
	private static final ThreadLocal<Router> _router = new ThreadLocal<Router>();

    public static void set(Router router) {
    	_router.set(router);
    }
    public static Router get() {
		return _router.get();
	}
    public static void remove() {
    	_router.remove();
    }
}