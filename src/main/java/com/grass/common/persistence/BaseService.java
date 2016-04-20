package com.grass.common.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 韩亚辉 on 2016/4/5.
 */
@Transactional(readOnly = true)
public abstract class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

}
