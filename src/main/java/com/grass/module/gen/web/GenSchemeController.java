/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.web;


import com.github.pagehelper.PageInfo;
import com.grass.common.utils.StringUtils;
import com.grass.common.web.BaseController;
import com.grass.module.gen.entity.GenScheme;
import com.grass.module.gen.service.GenSchemeService;
import com.grass.module.gen.service.GenTableService;
import com.grass.module.gen.util.GenUtils;
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
 * 生成方案Controller
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genScheme")
public class GenSchemeController extends BaseController {

    @Autowired
    private GenSchemeService genSchemeService;

    @Autowired
    private GenTableService genTableService;

    @ModelAttribute
    public GenScheme get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return genSchemeService.get(id);
        } else {
            return new GenScheme();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
        PageInfo<GenScheme> page = genSchemeService.find(super.getPageNum(request), super.getPageSize(request), genScheme);
        model.addAttribute("page", page);

        return "module/gen/genSchemeList";
    }

    @RequestMapping(value = "form")
    public String form(GenScheme genScheme, Model model) {
        if (StringUtils.isBlank(genScheme.getPackageName())) {
            genScheme.setPackageName("com.swifts.frame");
        }
//		if (StringUtils.isBlank(genScheme.getFunctionAuthor())){
//			genScheme.setFunctionAuthor(UserUtils.getUser().getName());
//		}
        model.addAttribute("genScheme", genScheme);
        model.addAttribute("config", GenUtils.getConfig());
        model.addAttribute("tableList", genTableService.findAll());
        return "module/gen/genSchemeForm";
    }

    @RequestMapping(value = "save")
    public String save(GenScheme genScheme, Model model, RedirectAttributes redirectAttributes) {
//        if (!beanValidator(model, genScheme)) {
//            return form(genScheme, model);
//        }

        String result = genSchemeService.saveScheme(genScheme);
        addMessage(redirectAttributes, "操作生成方案'" + genScheme.getName() + "'成功<br/>" + result);
        return "redirect:" + adminPath + "/template/gen/genScheme/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenScheme genScheme, RedirectAttributes redirectAttributes) {
        genSchemeService.delete(genScheme);
        addMessage(redirectAttributes, "删除生成方案成功");
        return "redirect:" + adminPath + "/template/gen/genScheme/?repage";
    }

}
