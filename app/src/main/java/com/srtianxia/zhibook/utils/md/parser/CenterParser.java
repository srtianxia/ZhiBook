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

public class CenterParser extends MarkDown.MDParser {
    
    @Override
    public MarkDown.MDWord parseLineFmt(String content) {
        return MarkDown.MDWord.NULL;
    }

    @Override
    public MarkDown.MDWord parseWordFmt(String content) {
        if (content.charAt(0)=='{'&&content.charAt(content.length()-1)=='}') {
            int length = content.length();
            return new MarkDown.MDWord(content.substring(1,length-1),length,MarkDown.MD_FMT_CENTER);
        }
        return MarkDown.MDWord.NULL;
    }
}
