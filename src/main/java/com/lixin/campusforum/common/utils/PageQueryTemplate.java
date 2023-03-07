package com.lixin.campusforum.common.utils;

import com.github.pagehelper.PageHelper;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.model.base.BasePageRequest;
import com.lixin.campusforum.model.base.BasePageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @param <PR> result
 * @param <Q>  query
 * @param <T>  target list type
 * @param <R>  resource list type
 * @author lixin
 */
@Slf4j
@RequiredArgsConstructor
public class PageQueryTemplate<PR extends BasePageResponse, Q extends BasePageRequest, R, T> {
    private final Class<PR> resultClass;
    private final Supplier<List<R>> resourceListSupplier;
    private final BiConsumer<T, R> consumer;
    private final Class<T> voListItemType;

    public PR run(Q query) {
        try {
            PR result = resultClass.newInstance();
            PageHelper.startPage(query.getPageNum(), query.getPageSize());
            List<R> boList = resourceListSupplier.get();
            List<T> list = ForumSystemUtils.easyCopy(boList, consumer, voListItemType);
            ForumSystemUtils.configPageInfo(result, boList);
            Field field = resultClass.getDeclaredField("list");
            field.setAccessible(true);
            field.set(result, list);
            return result;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            log.error("new instance filed", e);
            throw new NotExpectedException(e.getMessage());
        }
    }
}