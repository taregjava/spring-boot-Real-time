package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.model.CrVerificationResponseDTO;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CBCacheReaderService {

    private final CacheManager cacheManager;

    public CBCacheReaderService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CrVerificationResponseDTO getCrInfoFromCache(String crNumber) {
        Cache cache = cacheManager.getCache("crInfoCache");
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(crNumber);
            if (wrapper != null && wrapper.get() instanceof CrVerificationResponseDTO crInfo) {
                return crInfo;
            }
        }
        return null;
    }
}