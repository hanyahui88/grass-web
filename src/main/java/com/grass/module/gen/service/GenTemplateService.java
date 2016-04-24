/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.common.persistence.BaseService;
import com.grass.common.utils.StringUtils;
import com.grass.module.gen.mapper.GenTemplateMapper;
import com.grass.module.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代码模板Service
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenTemplateService extends BaseService {

    @Autowired
    private GenTemplateMapper genTemplateDao;

    public GenTemplate get(String id) {
        return genTemplateDao.get(id);
    }

    public PageInfo<GenTemplate> find(int pageNum, int pageSize, GenTemplate genTemplate) {
        PageHelper.startPage(pageNum, pageSize, true);
        PageInfo<GenTemplate> page = new PageInfo<>(genTemplateDao.findList(genTemplate));
        return page;
    }

    @Transactional(readOnly = false)
    public void save(GenTemplate genTemplate) {
        if (genTemplate.getContent() != null) {
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }
        if (StringUtils.isBlank(genTemplate.getId())) {
            genTemplate.preInsert();
            genTemplateDao.insert(genTemplate);
        } else {
            genTemplate.preUpdate();
            genTemplateDao.update(genTemplate);
        }
    }

    @Transactional(readOnly = false)
    public void delete(GenTemplate genTemplate) {
        genTemplateDao.delete(genTemplate);
    }

}
