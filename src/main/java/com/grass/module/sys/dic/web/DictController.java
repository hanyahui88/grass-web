/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dic.web;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.grass.common.utils.StringUtils;
import com.grass.common.web.BaseController;
import com.grass.module.sys.dic.entity.DictEntity;
import com.grass.module.sys.dic.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 字典Controller
 *
 * @author hanyahui
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @ModelAttribute
    public DictEntity get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return dictService.get(id);
        } else {
            return new DictEntity();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(DictEntity dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> typeList = dictService.findTypeList();
        model.addAttribute("typeList", typeList);
        PageInfo<DictEntity> page = dictService.findPage(super.getPageNum(request), super.getPageSize(request), dict);
        model.addAttribute("page", page);
        return "modules/sys/dictList";
    }

    @RequestMapping(value = "form")
    public String form(DictEntity dict, Model model) {
        model.addAttribute("dict", dict);
        return "modules/sys/dictForm";
    }

    @RequestMapping(value = "save")//@Valid
    public String save(DictEntity dict, Model model, RedirectAttributes redirectAttributes) {
//		if(GlobalUtils.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
//		}
//		if (!beanValidator(model, dict)){
//			return form(dict, model);
//		}
        dictService.save(dict);
        addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
        return "redirect:" + adminPath + "/sys/dict/?repage&type=" + dict.getType();
    }

    @RequestMapping(value = "delete")
    public String delete(DictEntity dict, RedirectAttributes redirectAttributes) {
//		if(GlobalUtils.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/dict/?repage";
//		}
        dictService.delete(dict);
        addMessage(redirectAttributes, "删除字典成功");
        return "redirect:" + adminPath + "/sys/dict/?repage&type=" + dict.getType();
    }

    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String type, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        DictEntity dict = new DictEntity();
        dict.setType(type);
        List<DictEntity> list = dictService.findList(dict);
        for (int i = 0; i < list.size(); i++) {
            DictEntity e = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("pId", e.getParentId());
            map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
            mapList.add(map);
        }
        return mapList;
    }

    @ResponseBody
    @RequestMapping(value = "listData")
    public List<DictEntity> listData(@RequestParam(required = false) String type) {
        DictEntity dict = new DictEntity();
        dict.setType(type);
        return dictService.findList(dict);
    }

}
