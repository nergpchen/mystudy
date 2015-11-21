package org.babyfishdemo.spring.dal.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Set;

import javax.sql.DataSource;

import org.babyfish.collection.LinkedHashSet;
import org.babyfish.collection.MACollections;

/**
 * @author Tao Chen
 */
public class SqlAwareUtil {

    public static DataSource wrap(final DataSource dataSource) {
        if (dataSource instanceof SqlAware) {
            return dataSource;
        }
        return (DataSource)Proxy.newProxyInstance(
                SqlAware.class.getClassLoader(), 
                getWrapperInterfaces(dataSource), 
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object retval = method.invoke(dataSource, args);
                        if (retval instanceof Connection) {
                            return wrap((Connection)retval);
                        }
                        return retval;
                    }
                }
        );
    }
    
    public static Connection wrap(final Connection con) {
        if (con instanceof SqlAware) {
            return con;
        }
        return (Connection)Proxy.newProxyInstance(
                SqlAware.class.getClassLoader(), 
                getWrapperInterfaces(con), 
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("prepareStatement") && 
                                args.length != 0 && 
                                args[0] instanceof String) {
                            SqlRecorder.prepare((String)args[0]);
                        }
                        return method.invoke(con, args);
                    }
                }
        );
    }
    
    private static Class<?>[] getWrapperInterfaces(Object o) {
        Set<Class<?>> set = new LinkedHashSet<>();
        for (Class<?> type = o.getClass(); type != Object.class; type = type.getSuperclass()) {
            set.addAll(MACollections.wrap(type.getInterfaces()));
        }
        set.add(SqlAware.class);
        return set.toArray(new Class[set.size()]);
    }
    
    private interface SqlAware {}
}
