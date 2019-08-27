package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: mall
 * @BelongsPackage: com.example.web.web
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-08-16 23:08
 * @Description: aaa
 */
@RestController
public class HomeController {

    private static Map<String,String> nameMap = new HashMap<>();

    static {
        nameMap.put("1","今朝大药房");
        nameMap.put("2","天天大药房");
        nameMap.put("3","京东自营大药房");
    }

    @RequestMapping("/shop/{id}")
    public ModelAndView home(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("shop");
        view.addObject("name",nameMap.get(id+""));
        System.out.println(request.getParameter("query"));
        view.addObject("query",request.getParameter("query"));
        return view;
    }

}
