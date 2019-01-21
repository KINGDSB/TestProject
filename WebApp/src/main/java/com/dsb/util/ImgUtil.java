package com.dsb.util;

import com.alibaba.fastjson.JSONArray;
import com.dsb.entity.PosterElement;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.AttributedString;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class ImgUtil {

    public static void main(String[] args) throws ParseException, ImageFormatException, IOException {

//        String bigImg = "E:\\1.jpg";
//        String smallImg = "E:\\2.jpg";
//        String content = "好久不见，你还好吗";
//        String outPath = "E:\\" + System.currentTimeMillis() + ".jpg";
//        try {
//            bigImgAddSmallImgAndText(bigImg, smallImg, 200, 600, content, 200, 200, outPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        long bt = System.currentTimeMillis();
        System.out.println(bt);
        // 正面
        List<PosterElement> elements1 = JSONArray.parseArray("[{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20190118_5c4161706f1f8.png\",\"top\":2260.35,\"left\":620.01,\"width\":600.02,\"height\":600.12,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"可爱可亲母婴生活馆(广州番禺店)\",\"top\":2260.35,\"left\":1408.02,\"width\":1591.2,\"fontSize\":94,\"color\":\"#F0D79E\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":85,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":16,\"content\":\"地址：广东省广州番禺区洛浦街道丽江花园丽波楼C07之二铺\",\"top\":2380.39,\"left\":1408.02,\"width\":1591.2,\"fontSize\":94,\"color\":\"#F0D79E\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":85,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"020-34528063\",\"top\":2682.44,\"left\":1872,\"width\":516.02,\"fontSize\":118,\"color\":\"#F0D79E\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0}]").toJavaList(PosterElement.class);
        createPoster2File("https://kakqfile.honey-lovely.com/member/jpg/20181231_5c29f9afcbe8e.jpg", elements1 , "E:\\0121-" + System.currentTimeMillis() + ".jpg");

        long et = System.currentTimeMillis();
        System.out.println(et+"\n"+(et-bt));
        // 反面
        List<PosterElement> elements2 = JSONArray.parseArray("[{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/jpg/20181231_5c29f9c79d0eb.jpg\",\"top\":0,\"left\":0,\"width\":3510,\"height\":4960.8,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0}]").toJavaList(PosterElement.class);
        createPoster2File("https://kakqfile.honey-lovely.com/member/jpg/20181231_5c29f9c79d0eb.jpg", elements2 , "E:\\0118-" + System.currentTimeMillis() + ".jpg");

        et = System.currentTimeMillis();
        System.out.println(et+"\n"+(et-bt));
        
    }

    /***
     * 在一张大图张添加小图和文字
     * 
     * @param bigImgPath
     *            大图的路径
     * @param smallImgPath
     *            小图的路径
     * @param sx
     *            小图在大图上x抽位置
     * @param sy
     *            小图在大图上y抽位置
     * @param content
     *            文字内容
     * @param cx
     *            文字在大图上y抽位置
     * @param cy
     *            文字在大图上y抽位置
     * @param outPathWithFileName
     *            结果输出路径
     */
    public static void bigImgAddSmallImgAndText(String bigImgPath, String smallImgPath, int sx, int sy, String content,
            int cx, int cy, String outPathWithFileName) throws IOException {
        // 主图片的路径
        InputStream is = new FileInputStream(bigImgPath);
        // 通过JPEG图象流创建JPEG数据流解码器
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        // 解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        // 得到画笔对象
        Graphics g = buffImg.getGraphics();

        // 小图片的路径
        ImageIcon imgIcon = new ImageIcon(smallImgPath);
        // 得到Image对象。
        Image img = imgIcon.getImage();
        // 将小图片绘到大图片上,5,300 .表示你的小图片在大图片上的位置。
        g.drawImage(img, sx, sy, null);
        // 设置颜色。
//        g.setColor(Color.WHITE);

        // 最后一个参数用来设置字体的大小
        if (content != null) {
            Font f = new Font("宋体", Font.PLAIN, 25);
            Color mycolor = Color.red;// new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(f);
            g.drawString(content, cx, cy); // 表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
        }
        g.dispose();
        OutputStream os = new FileOutputStream(outPathWithFileName);
        // 创键编码器，用于编码内存中的图象数据。
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        is.close();
        os.close();
    }
    
    /**
     * @Title createPoster 
     * @Description 生成海报
     * @param baseImgUrl
     * @param elements
     * @param saveUrl
     * @throws IOException 
     * @throws ImageFormatException 
     */
    public static void createPoster2File(String baseImgUrl, List<PosterElement> elements, String saveUrl) throws ImageFormatException, IOException {
        // 主图片的路径
        InputStream is = getStreamByUrl(baseImgUrl);
        // 通过JPEG图象流创建JPEG数据流解码器
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        // 解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        // 得到画笔对象
        Graphics g = buffImg.getGraphics();
        
        elements.forEach(element -> {
            if ("image".equals(element.getType())) {
                // 小图片的路径
                ImageIcon imgIcon = null;
                try {
                    imgIcon = new ImageIcon(new URL(element.getUrl()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    // 设置默认图片
                    try {
                        imgIcon = new ImageIcon(new URL("http://file.lexin580.com/Files/ProductPicture/no_img.jpg"));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                        // 设置默认图片失败
                    }
                }
                // 得到Image对象。
                Image img = imgIcon.getImage();
                if (null != element.getWidth() && null != element.getHeight()) {
                    // 将小图片绘到大图片上
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), element.getWidth().intValue(), element.getHeight().intValue(), null);
                } else {
                    // 将小图片绘到大图片上
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), null);
                }
            } else if ("text".equals(element.getType())) {

                String fontName = element.getFontName() != null ? element.getFontName() : "宋体";
                Integer fontStyle = element.getBolder() != null && element.getBolder() ? Font.BOLD : Font.PLAIN;
                Font font = new Font(fontName, fontStyle, element.getFontSize());
                
                Color color = hex2Rgb(element.getColor());
                g.setColor(color);
                g.setFont(font);
                
                // 处理文字换行 contentLength 超过 productNameLimit 的一半时 换到第二行
                Integer contentLength = element.getContent().length();

                String[] strs;
                if (null != element.getTextLineLimit() && contentLength > element.getTextLineLimit()/2) {
                    int half = element.getTextLineLimit()/2;
                    strs = new String[]{element.getContent().substring(0, half), element.getContent().substring(half)};
                } else {
                    strs = new String[]{element.getContent()};
                }
                
                for (int i = 0; i < strs.length; i++) {
                    AttributedString as = new AttributedString(strs[i]);
                    as.addAttribute(TextAttribute.FONT, font);
                    if ("line-through".equals(element.getTextDecoration())) {
                        // 添加下划线
                        as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    }
                    // 表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
                    g.drawString(as.getIterator(), element.getLeft().intValue(), element.getTop().add(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(element.getFontSize()))).intValue()); 
                }
            }
        });
        
        g.dispose();
        OutputStream os = new FileOutputStream(saveUrl);
        // 创键编码器，用于编码内存中的图象数据。
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        is.close();
        os.close();
    }
    
    /**
     * @Title createPoster 
     * @Description 生成海报
     * @param baseImgUrl
     * @param elements
     * @param response
     * @throws IOException 
     * @throws ImageFormatException 
     */
    public static void createPoster(String baseImgUrl, List<PosterElement> elements, HttpServletResponse response) throws ImageFormatException, IOException {
        // 主图片的路径
        InputStream is = getStreamByUrl(baseImgUrl);
        // 通过JPEG图象流创建JPEG数据流解码器
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        // 解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        // 得到画笔对象
        Graphics g = buffImg.getGraphics();
        
        elements.forEach(element -> {
            if ("image".equals(element.getType())) {
                // 小图片的路径
                ImageIcon imgIcon = null;
                try {
                    imgIcon = new ImageIcon(new URL(element.getUrl()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    // 设置默认图片
                    try {
                        imgIcon = new ImageIcon(new URL("http://file.lexin580.com/Files/ProductPicture/no_img.jpg"));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                        // 设置默认图片失败
                    }
                }
                // 得到Image对象。
                Image img = imgIcon.getImage();
                if (null != element.getWidth() && null != element.getHeight()) {
                    // 将小图片绘到大图片上
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), element.getWidth().intValue(), element.getHeight().intValue(), null);
                } else {
                    // 将小图片绘到大图片上
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), null);
                }
            } else if ("text".equals(element.getType())) {
                Font font = new Font("宋体", Font.BOLD, element.getFontSize());
                Color color = hex2Rgb(element.getColor());
                g.setColor(color);
                g.setFont(font);
                
                AttributedString as = new AttributedString(element.getContent());
                as.addAttribute(TextAttribute.FONT, font);
                if ("line-through".equals(element.getTextDecoration())) {
                    // 添加下划线
                    as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                }
                g.drawString(as.getIterator(), element.getLeft().intValue(), element.getTop().intValue()); // 表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
            }
        });
        
        g.dispose();
        OutputStream os = response.getOutputStream();
        // 创键编码器，用于编码内存中的图象数据。
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        is.close();
        os.close();
    }

    /**
     * @Title getStreamByUrl 
     * @Description 从url获取InputStream
     * @param imgUrl
     * @return
     */
    public static InputStream getStreamByUrl(String imgUrl){
        if (StringUtils.isNotBlank(imgUrl)) {
            try {
                URL url = new URL(imgUrl);
                //打开链接  
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                //设置请求方式为"GET"  
                conn.setRequestMethod("GET");  
                //超时响应时间为5秒  
                conn.setConnectTimeout(5 * 1000);  
                //通过输入流获取图片数据  
                return conn.getInputStream();  
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * hex转Color(rgb)
     * @param colorStr e.g. "#FFFFFF"
     * @return 
     */
    public static Color hex2Rgb(String colorStr) {
        return new Color(
            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ));
    }
}
