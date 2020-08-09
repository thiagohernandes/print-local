package com.br.print.core.cache;

import com.br.print.core.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EhCacheLogger implements CacheEventListener<Object, Object> {

    private final ApiUtil apiUtil = new ApiUtil();

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("Cache key: {} | Cache emited: {} ", cacheEvent.getKey(), apiUtil.dateTimeFormated());
    }
}