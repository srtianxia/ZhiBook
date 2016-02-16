package com.srtianxia.zhibook.utils.markdown.parser;


import com.srtianxia.zhibook.utils.markdown.Markdown;

public class ItalicParser extends Markdown.MDParser {
    
    private static final String KEY = "*";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        if (!content.startsWith(KEY)) {
            return Markdown.MDWord.NULL;
        }
        int position = content.indexOf(KEY,1);
        if (position==-1) {
            return Markdown.MDWord.NULL;
        }
        return new Markdown.MDWord(content.substring(1,position),position+1,Markdown.MD_FMT_ITALIC);
    }
}
