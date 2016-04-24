/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.common.persistence.BaseService;
import com.grass.common.utils.StringUtils;
import com.grass.module.gen.mapper.GenSchemeMapper;
import com.grass.module.gen.mapper.GenTableColumnMapper;
import com.grass.module.gen.mapper.GenTableMapper;
import com.grass.module.gen.entity.*;
import com.grass.module.gen.util.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 生成方案Service
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {
    @Autowired
    private GenSchemeMapper genSchemeDao;
    @Autowired
    private GenTableMapper genTableDao;
    @Autowired
    private GenTableColumnMapper genTableColumnDao;

    public GenScheme get(String id) {
        return genSchemeDao.get(id);
    }

    public PageInfo<GenScheme> find(int pageNum, int pageSize, GenScheme genScheme) {
        GenUtils.getTemplatePath();
        PageHelper.startPage(pageNum, pageSize, true);
        PageInfo<GenScheme> page = new PageInfo<>(genSchemeDao.findList(genScheme));
        return page;
    }

    @Transactional(readOnly = false)
    public String save(GenScheme genScheme) {
        if (StringUtils.isBlank(genScheme.getId())) {
            genScheme.preInsert();
            genSchemeDao.insert(genScheme);
        } else {
            genScheme.preUpdate();
            genSchemeDao.update(genScheme);
        }
        // 生成代码
        if ("1".equals(genScheme.getFlag())) {
            return generateCode(genScheme);
        }
        return "";
    }

    @Transactional(readOnly = false)
    public void delete(GenScheme genScheme) {
        genSchemeDao.delete(genScheme);
    }

    private String generateCode(GenScheme genScheme) {

        StringBuilder result = new StringBuilder();

        // 查询主表及字段列
        GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
        genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));

        // 获取所有代码模板
        GenConfig config = GenUtils.getConfig();

        // 获取模板列表
        List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
        List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genScheme.getCategory(), true);

        // 如果有子表模板，则需要获取子表列表
        if (childTableTemplateList.size() > 0) {
            GenTable parentTable = new GenTable();
            parentTable.setParentTable(genTable.getName());
            genTable.setChildList(genTableDao.findList(parentTable));
        }

        // 生成子表模板代码
        for (GenTable childTable : genTable.getChildList()) {
            childTable.setParent(genTable);
            childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
            genScheme.setGenTable(childTable);
            Map<String, Object> childTableModel = GenUtils.getDataModel(genScheme);
            for (GenTemplate tpl : childTableTemplateList) {
                result.append(GenUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
            }
        }

        // 生成主表模板代码
        genScheme.setGenTable(genTable);
        Map<String, Object> model = GenUtils.getDataModel(genScheme);
        for (GenTemplate tpl : templateList) {
            result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
        }
        return result.toString();
    }
}
