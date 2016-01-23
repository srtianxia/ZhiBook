/*
 *  Copyright (C) 2015, Jhuster, All Rights Reserved
 *
 *  Author:  Jhuster(lujun.hust@gmail.com)
 *  
 *  https://github.com/Jhuster/JNote
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 */
package com.srtianxia.zhibook.utils.md.parser;


import com.srtianxia.zhibook.utils.md.MarkDown;

public class HeaderParser extends MarkDown.MDParser {
    
    public static final String HEADER = "# ";
    public static final String HEADER2 = "## ";
    public static final String HEADER3 = "### ";

    @Override
    public MarkDown.MDWord parseLineFmt(String content) {
        if (content.startsWith(HEADER)) {
            return new MarkDown.MDWord("",HEADER.length(),MarkDown.MD_FMT_HEADER1);
        }
        else if (content.startsWith(HEADER2)) {
            return new MarkDown.MDWord("",HEADER2.length(),MarkDown.MD_FMT_HEADER2);
        }
        else if (content.startsWith(HEADER3)) {
            return new MarkDown.MDWord("",HEADER3.length(),MarkDown.MD_FMT_HEADER3);
        }        
        return MarkDown.MDWord.NULL;
    }

    @Override
    public MarkDown.MDWord parseWordFmt(String content) {
        return MarkDown.MDWord.NULL;
    }    
}
