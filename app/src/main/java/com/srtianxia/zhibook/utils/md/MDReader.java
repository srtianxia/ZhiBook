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
package com.srtianxia.zhibook.utils.md;


import android.text.SpannableStringBuilder;
import android.util.Log;

import com.srtianxia.zhibook.utils.md.parser.BoldParser;
import com.srtianxia.zhibook.utils.md.parser.CenterParser;
import com.srtianxia.zhibook.utils.md.parser.HeaderParser;
import com.srtianxia.zhibook.utils.md.parser.OrderListParser;
import com.srtianxia.zhibook.utils.md.parser.QuoteParser;
import com.srtianxia.zhibook.utils.md.parser.UnOrderListParser;

import java.util.ArrayList;
import java.util.List;

public class MDReader {
        
    private final String mContent;
    private List<MarkDown.MDLine> mMDLines = new ArrayList<MarkDown.MDLine>();
    private static List<MarkDown.MDParser> mMDParsers = new ArrayList<MarkDown.MDParser>();
    
    static {
        mMDParsers.add(new HeaderParser());
        mMDParsers.add(new QuoteParser());
        mMDParsers.add(new OrderListParser());
        mMDParsers.add(new UnOrderListParser());
        mMDParsers.add(new BoldParser());
        mMDParsers.add(new CenterParser());
    }
    
    public MDReader(String content) {
        mContent = content;
        if (mContent==null||"".equals(content)) {
            return;
        }
        String[] lines = content.split("\n");
        for (String line : lines) {            
            mMDLines.add(parseLine(line));
        }        
    }
    
    public String getTitle() {
        if (mContent==null||"".equals(mContent)) {
            return "";
        }
        int end = mContent.indexOf("\n");        
        return mContent.substring(0,end==-1?mContent.length():end); 
    }
    
    public String getContent() {
        return mContent;
    }
    
    public String getRawContent() {
        StringBuilder builder = new StringBuilder();
        for (MarkDown.MDLine line : mMDLines) {
            builder.append(line.getRawContent());
            builder.append("\n");
        }
        return builder.toString();
    }
        
    public SpannableStringBuilder getFormattedContent() {
        return new MDFormatter(mMDLines).getFormattedContent();
    }
    
    private MarkDown.MDLine parseLine(String lineContent) {
        
        MarkDown.MDLine mdline = new MarkDown.MDLine(lineContent);
        if ("".equals(lineContent)) {
            return mdline;
        }
        
        String pContent = lineContent;
        
        //Parse the start format        
        for (MarkDown.MDParser parser : mMDParsers) {
            MarkDown.MDWord word = parser.parseLineFmt(pContent);
            if (word.mFormat != MarkDown.MD_FMT_TEXT) {
                mdline.mFormat = word.mFormat;
                pContent = lineContent.substring(word.mLength);
                break;
            }
        }        
        
        //Parse the word format              
        StringBuilder mNoFmtContent = new StringBuilder();
        while(pContent.length() != 0) {
            boolean isFmtFound = false;
            //Check format start with pContent
            for (MarkDown.MDParser parser : mMDParsers) {
                MarkDown.MDWord word = parser.parseWordFmt(pContent);
                if (word.mLength > 0) {
                    isFmtFound = true;
                    //Add no format string first 
                    int noFmtContentLen = mNoFmtContent.length(); 
                    if (noFmtContentLen!=0) {                
                        mdline.mMDWords.add(new MarkDown.MDWord(mNoFmtContent.toString(),noFmtContentLen,MarkDown.MD_FMT_TEXT));
                        mNoFmtContent = new StringBuilder();
                    }                            
                    mdline.mMDWords.add(word);
                    pContent = pContent.substring(word.mLength); 
                    break;
                }
            }
            //If no format found, move to next position
            if (!isFmtFound) {
                mNoFmtContent.append(pContent.charAt(0));
                pContent = pContent.substring(1);
                if (pContent.length()==0) {
                    mdline.mMDWords.add(new MarkDown.MDWord(mNoFmtContent.toString(),mNoFmtContent.length(),MarkDown.MD_FMT_TEXT));
                    break;
                }
            }
        }                        
        return mdline;
    }
    
    protected void display() {
        StringBuilder builder = new StringBuilder();
        builder.append("Markdown Parse: \n" + mContent + "\n\n");
        for (MarkDown.MDLine line : mMDLines) {
            builder.append("Line format: " + line.mFormat + "\n");
            for (MarkDown.MDWord word : line.mMDWords) {
                builder.append("Word: "+word.mRawContent+", "+word.mFormat+"\n");
            }
        }        
        Log.d("JNote",builder.toString());
    }
}
