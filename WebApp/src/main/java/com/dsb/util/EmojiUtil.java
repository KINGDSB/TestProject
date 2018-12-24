package com.dsb.util;

import com.vdurmont.emoji.EmojiParser;

public class EmojiUtil {

    public static void main(String[] args) {
        System.out.println("啊啊啊😈從😫");
        System.out.println(EmojiParser.parseToUnicode("啊啊啊😈從😫"));
        
    }
    
}
