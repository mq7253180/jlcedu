package com.quincy.core.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.quincy.core.annotation.Sharding;
import com.quincy.core.entity.Router;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class ShardingInterceptor implements Interceptor {
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_OBJECT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_OBJECT_REFLECTOR_FACTORY);
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		String id = mappedStatement.getId();
		String className = id.substring(0, id.lastIndexOf("."));
		Class<?> classObj = Class.forName(className);
		Sharding annotation = classObj.getAnnotation(Sharding.class);
//		System.out.println(invocation.getTarget().getClass().getName());
		if(annotation!=null) {
//			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
//			Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
//			Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
			Router router = ShardingRouterHolder.get();
			String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
			String convertedSql = originalSql
				.replaceAll("t_s_package", router.getDb()+".t_s_package_"+router.getSuffix())
				.replaceAll("t_s_order", router.getDb()+".t_s_order_"+router.getSuffix())
				.replaceAll("t_s_order_set", router.getDb()+".t_s_order_set_"+router.getSuffix())
				.replaceAll("t_s_order_item", router.getDb()+".t_s_order_item_"+router.getSuffix());
			metaStatementHandler.setValue("delegate.boundSql.sql", convertedSql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
}