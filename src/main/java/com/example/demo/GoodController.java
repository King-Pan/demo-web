package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @BelongsProject: demo-web
 * @BelongsPackage: com.example.demo
 * @Author: king-pan
 * @CreateTime: 2019-08-27 09:46
 * @Description: 商品控制器
 */
@RestController
@RequestMapping("/goods")
public class GoodController {


    @RequestMapping("/{id}")
    public ModelAndView good(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("goods");
        if(id.equals("1")){
            view.addObject("name","超级无敌灭蚊器");
        }else if(id.equals("2")){
            view.addObject("name","感冒药");
        }else if(id.equals("3")){
            view.addObject("name","胃药");
        }else{
            view.addObject("name","你的商品已经飞走了");
        }
        return view;
    }
}
