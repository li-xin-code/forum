package com.lixin.campusforum.service;

import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.model.query.SearchQuery;
import com.lixin.campusforum.model.vo.search.SearchResultVo;

/**
 * @author lixin
 * @date 2023/3/7 20:01
 */
public interface SearchService {

    /***
     * search
     *
     * @param query ...
     *
     * @return com.lixin.campusforum.common.result.DataResult<com.lixin.campusforum.model.vo.search.SearchResultVo>
     * @date 2023/3/7 20:02
     **/
    DataResult<SearchResultVo> search(SearchQuery query);
}
