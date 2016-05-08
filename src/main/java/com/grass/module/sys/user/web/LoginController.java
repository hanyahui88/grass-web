package com.grass.module.sys.user.web;

import com.grass.common.web.BaseController;
import com.grass.module.sys.user.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yahui on 2016/4/25.
 */
@Controller
public class LoginController extends BaseController {
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public ModelAndView index(UserEntity userEntity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
