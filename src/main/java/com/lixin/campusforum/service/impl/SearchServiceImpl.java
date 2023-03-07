package com.lixin.campusforum.service.impl;

import com.github.pagehelper.PageHelper;
import com.lixin.campusforum.common.constant.enums.SearchTypeEnums;
import com.lixin.campusforum.common.constant.enums.UserGenderEnums;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.utils.ForumSystemUtils;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.dao.TopicDao;
import com.lixin.campusforum.dao.UserRegistrationDao;
import com.lixin.campusforum.model.bo.topic.TopicListItemBo;
import com.lixin.campusforum.model.bo.user.UserInfoListItemBo;
import com.lixin.campusforum.model.query.SearchQuery;
import com.lixin.campusforum.model.vo.search.SearchResultVo;
import com.lixin.campusforum.model.vo.search.SearchUserListItemVo;
import com.lixin.campusforum.model.vo.topic.TopicListVoItem;
import com.lixin.campusforum.service.SearchService;
import com.lixin.campusforum.service.search.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author lixin
 * @date 2023/3/7 20:03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService, InitializingBean {

    private static final EnumMap<SearchTypeEnums, BaseSearch> ENUM_MAP = new EnumMap<>(SearchTypeEnums.class);

    private final UserRegistrationDao userRegistrationDao;
    private final TopicDao topicDao;

    @Override
    public DataResult<SearchResultVo> search(SearchQuery query) {
        BaseSearch search
                = ENUM_MAP.get(SearchTypeEnums.get(query.getSearchType()));
        SearchResultVo searchResult = search.search(query);
        return ResultUtils.ok(searchResult);
    }

    @Override
    public void afterPropertiesSet() {
        ENUM_MAP.put(SearchTypeEnums.USER, new UserSearch(userRegistrationDao));
        ENUM_MAP.put(SearchTypeEnums.TOPIC, new TopicSearch(topicDao));
    }

    private static abstract class BaseSearch implements Search<SearchResultVo, SearchQuery> {

    }

    @RequiredArgsConstructor
    private static class UserSearch extends BaseSearch {

        private final UserRegistrationDao dao;

        @Override
        public SearchResultVo search(SearchQuery keyword) {
            SearchResultVo vo = new SearchResultVo();
            PageHelper.startPage(keyword.getPageNum(), keyword.getPageSize());
            List<UserInfoListItemBo> boList = dao.selectUserInfoBySearch(keyword);
            List<SearchUserListItemVo> list = ForumSystemUtils.easyCopy(boList,
                    (t, r) -> t.setGender(UserGenderEnums.convert(r.getGender())), SearchUserListItemVo.class);
            ForumSystemUtils.configPageInfo(vo, boList);
            vo.setList(list);
            return vo;
        }
    }

    @RequiredArgsConstructor
    private static class TopicSearch extends BaseSearch {

        private final TopicDao topicDao;

        @Override
        public SearchResultVo search(SearchQuery keyword) {
            BiConsumer<TopicListVoItem, TopicListItemBo> empty = (t, r) -> {
            };
            return ForumSystemUtils.build(SearchResultVo.class,
                            () -> topicDao.selectTopicList(keyword),
                            empty,
                            TopicListVoItem.class)
                    .run(keyword);
        }
    }
}
