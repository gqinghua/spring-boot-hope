package com.data.hope.init;

import com.data.hope.annotation.Permission;
import com.data.hope.annotation.Permissions;
import com.data.hope.constant.Enabled;
import com.data.hope.controller.ReportBootController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 获取所有请求Url，供权限管理使用 自定义权限
 * @author guoqinghua
 * @since 2022-02-26
 */
public class InitRequestUrlMappings {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Value("${spring.application.name:}")
    private String applicationName;

    public InitRequestUrlMappings() {
    }

    public List<InitRequestUrlMappings.RequestInfo> getRequestInfos(Integer scanAnnotation) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();
        List<RequestInfo> list = new ArrayList();
        handlerMethods.entrySet().stream().forEach((entry) -> {
            List<Permission> annotations = new ArrayList();
            InitRequestUrlMappings.RequestInfo requestInfo = new InitRequestUrlMappings.RequestInfo();
            HandlerMethod value = (HandlerMethod)entry.getValue();
            if (value.getBeanType() != ReportBootController.class) {
                if (!Enabled.YES.getValue().equals(scanAnnotation)) {
                    requestInfo.setAuthCode(value.getBean() + "#" + value.getMethod().getName());
                } else {
                    Method method = value.getMethod();
                    Class<?> beanType = value.getBeanType();
                    if (!beanType.isAnnotationPresent(Permission.class) || !method.isAnnotationPresent(Permission.class) && !method.isAnnotationPresent(Permissions.class)) {
                        return;
                    }

                    Permission typeAnnotation = (Permission)beanType.getAnnotation(Permission.class);
                    Permission[] permissions = (Permission[])method.getAnnotationsByType(Permission.class);
                    Permission[] var11 = permissions;
                    int var12 = permissions.length;

                    for(int var13 = 0; var13 < var12; ++var13) {
                        Permission methodAnnotation = var11[var13];
                        if (StringUtils.isNotBlank(methodAnnotation.superCode())) {
                            requestInfo.setMenuCode(methodAnnotation.superCode());
                        } else {
                            requestInfo.setMenuCode(typeAnnotation.code());
                        }

                        requestInfo.setAuthCode(typeAnnotation.code() + ":" + methodAnnotation.code());
                        requestInfo.setAuthName(typeAnnotation.name() + methodAnnotation.name());
                        annotations.add(methodAnnotation);
                    }
                }

                requestInfo.setBeanName(value.getBean().toString());
                requestInfo.setApplicationName(this.applicationName);
                RequestMappingInfo requestMappingInfo = (RequestMappingInfo)entry.getKey();
                Optional<RequestMethod> requestMethodOptional = requestMappingInfo.getMethodsCondition().getMethods().stream().findFirst();
                if (requestMethodOptional.isPresent()) {
                    RequestMethod requestMethod = (RequestMethod)requestMethodOptional.get();
                    Optional<String> pathOptional = requestMappingInfo.getPatternsCondition().getPatterns().stream().findFirst();
                    if (pathOptional.isPresent()) {
                        String path = (String)pathOptional.get();
                        if (path.contains("{")) {
                            path = path.replaceAll("\\{\\w+\\}", "**");
                        }

                        requestInfo.setPath(requestMethod + "#" + path);
                        if (Enabled.YES.getValue().equals(scanAnnotation) && annotations.size() > 1) {
                            annotations.stream().forEach((annotation) -> {
                                InitRequestUrlMappings.RequestInfo requestInfoTmp = new InitRequestUrlMappings.RequestInfo();
                                BeanUtils.copyProperties(requestInfo, requestInfoTmp);
                                if (StringUtils.isNotBlank(annotation.superCode())) {
                                    requestInfoTmp.setMenuCode(annotation.superCode());
                                }

                                requestInfoTmp.setAuthCode(annotation.code());
                                requestInfoTmp.setAuthName(annotation.name());
                                list.add(requestInfoTmp);
                            });
                        } else {
                            list.add(requestInfo);
                        }

                    }
                }
            }
        });
        return list;
    }

    public static class RequestInfo {
        private String applicationName;
        private String beanName;
        private String menuCode;
        private String authCode;
        private String authName;
        private String path;

        public RequestInfo() {
        }

        public String getApplicationName() {
            return this.applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

        public String getAuthCode() {
            return this.authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getAuthName() {
            return this.authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getBeanName() {
            return this.beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public String getMenuCode() {
            return this.menuCode;
        }

        public void setMenuCode(String menuCode) {
            this.menuCode = menuCode;
        }
    }
}
