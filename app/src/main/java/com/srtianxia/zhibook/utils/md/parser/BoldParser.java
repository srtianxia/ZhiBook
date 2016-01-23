package com.srtianxia.zhibook.utils.md.parser;

import com.srtianxia.zhibook.utils.md.MarkDown;

/**
 * Created by srtianxia on 2016/1/20.
 */
public class BoldParser extends MarkDown.MDParser {
    private static final String KEY = "**";

    @Override
    public MarkDown.MDWord parseLineFmt(String content) {
        return MarkDown.MDWord.NULL;
    }

    @Override
    public MarkDown.MDWord parseWordFmt(String content) {
        if (!content.startsWith(KEY)) {
            return MarkDown.MDWord.NULL;
        }
        int position = content.indexOf(KEY,2);
        if (position==-1) {
            return MarkDown.MDWord.NULL;
        }
        return new MarkDown.MDWord(content.substring(2,position),position+2, MarkDown.MD_FMT_BOLD);
    }
}
