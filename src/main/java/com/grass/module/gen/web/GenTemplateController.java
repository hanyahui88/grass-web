/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.web;


import com.github.pagehelper.PageInfo;
import com.grass.common.utils.StringUtils;
import com.grass.common.web.BaseController;
import com.grass.module.gen.entity.GenTemplate;
import com.grass.module.gen.service.GenTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 代码模板Controller
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTemplate")
public class GenTemplateController extends BaseController {

    @Autowired
    private GenTemplateService genTemplateService;

    @ModelAttribute
    public GenTemplate get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return genTemplateService.get(id);
        } else {
            return new GenTemplate();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
        PageInfo<GenTemplate> page = genTemplateService.find(super.getPageNum(request), super.getPageSize(request), genTemplate);
        model.addAttribute("page", page);
        return "module/gen/genTemplateList";
    }

    @RequestMapping(value = "form")
    public String form(GenTemplate genTemplate, Model model) {
        model.addAttribute("genTemplate", genTemplate);
        return "module/gen/genTemplateForm";
    }

    @RequestMapping(value = "save")
    public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
//        if (!beanValidator(model, genTemplate)) {
//            return form(genTemplate, model);
//        }
        genTemplateService.save(genTemplate);
        addMessage(redirectAttributes, "保存代码模板'" + genTemplate.getName() + "'成功");
        return "redirect:" + adminPath + "/template/gen/genTemplate/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
        genTemplateService.delete(genTemplate);
        addMessage(redirectAttributes, "删除代码模板成功");
        return "redirect:" + adminPath + "/template/gen/genTemplate/?repage";
    }

}
