package com.data.hope.intercept;

import com.data.hope.annotation.AccessKey;
import com.data.hope.code.ResponseCode;
import com.data.hope.exception.BusinessExceptionBuilder;
import com.data.hope.ReportUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * AccessKey拦截器  拦截，主要为了防止重复提交
 * @author guoqinghua
 * @since 2022-03-12
 **/
public class AccessKeyInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AccessKeyInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param request
     * @param httpServletResponse
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        // 获取注解
        if( !(handler instanceof HandlerMethod)){
            return true ;
        }
        AccessKey accessKey = ((HandlerMethod) handler).getMethodAnnotation(AccessKey.class);
        if (accessKey == null) {
            return true;
        }
        String id= request.getParameter(accessKey.key());

        //如果id为空,再获取类似xx/{xx}
        if(StringUtils.isBlank(id)){
            Map<String, Object> pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if(!CollectionUtils.isEmpty(pathVariables)){
                id = pathVariables.get(accessKey.key())+"";
            }
        }
        //如果参数ID值为空
        if(StringUtils.isBlank(id)){
            throw BusinessExceptionBuilder.build(ResponseCode.FAIL_CODE,"accessKey校验失败，缺少参数ID");
        }

        String passKey= request.getParameter("accessKey");
        if(StringUtils.isBlank(passKey)){
            throw BusinessExceptionBuilder.build(ResponseCode.FAIL_CODE,"accessKey校验失败，缺少参数accessKey");
        }

        String realPassKey = ReportUtils.getPassKey(Long.parseLong(id));
        if (!StringUtils.equals(passKey,realPassKey)) {
            throw BusinessExceptionBuilder.build(ResponseCode.FAIL_CODE,"accessKey校验失败，传入的accessKey参数值有误");
        }
        return true;
    }

}
