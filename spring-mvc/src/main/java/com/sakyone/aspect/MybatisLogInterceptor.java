package com.sakyone.aspect;

import com.sakyone.util.Logs;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * Mybatis 日志拦截器
 * 
 * 目的 ：1、拦截SQL并DEBUG日志输出，方便开发 2、记录SQL指标到CAT系统
 * 
 * @author Ternence
 * @date 2015年8月18日
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}), @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisLogInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisLogInterceptor.class);

    @SuppressWarnings("unused")
    private Properties properties;
    
    @Value("${sql.rows.max.return}")
    private Integer tooManyRows;

    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {


        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        long start = System.currentTimeMillis();


        returnValue = invocation.proceed();

        long end = System.currentTimeMillis();
        long time = (end - start);

        String sql = null;
        if (returnValue != null && returnValue instanceof List && ((List<Object>) returnValue).size() > tooManyRows) {
            sql = getSql(configuration, boundSql, sqlId, time);
            // 发现返回值过大的sql
            logger.warn("[structured][toomanyrows] return rows greater than " + tooManyRows + ",#sqlid:{}#,#rowCount:{}#,#sql:{}#", sqlId, ((List<Object>) returnValue).size(), sql);
        }


        if (time > 2000 || logger.isDebugEnabled()) {
            if (null == sql) {
                sql = getSql(configuration, boundSql, sqlId, time);
            }
            logger.debug(sql);

            if (time > 2000) {
                // 发现慢sql
                logger.warn("[structured][slowsql]Find a slow SQL,#sqlid:{}#,#i_duration:{}#,#sql:{}#", sqlId, time, sql);
            }
        }

        return returnValue;
    }

    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        try {
            String sql = showSql(configuration, boundSql);
            StringBuilder str = new StringBuilder(100);
            str.append(sqlId);
            str.append(" ==> ");
            str.append(sql);
            str.append(";  \r\n{executed in ");
            str.append(time);
            str.append(" msec}");
            return str.toString();
        } catch (Error e) {
            logger.error("解析 sql 异常", e);
        }
        return "";
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);

                StringBuilder sb = new StringBuilder();
                int index = 0;
                for (int i = 0; i < sql.length(); i++) {
                    if (sql.charAt(i) != '?') {
                        sb.append(sql.charAt(i));
                    } else if (index < parameterMappings.size()) {
                        String propertyName = parameterMappings.get(index).getProperty();
                        if (metaObject.hasGetter(propertyName)) {
                            Object obj = metaObject.getValue(propertyName);
                            sb.append(Matcher.quoteReplacement(getParameterValue(obj)));
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            Object obj = boundSql.getAdditionalParameter(propertyName);
                            sb.append(Matcher.quoteReplacement(getParameterValue(obj)));
                        }
                        index++;
                    }
                }
                sql = sb.toString();
            }
        }
        return sql;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties0) {
        this.properties = properties0;
    }
}
