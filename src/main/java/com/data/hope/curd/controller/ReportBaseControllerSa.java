package com.data.hope.curd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.data.hope.curd.dto.BaseDTO;
import com.data.hope.curd.entity.BaseEntity;
import com.data.hope.ReportUtils;
import com.data.hope.annotation.log.ReportAuditLog;
import com.data.hope.bean.ResponseBean;
import com.data.hope.curd.params.PageParam;
import com.data.hope.curd.service.ReportBaseService;
import com.data.hope.utils.ReportBeanUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;


/**
 * 基础controller
 *
 * @author guoqinghua
 * @since 2022-03-01
 */
public abstract class ReportBaseControllerSa<P extends PageParam, T extends BaseEntity, D extends BaseDTO> extends ReportController {

    /**
     * 获取实际服务类
     *
     * @return
     */
    public abstract ReportBaseService<P, T> getService();

    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    public abstract T getEntity();

    /**
     * 获取当前Controller数据传输DTO
     *
     * @return
     */
    public abstract D getDTO();

    /**
     * 分页模板
     *
     * @param param
     * @return
     */
    @GetMapping("/pageList")
    @ReportAuditLog(pageTitle = "查询")
    @ApiOperation(value = "分页查询 维护人：郭清华")
    public ResponseBean pageList(P param) {
        IPage<T> iPage = getService().page(param);
        List<T> records = iPage.getRecords();

        //entity转换成DTO
        List<D> list = (List<D>) ReportBeanUtils.copyList(records, getDTO().getClass());

        //处理dto返回结果
        pageResultHandler(list);
        Page<D> pageDto = new Page<>();
        pageDto.setCurrent(iPage.getCurrent())
                .setRecords(list)
                .setPages(iPage.getPages())
                .setTotal(iPage.getTotal())
                .setSize(iPage.getSize());
        return responseSuccessWithData(pageDto);
    }

    /**
     * 对分页dto返回处理
     * @param list
     * @return
     */
    public List<D> pageResultHandler(List<D> list) {
        return list;
    }

    /**
     * 对详情返回DTO进行处理
     * @param detail
     * @return
     */
    public D detailResultHandler(D detail) {
        return detail;
    }


    /**
     * 根据ID查询相关记录
     *
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    @ApiOperation(value = "查询服务:根据ID查询 维护人：郭清华")
    public ResponseBean detail(Long id) {
        logger.info("{}根据ID查询服务开始，id为：{}", this.getClass().getSimpleName(), id);
        T result = getService().selectOne(id);

        D dto = getDTO();
        ReportBeanUtils.copyAndFormatter(result, dto);

        //对返回值建处理
        detailResultHandler(dto);
        ResponseBean responseBean = responseSuccessWithData(resultDtoHandle(dto));
        logger.info("{}根据ID查询结束，结果：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(responseBean));
        return responseBean;
    }

    /**
     * 对明细结果进行处理,子类可以覆盖
     * @param d
     * @return
     */
    protected D resultDtoHandle(D d) {
        return d;
    }

    /**
     * 插入
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ReportAuditLog(pageTitle = "新增")
    @ApiOperation(value = "新增数据服务 维护人：郭清华")
    @Transactional
    public ResponseBean insert(@Validated @RequestBody D dto) {
        logger.info("{}新增服务开始，参数：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(dto));

        ResponseBean responseBean = responseSuccess();
        T entity = getEntity();
        //dto转为数据库实体
        BeanUtils.copyProperties(dto, entity);
        //插入
        getService().insert(entity);

        responseBean.setData(entity);

        logger.info("{}新增服务结束，结果：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(responseBean));
        return responseBean;
    }


    /**
     * 根据ID修改对饮记录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    @ReportAuditLog(pageTitle = "修改")
    @ApiOperation(value = "更新服务:根据id更新数据 维护人：郭清华")
    @Transactional
    public ResponseBean update(@Validated @RequestBody D dto) {
//        String username = UserContentHolder.getContext().getUsername();

        ResponseBean responseBean = responseSuccess();

        logger.info("{}更新服务开始,更新人：{}，参数：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(dto));
        T entity = getEntity();
        //dto转换entity
        BeanUtils.copyProperties(dto, entity);

        getService().update(entity);
        responseBean.setData(entity);

        logger.info("{}更新服务结束，结果：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(entity));

        return responseSuccess();
    }


    /**
     * 根据ID删除指定记录,这里被删除的记录会进入删除记录表
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    @ReportAuditLog(pageTitle = "删除")
    @Transactional
    @ApiOperation(value = "删除服务:根据id删除数据 维护人：郭清华")
    public ResponseBean deleteById(Long id) {
        logger.info("{}删除服务开始，参数ID：{}", this.getClass().getSimpleName(), id);
        getService().deleteById(id);
        logger.info("{}删除服务结束", this.getClass().getSimpleName());
        return responseSuccess();
    }

    /**
     * 删除批量ID对应的记录
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    @ReportAuditLog(pageTitle = "批量删除")
    @ApiOperation(value = "批量删除服务:根据id批量删除数据 维护人：郭清华")
    @Transactional
    public ResponseBean deleteBatchIds(@RequestBody List<Serializable> ids) {
        logger.info("{}批量删除服务开始，批量参数Ids：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(ids));
        boolean deleteCount = getService().deleteByIds(ids);

        ResponseBean responseBean = responseSuccessWithData(deleteCount);

        logger.info("{}批量删除服务结束，结果：{}", this.getClass().getSimpleName(), ReportUtils.toJSONString(responseBean));
        return responseBean;
    }
}
