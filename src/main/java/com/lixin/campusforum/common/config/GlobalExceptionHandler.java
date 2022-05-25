package com.lixin.campusforum.common.config;

import com.lixin.campusforum.common.enums.ResultCode;
import com.lixin.campusforum.common.exception.AuthenticationException;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.exception.TokenInvalidException;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.utils.ResultUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Iterator;
import java.util.List;

/**
 * @author lixin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SystemException.class)
    public NoDataResult systemExceptionHandler(SystemException e) {
        e.printStackTrace();
        return ResultUtils.fail(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NoDataResult authenticationExceptionHandler(AuthenticationException e) {
        e.printStackTrace();
        return ResultUtils.fail(ResultCode.AUTHENTICATION, e.getMessage());
    }

    /**
     * 参数效验异常处理器
     *
     * @param e ...
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NoDataResult parameterExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        e.printStackTrace();
        if (!exceptions.hasErrors()) {
            return ResultUtils.fail(ResultCode.ARGUMENT_NOT_VALID, e.getMessage());
        }
        List<ObjectError> errors = exceptions.getAllErrors();
        StringBuilder builder = new StringBuilder();
        Iterator<ObjectError> iterator = errors.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().getDefaultMessage());
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        return ResultUtils.fail(ResultCode.ARGUMENT_NOT_VALID, builder.toString());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenInvalidException.class)
    public NoDataResult invalid(TokenInvalidException e) {
        return ResultUtils.fail(ResultCode.TOKEN_INVALID, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NoDataResult exception(Exception e) {
        e.printStackTrace();
        return ResultUtils.fail(ResultCode.ERROR, e.getMessage());
    }

}
