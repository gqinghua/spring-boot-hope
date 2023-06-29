package com.data.hope;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.data.hope.cache.CacheHelper;
import com.data.hope.cache.RedisCacheHelper;
import com.data.hope.event.listener.ExceptionApplicationListener;
import com.data.hope.holder.UserContentHolder;
import com.data.hope.holder.UserContext;
import com.data.hope.i18.MessageLocaleResolver;
import com.data.hope.i18.MessageSourceHolder;
import com.data.hope.init.InitRequestUrlMappings;
import com.data.hope.intercept.AccessKeyInterceptor;
import com.data.hope.utils.ApplicationContextUtils;
import com.data.hope.utils.JwtBean;
import com.data.hope.bean.ResponseBean;
import com.data.hope.config.MybatisPlusMetaObjectHandler;
import com.data.hope.curd.mapper.injected.CustomSqlInjector;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.jeasy.rules.api.RuleListener;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 自动装配核心类配合resource下的配置文集
 *
 * @author guoqinghua
 * @since 2021-01-11
 */
@Configuration
@EnableConfigurationProperties(ReportProperties.class)
@ComponentScan(value = "com.data.hope")
public class ReportAutoConfiguration {
    public ReportAutoConfiguration() {
    }

    /**
     * spring上下文工具类
     *
     * @return
     */
    @Bean
    public ApplicationContextUtils applicationContextUtils() {
        return new ApplicationContextUtils();
    }
    @Bean
    public InitRequestUrlMappings InitRequestUrlMappings() {
        return new InitRequestUrlMappings();
    }

    /**
     * jwt实例
     *
     * @param reportProperties
     * @return
     */
    @Bean
    public JwtBean jwtBean(ReportProperties reportProperties) {
        return new JwtBean(reportProperties);
    }

    @Bean
    @ConditionalOnClass({RedisAutoConfiguration.class})
    @ConditionalOnMissingBean
    public CacheHelper cacheHelper() {
        return new RedisCacheHelper();
    }


    /**
     * web服务器环境,网关不加载
     */
    @Configuration
    @ConditionalOnClass({WebMvcConfigurer.class})
    @ComponentScan(value = {"com.data.hope.curd.controller", "com.data.hope.exception.advice"})
    public static class WebReportAutoConfiguration {
        public WebReportAutoConfiguration() {
        }

        @Bean
        public InitRequestUrlMappings initRequestUrlMappings() {
            return new InitRequestUrlMappings();
        }

        /**
         * 解析token用户名
         *
         * @return
         */
        @Bean
        public FilterRegistrationBean registrationBean(JwtBean jwtBean) {
            FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter((request, response, chain) -> {
                if (request instanceof HttpServletRequest) {
                    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
                    String authorization = httpServletRequest.getHeader("Authorization");
                    String orgCode = httpServletRequest.getHeader("orgCode");
                    String sysCode = httpServletRequest.getHeader("systemCode");
                    String locale = httpServletRequest.getHeader("locale");
                    UserContext userContext = UserContentHolder.getContext();
                    if (StringUtils.isNotBlank(locale)) {
                        userContext.setLocale(Locale.forLanguageTag(locale));
                    }

                    if (StringUtils.isNotBlank(authorization)) {
                        try {
                            //记得修改
                            String authToken = StringUtils.substring(authorization, 7);
                            String username = jwtBean.getUsername(authToken);
                            Integer userType = jwtBean.getUserType(authToken);
                            String tenant = jwtBean.getTenant(authToken);
//                            String username = jwtBean.getUsername(authorization);
//                            Integer userType = jwtBean.getUserType(authorization);
//                            String tenant = jwtBean.getTenant(authorization);
                            userContext.setUsername(username);
                            userContext.setType(userType);
                            userContext.setTenantCode(tenant);
                            MDC.put("loginName", username);
                            if (StringUtils.isNotBlank(orgCode)) {
                                userContext.setOrgCode(orgCode);
                            }

                            if (StringUtils.isNotBlank(sysCode)) {
                                userContext.setSysCode(sysCode);
                            }

                            userContext.getParams().put("orgCode", orgCode);
                        } catch (Exception var13) {
                            ResponseBean responseBean = ResponseBean.builder().code("500").message("The Token has expired").build();
                            response.getWriter().print(JSONObject.toJSONString(responseBean));
                            return;
                        }
                    }
                }

                chain.doFilter(request, response);
                UserContentHolder.clearContext();
            });
            registrationBean.addUrlPatterns(new String[]{"/*"});
            registrationBean.setName("userOrgCodeFilter");
            registrationBean.setOrder(-2147483548);
            return registrationBean;
        }

        @Configuration
        @ConditionalOnClass({LocaleResolver.class})
        @ConditionalOnMissingBean({MessageLocaleResolver.class})
        public class MessageI18AutoConfiguration {
            public MessageI18AutoConfiguration() {
            }

            @Bean
            public MessageLocaleResolver localeResolver() {
                return new MessageLocaleResolver();
            }

            /**
             * 国际化
             *
             * @return
             */
            @Bean
            public MessageSourceHolder messageSourceHolder() {
                return new MessageSourceHolder();
            }
        }
    }

    /**
     * 异常监听
     *
     * @return
     */
    @Bean
    public ExceptionApplicationListener exceptionApplicationListener() {
        return new ExceptionApplicationListener();
    }

    /**
     * Web配置
     */
    @Configuration
    @ConditionalOnClass(WebMvcConfigurer.class)
    public static class ReportWebMvcConfigurer implements WebMvcConfigurer {

        /**
         * 拦截器
         *
         * @param registry
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AccessKeyInterceptor());
            interceptorRegistration.addPathPatterns("/**");
        }
    }

    /**
     * 持久层mybatis-plus自动装配
     *
     * @author guoqinghua
     * @since 2021-01-01
     */
    @Configuration
    @ConditionalOnClass(MybatisPlusAutoConfiguration.class)
    public class ReportMybatisPlusAutoConfiguration {

        public ReportMybatisPlusAutoConfiguration() {
        }
        /**
         * 乐观锁，需要在version字段上加@Version
         *
         * @return
         */
//        @Bean
//        public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//            return new OptimisticLockerInterceptor();
//        }

        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

            return interceptor;
        }
        /**
         * 自定义 SqlInjector
         * 里面包含自定义的全局方法
         */
//        @Bean
//        public MyLogicSqlInjector myLogicSqlInjector() {
//            return new MyLogicSqlInjector();
//        }
//        @Bean
//        public IdentifierGenerator idGenerator() {
//            return new CustomIdGenerator();
//        }
            /**
             * 填充sql
             *
             * @return
             */
        @Bean
        public CustomSqlInjector customSqlInjector() {
            return new CustomSqlInjector();
        }

        /**
         * 分页
         *
         * @return
         */
//        @Bean
//        public PaginationInterceptor paginationInterceptor() {
//            return new PaginationInterceptor();
//        }
        /**
         * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
         */
//        @Bean
//        public MybatisPlusInterceptor mybatisPlusInterceptor() {
//            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
//            return interceptor;
//        }

//        @Bean
//        public ConfigurationCustomizer configurationCustomizer() {
//            return configuration -> configuration.setUseDeprecatedExecutor(false);
//        }

        /**
         * 默认填充
         *
         * @return
         */
        @Bean
        @ConditionalOnMissingBean(value = {MetaObjectHandler.class})
        public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler() {
            return new MybatisPlusMetaObjectHandler();
        }
    }



    @Bean
    @ConditionalOnMissingBean
    public RuleListener defaultRulesListener() {
        return new DefaultRulesListener();
    }


    @Bean
    @ConditionalOnMissingBean(RulesEngine.class)
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RulesEngine rulesEngine(RuleListener defaultRulesListener) {
        RulesEngineParameters parameters = new RulesEngineParameters();
        DefaultRulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        rulesEngine.registerRuleListener(defaultRulesListener);
        return rulesEngine;
    }
    private class ResponseResultInterceptor implements HandlerInterceptor {
    }

    private class DefaultRulesListener implements RuleListener {
    }


    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
