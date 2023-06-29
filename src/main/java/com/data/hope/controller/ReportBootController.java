package com.data.hope.controller;

import com.data.hope.init.InitRequestUrlMappings;
import com.data.hope.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通用请求
 * @author guoqinghua
 * @since 2022-03-01
 */
@RestController
public class ReportBootController {

    @Autowired
    private InitRequestUrlMappings initRequestUrlMappings;

    /**
     * 获取请求信息，用于微服务中获取其他模块的请求信息
     * @return
     */
    @GetMapping("/report/boot/requestInfos/{scanAnnotation}")
    public ResponseBean getRequestInfos(@PathVariable("scanAnnotation") Integer scanAnnotation) {
        List<InitRequestUrlMappings.RequestInfo> requestInfos = initRequestUrlMappings.getRequestInfos(scanAnnotation);
        return ResponseBean.builder().data(requestInfos).build();
    }
}
