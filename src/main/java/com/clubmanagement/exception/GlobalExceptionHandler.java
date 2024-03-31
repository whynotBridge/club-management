package com.clubmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理数据库唯一约束
     * @return
     */

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exceptionHandler(SQLIntegrityConstraintViolationException e){

        if(e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");

            StringBuffer buffer=new StringBuffer();
//            buffer.append("该活动主题").append(split[2]).append("已存在，请勿重复发布！");
            buffer.append(split[2]).append("已存在，请勿重复操作！");
            String errMessage = buffer.toString();
            //记录异常
            log.error("系统异常{}",e.getMessage(),errMessage);

            //解析出异常信息
            RestErrorResponse restErrorResponse=new RestErrorResponse(errMessage);
            return restErrorResponse;

        }

        RestErrorResponse restErrorResponse=new RestErrorResponse("未知错误");
        return restErrorResponse;
    }

    /**
     * 处理请求参数不合理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();
        //存储错误信息
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item->{
            errors.add(item.getDefaultMessage());
        });

        //将list中的错误信息拼接起来
        String errMessage = StringUtils.join(errors, ",");
        //记录异常
        log.error("系统异常{}",e.getMessage(),errMessage);

        //解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
        return restErrorResponse;
    }


}
