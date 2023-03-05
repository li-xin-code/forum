package com.lixin.campusforum.config;

import com.lixin.campusforum.common.constant.enums.ResultCodeEnums;
import com.lixin.campusforum.common.exception.AuthenticationException;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.exception.TokenInvalidException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lixin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 论坛系统异常处理
     *
     * @param e exception
     * @return ...
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ForumSystemException.class)
    public NoDataResult systemExceptionHandler(ForumSystemException e) {
        logger.error(e.getMessage(), e);
        return ResultUtils.fail(e.getMessage());
    }

    /**
     * 身份认证异常处理
     *
     * @param e exception
     * @return ...
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public NoDataResult authenticationExceptionHandler(AuthenticationException e) {
        logger.error(e.getMessage(), e);
        return ResultUtils.fail(ResultCodeEnums.AUTHENTICATION, e.getMessage());
    }

    /**
     * 参数效验异常处理器
     *
     * @param e ...
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResult<List<String>> parameterExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errorList = Optional.of(e.getBindingResult())
                .map(BindingResult::getAllErrors)
                .orElse(Collections.emptyList());
        List<String> errorMsg = errorList.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResultUtils.fail(ResultCodeEnums.ARGUMENT_NOT_VALID, "格式错误", errorMsg);
    }


    /**
     * 令牌无效异常处理
     *
     * @param e exception
     * @return ...
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenInvalidException.class)
    public NoDataResult invalid(TokenInvalidException e) {
        return ResultUtils.fail(ResultCodeEnums.TOKEN_INVALID, e.getMessage());
    }

    /**
     * 兜底异常处理
     *
     * @param e exception
     * @return ...
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NoDataResult exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultUtils.fail(ResultCodeEnums.ERROR, e.getMessage());
    }

}
