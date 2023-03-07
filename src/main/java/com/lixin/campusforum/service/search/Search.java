package com.lixin.campusforum.service.search;

/**
 * @param <R> result voListItemType
 * @param <K> keyword voListItemType
 * @author lixin
 * @date 2023/3/7 20:06
 */
public interface Search<R, K> {

    /**
     * search
     *
     * @param keyword ...
     * @return R
     * @date 2023/3/7 20:09
     **/
    R search(K keyword);
}
