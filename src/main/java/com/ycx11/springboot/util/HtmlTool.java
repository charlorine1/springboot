package com.ycx11.springboot.util;

import com.ycx11.springboot.es.HtmlBean;

import com.ycx11.springboot.es.IndexService;
import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.File;

public class HtmlTool {

    /**
     *
     * @param path html 文件路径
     */
    public static HtmlBean parserHtml(String path)throws Throwable{
        HtmlBean bean  =new HtmlBean();
        Source source=new Source(new File(path));
        // Call fullSequentialParse manually as most of the source will be parsed.
        source.fullSequentialParse();
        Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
        if(titleElement==null){
            return null;
        }else{
            String title=CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
            bean.setTitle(title);
        }
        String content =source.getTextExtractor().setIncludeAttributes(true).toString();

        String url =path.substring(IndexService.DATA_DIR.length());
        bean.setContent(content);
        bean.setUrl(url);
        return bean;
    }

    public static void main(String[] args) {
        try {
            parserHtml("D:/data/www.sxt.cn/blog-category-7.html");
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
