/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: CacheConfig
 * @Prject: shopping
 * @Package: com.sunshine.shopping.config
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/12 13:53
 * @version: V1.0
 */

package com.sunshine.shopping.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: CacheConfig
 * @Description: 缓存配置类
 * @author LiMG
 * @date 2017/7/12 13:53
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class CacheConfig {

    @Bean(name = "cacheManager")
    public MemoryConstrainedCacheManager getMemoryConstrainedCacheManager() {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }

}
