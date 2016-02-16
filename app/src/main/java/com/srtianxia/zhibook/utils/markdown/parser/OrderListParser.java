package com.srtianxia.zhibook.utils.markdown.parser;


import com.srtianxia.zhibook.utils.markdown.Markdown;

public class OrderListParser extends Markdown.MDParser {
    
    private static final String KEY = "^[0-9].*";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if (!content.matches(KEY)) {
            return Markdown.MDWord.NULL;
        }  
        return new Markdown.MDWord("",0,Markdown.MD_FMT_ORDER_LIST);
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        return Markdown.MDWord.NULL;
    }

}
