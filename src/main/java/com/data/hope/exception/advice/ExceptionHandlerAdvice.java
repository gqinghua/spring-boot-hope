package com.data.hope.exception.advice;

import com.data.hope.bean.ResponseBean;
import com.data.hope.exception.BusinessException;
import com.data.hope.exception.ReportBusinessException;
import com.data.hope.i18.MessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static com.data.hope.code.ResponseCode.FAIL_CODE;


/**
 * 全局异常处理
 * @author guoqinghua
 * @since 2021-01-02
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @Autowired
    private MessageSourceHolder messageSourceHolder;

    /**
     * 业务异常
     * @param businessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseBean handleBusinessException(BusinessException businessException) {
        return ResponseBean.builder().
                code(businessException.getCode()).
                message(businessException.getMessage()).
                args(businessException.getArgs()).build();
    }



    /**
     * 参数校验异常
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String code = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
        String message;
        try {
            message = messageSourceHolder.getMessage(code, null);
        } catch (NoSuchMessageException exception) {
            message = code;
        }
        return ResponseBean.builder().code(FAIL_CODE).message(message).build();
    }

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseBean exception(Exception exception){
        //返回值构建
        logger.error("系统异常", exception);
        ResponseBean.Builder builder = ResponseBean.builder();
        builder.code(FAIL_CODE);
        builder.message(messageSourceHolder.getMessage(FAIL_CODE, null));
        builder.args(new String[]{exception.getMessage()});
        return builder.build();
    }

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseBean Nullexception(NullPointerException exception){
        //返回值构建
        logger.error("数据不存在或数据异常", exception);
        ResponseBean.Builder builder = ResponseBean.builder();
        builder.code(FAIL_CODE);
        builder.message("数据不存在或数据异常");
        builder.args(new String[]{exception.getMessage()});
        return builder.build();
    }
    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public ResponseBean SQLException(SQLException exception){
        //返回值构建
        logger.error("sql不规范或sql执行异常", exception);
        ResponseBean.Builder builder = ResponseBean.builder();
        builder.code(FAIL_CODE);
        builder.message("sql不规范或sql执行异常");
        builder.args(new String[]{exception.getMessage()});
        return builder.build();
    }


    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(ReportBusinessException.class)
    public ResponseBean handleBusinessException(ReportBusinessException exception){
        //返回值构建
        logger.error("sql不规范或sql执行异常", exception);
        ResponseBean.Builder builder = ResponseBean.builder();
        builder.code(FAIL_CODE);
        builder.message( exception.getMessage());
        builder.args(new String[]{exception.getMessage()});
        return builder.build();
    }
}
