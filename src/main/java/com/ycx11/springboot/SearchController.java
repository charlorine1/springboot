package com.ycx11.springboot;

import com.ycx11.springboot.es.HtmlBean;
import com.ycx11.springboot.es.IndexService;
import com.ycx11.springboot.es.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {


   @Autowired
    private IndexService indexService;

    @RequestMapping("/search.do")
    public String search(String keyword,int num,int count,Model m){
    PageBean<HtmlBean> page =indexService.search(keyword, num, count);
        m.addAttribute("page", page);
        System.out.println("--------------------------------------------");
        return "hello";
    }

}
