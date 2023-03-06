package com.lixin.campusforum.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 支持蛇形命名方式的ServletModelAttributeMethodProcessor
 *
 * @author lixin
 */
@Slf4j
public class SupportSnakeCaseProcessor extends ServletModelAttributeMethodProcessor {

    public SupportSnakeCaseProcessor(final boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        Assert.state(servletRequest != null, "No ServletRequest");
        ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        new SupportSnakeCaseBinder(servletBinder.getTarget()).bind(servletRequest);
    }

    /**
     * 支持蛇形命名方式的servlet请求数据绑定器
     */
    private static class SupportSnakeCaseBinder extends ServletRequestDataBinder {

        private final Pattern underLinePattern = Pattern.compile("_(\\w)");

        public SupportSnakeCaseBinder(final Object target) {
            super(target);
        }

        /**
         * 添加绑定,在原有绑定关系基础上增加，下划线风格绑定。
         * <p>
         * example: [{simple_value,key}]->[{simpleValue,key},{simple_value,key}]
         *
         * @param propertyValues ...
         * @param request        ...
         */
        @Override
        protected void addBindValues(MutablePropertyValues propertyValues, ServletRequest request) {
            // 来自请求的参数列表
            List<PropertyValue> pvs = propertyValues.getPropertyValueList();
            List<PropertyValue> list = pvs.stream()
                    .filter(propertyValue -> {
                        String name = propertyValue.getName();
                        return !name.equals(this.underLineToCamel(name));
                    })
                    .map(propertyValue ->
                            new PropertyValue(this.underLineToCamel(propertyValue.getName()), propertyValue.getValue()))
                    .collect(Collectors.toList());
            if (!list.isEmpty()) {
                log.info("new bindings: {}", list.stream()
                        .map(p -> String.format("{\"%s\",\"%s\"}", p.getName(), p.getValue()))
                        .collect(Collectors.toList()));
                pvs.addAll(list);
            }
        }

        /**
         * 下划线转驼峰方法(如: 把app_id转换成appId)
         *
         * @param value 要转换的下划线字符串
         * @return 驼峰体字符串
         */
        private String underLineToCamel(final String value) {
            final StringBuffer sb = new StringBuffer();
            Matcher m = this.underLinePattern.matcher(value);
            while (m.find()) {
                m.appendReplacement(sb, m.group(1).toUpperCase());
            }
            m.appendTail(sb);
            return sb.toString();
        }

    }
}


