package com.dsb.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Created by DSB
 * @date 2018/12/12 10:16
 */
@Data
public class PosterElement {

    /** 类型 PosterInfoTypeEnum */
    private String type;

    /** 图片类型需要传的图片地址 */
    private String url;

    /** 文字类型需要传的文本 */
    private String content;

    /** 上面距离 */
    private BigDecimal top;

    /** 左边距离 */
    private BigDecimal left;

    /** 文字换行宽度 或者是 图片的宽度 */
    private BigDecimal width;

    /** 字数限制
     * 促销海报背面商品名称字数限制 */
    private Integer textLineLimit;

    /** 图片的高度 */
    private BigDecimal height;

    /** 文字的字体大小 */
    private Integer fontSize;

    /** 文字类型 默认宋体 */
    private String fontName;

    /** 文字的字体颜色 #000000 */
    private String color;

    /** 文字是否加粗 */
    private Boolean bolder;

    /** 文字的对齐方式 left */
    private String textAlign;

    /** 文字 显示中划线、下划线效果 underline（下划线）、line-through（中划线）*/
    private String textDecoration;

    /** 是否换行 **/
    private boolean breakWord;

}
