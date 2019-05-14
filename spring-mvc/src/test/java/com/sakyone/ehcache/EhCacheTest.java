package com.sakyone.ehcache;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhCacheTest {
    private static final Logger log = LoggerFactory.getLogger(EhCacheTest.class);
    @Test
    public void test(){
        //1、获取到XML文件位置的URL
        URL myUrl = getClass().getResource("/ehcache.xml");
        //2、实例化一个XmlConfiguration，将XML文件URL传递给它
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        //3、使用静态的org.ehcache.config.builders.CacheManagerBuilder.newCacheManager(org.ehcache.config.Configuration)
        //使用XmlConfiguration的Configuration创建你的CacheManager实例。
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String, String> foo = myCacheManager.getCache("foo",String.class,String.class);
        Cache<Number, String> bar = myCacheManager.getCache("bar",Number.class,String.class);
        Cache<Long, String> simpleCache = myCacheManager.getCache("simpleCache",Long.class,String.class);
        foo.put("1","zhangsan");
        foo.put("2","lisi");
        log.info("foo,姓名：{}",foo.get("1"));

        bar.put(1.1,"zhangsan");
        bar.put(2.2,"lisi");
        log.info("bar,姓名：{}",bar.get(1.1));

        simpleCache.put(1L,"zhangsan");
        simpleCache.put(2L,"lisi");
        log.info("simpleCache,姓名：{}",simpleCache.get(1L));

        myCacheManager.close();
    }

}
