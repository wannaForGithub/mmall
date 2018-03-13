package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by wanna on 2018/3/12.
 */
public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    //Loadingcache()使用LRU算法,设置本地缓存
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)//设置缓存容量
            .maximumSize(10000)//设置缓存上限，当缓存数量超过上限时会使用LRU算法进行回收
            .expireAfterAccess(12, TimeUnit.HOURS) //若12小时之内缓存没有被读或者写则被回收
            .build(new CacheLoader<String, String>() {

                //默认的数据加载实现，当调用get取值的时间如果对应的key没有值，就调用这个方法进行加载，
                //当get()没有取到值时返回一个“null”
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);//该get()方法传入key，返回value
            if ("null".equals(value)){
                return null;
            }
            return value;
        } catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }
}
