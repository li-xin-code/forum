package com.lixin.campusforum.common.utils;

import com.github.pagehelper.PageInfo;
import com.lixin.campusforum.model.base.BasePageResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author lixin
 */
public final class ForumSystemUtils {

    private ForumSystemUtils() {
    }

    private final static String[] CHARS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成一个8位的62进制数
     * Generate an 8-bit Binary 62 number.
     *
     * @return uuid
     */
    public static String uuid() {
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int len = 8;
        for (int i = 0; i < len; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            builder.append(CHARS[x % 0x3E]);
        }
        return builder.toString();
    }

    public static String dateFormat(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy年MM⽉dd⽇ HH:mm:ss").format(dateTime);
    }

    /**
     * 列表拷贝
     *
     * @param list  数据源
     * @param clazz 目标对象 class
     * @param <T>   目标对象 type
     * @return list
     */
    public static <T> List<T> easyCopy(List<?> list, Class<T> clazz) {
        List<T> result = Collections.emptyList();
        if (!CollectionUtils.isEmpty(list)) {
            result = list.stream().map(item -> {
                try {
                    T o = clazz.newInstance();
                    BeanUtils.copyProperties(item, o);
                    return o;
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * 配置返回值分页信息
     *
     * @param response response
     * @param pageInfo pageInfo
     */
    public static void configPageInfo(BasePageResponse response, PageInfo<?> pageInfo) {
        response.setPageNum(pageInfo.getPageNum());
        response.setPageSize(pageInfo.getPageSize());
        response.setPages(pageInfo.getPages());
        response.setTotal(pageInfo.getTotal());
    }

    /**
     * 配置返回值分页信息
     *
     * @param response response
     * @param list     list
     */
    public static void configPageInfo(BasePageResponse response, List<?> list) {
        configPageInfo(response, new PageInfo<>(list));
    }

}
