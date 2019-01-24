package com.dsb.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.AttributedString;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.dsb.entity.PosterElement;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.font.FontDesignMetrics;

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
//        
        long bt = System.currentTimeMillis();
        System.out.println(bt);
        // 正面
        List<PosterElement> elements1 = JSONArray.parseArray("[{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20190116_5c3eee9acaa4e.png\",\"top\":4596.78,\"left\":1526.02,\"width\":587.99,\"height\":588.61,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":6777.04,\"left\":342.01,\"width\":733.01,\"height\":733.78,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"洗衣片\",\"top\":7560.86,\"left\":334.6,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#000000\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"48.00\",\"top\":7841.16,\"left\":334.6,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#FEFEFD\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":6777.04,\"left\":1426.02,\"width\":733.01,\"height\":733.78,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"洗衣片\",\"top\":7560.86,\"left\":1419.66,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#000000\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"48.00\",\"top\":7841.16,\"left\":1419.66,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#FEFEFD\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":6777.04,\"left\":2518.01,\"width\":733.01,\"height\":733.78,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"洗衣片\",\"top\":7560.86,\"left\":2509.5,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#000000\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"48.00\",\"top\":7841.16,\"left\":2509.5,\"width\":740.9,\"textLineLimit\":10,\"fontSize\":95,\"color\":\"#FEFEFD\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"咨询热选：020-34528063\",\"top\":9009.34,\"left\":1050.02,\"fontSize\":95,\"color\":\"#000000\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"lineHeight\":86,\"MaxLineNumber\":0},{\"type\":\"text\",\"content\":\"店铺地址：广东省广州番禺区洛浦街道丽江花园丽波楼C07之二铺\",\"top\":9179.51,\"left\":283.02,\"fontSize\":95,\"color\":\"#000000\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"lineHeight\":86,\"MaxLineNumber\":0}]").toJavaList(PosterElement.class);
        createPoster2File("https://kakqfile.honey-lovely.com/member/jpg/20190123_5c4815350038b.jpg", elements1 , "E:\\0123-" + System.currentTimeMillis() + ".jpg");

        long et = System.currentTimeMillis();
        System.out.println(et+"\n"+(et-bt));
//        // 反面
//        List<PosterElement> elements2 = JSONArray.parseArray("[{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/jpg/20181231_5c29f9c79d0eb.jpg\",\"top\":0,\"left\":0,\"width\":3510,\"height\":4960.8,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":428.08,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":890.14,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":927.58,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1053.14,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":1193.54,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":1317.23,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":1779.29,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":1816.73,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":1942.29,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2082.69,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":2206.39,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":2668.44,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":2705.88,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":2831.45,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":2971.85,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3095.54,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":3557.6,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":3595.04,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":3720.6,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":3861,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":269.99,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":179.99,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":554.39,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":220.01,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":220.01,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":1081.97,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":991.97,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":1366.37,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":1031.99,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":1031.99,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":1893.95,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":1803.95,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":2178.35,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":1843.97,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":1843.97,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"http://lxfile.honey-lovely.com/crm/jpg/20181218_5c1857a034c8d.JPG\",\"top\":3984.69,\"left\":2705.93,\"width\":519.99,\"height\":520.09,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"image\",\"url\":\"https://kakqfile.honey-lovely.com/member/png/20181231_5c297ddf3914d.png\",\"top\":4446.75,\"left\":2615.93,\"width\":698.82,\"height\":136.98,\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"￥48.00\",\"top\":4484.19,\"left\":2990.33,\"width\":1404,\"fontSize\":75,\"color\":\"#C42125\",\"bolder\":true,\"textAlign\":\"left\",\"breakWord\":false,\"MaxLineNumber\":0},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"洗衣片\",\"top\":4609.75,\"left\":2655.95,\"width\":630.02,\"fontSize\":66,\"color\":\"#6A6664\",\"textAlign\":\"left\",\"breakWord\":true,\"lineHeight\":70,\"MaxLineNumber\":2},{\"type\":\"text\",\"textLineLimit\":35,\"content\":\"原价：￥48.00\",\"top\":4750.15,\"left\":2655.95,\"width\":1404,\"fontSize\":70,\"color\":\"#C42125\",\"textAlign\":\"left\",\"textDecoration\":\"line-through\",\"breakWord\":false,\"MaxLineNumber\":0}]").toJavaList(PosterElement.class);
//        createPoster2File("https://kakqfile.honey-lovely.com/member/jpg/20181231_5c29f9c79d0eb.jpg", elements2 , "E:\\0121-" + System.currentTimeMillis() + ".jpg");
//
//        et = System.currentTimeMillis();
//        System.out.println(et+"\n"+(et-bt));
        
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
     * @throws FontFormatException 
     */
    public static void bigImgAddSmallImgAndText(String bigImgPath, String smallImgPath, int sx, int sy, String content,
            int cx, int cy, String outPathWithFileName) throws IOException, FontFormatException {
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
//            Font f = new Font("宋体", Font.PLAIN, 25);
            Font f = Font.createFont(Font.PLAIN, new File("E:\\SIMYOU.TTF"));
            f = f.deriveFont(25f);
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
//        // 主图片的路径
//        InputStream is = getStreamByUrl(baseImgUrl);
//        // 通过JPEG图象流创建JPEG数据流解码器
//        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
//        // 解码当前JPEG数据流，返回BufferedImage对象
//        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        BufferedImage buffImg = ImageIO.read(new URL(baseImgUrl));
        
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
                try {
                    Integer fontStyle = element.getBolder() != null && element.getBolder() ? Font.BOLD : Font.PLAIN;
//                    Font font = new Font(element.getFontName(), fontStyle, element.getFontSize());
                    
//                    Font font = Font.createFont(Font.PLAIN, new File("‪E:\\msyh.ttc"));
                    Font font = Font.createFont(Font.PLAIN, new BufferedInputStream(new FileInputStream("E:\\msyh.ttc")));
//                    Font font = Font.createFont(Font.PLAIN, getStreamByUrl("http://yyt.lexin580.com:8081/yddzb_font/msyh.ttc"));
                    font = font.deriveFont(fontStyle, new Float(element.getFontSize()));
                    
                    Color color = hex2Rgb(element.getColor());
                    g.setColor(color);
                    g.setFont(font);
                    
                    // 处理文字换行 contentLength 超过 productNameLimit 的一半时 换到第二行
                    Integer contentLength = element.getContent().length();
    
                    String[] strs;
                    if (null != element.getTextLineLimit() && contentLength > element.getTextLineLimit()) {
                        int rowsCount = contentLength%element.getTextLineLimit()==0 ? contentLength/element.getTextLineLimit() : contentLength/element.getTextLineLimit()+1;
                        strs = new String[rowsCount];
                        for (int i = 0; i < rowsCount; i++) {
                            if (i == rowsCount-1) {
                                strs[i] = element.getContent().substring(i*element.getTextLineLimit());
                            } else {
                                strs[i] = element.getContent().substring(i*element.getTextLineLimit(), (i+1)*element.getTextLineLimit());
                            }
                        }
                    } else {
                        strs = new String[]{element.getContent()};
                    }
                    
                    // 文字类型的y轴对应的是文字的基线baseline so:y+=baseline
                    FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
                    
                    for (int i = 0; i < strs.length; i++) {
                        AttributedString as = new AttributedString(strs[i]);
                        as.addAttribute(TextAttribute.FONT, font);
                        if ("line-through".equals(element.getTextDecoration())) {
                            // 添加删除线
                            as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                        } else if ("underline".equals(element.getTextDecoration())) {
                            // 添加下划线
                            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                        }
                        // 表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
                        g.drawString(as.getIterator(), element.getLeft().intValue(), element.getTop().add(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(element.getFontSize()))).intValue() + metrics.getAscent()); 
                    }
                } catch (FontFormatException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        g.dispose();
        // 创键编码器，用于编码内存中的图象数据。
//        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//        en.encode(buffImg);
        ImageIO.write(buffImg, "jpg", new File(saveUrl));
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
                    // 将小图片绘到大图片上 限制小图宽高
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), element.getWidth().intValue(), element.getHeight().intValue(), null);
                } else {
                    // 将小图片绘到大图片上
                    g.drawImage(img, element.getLeft().intValue(), element.getTop().intValue(), null);
                }
            } else if ("text".equals(element.getType())) {
                Integer fontStyle = element.getBolder() != null && element.getBolder() ? Font.BOLD : Font.PLAIN;
                Font font = new Font(element.getFontName(), fontStyle, element.getFontSize());
                
                Color color = hex2Rgb(element.getColor());
                g.setColor(color);
                g.setFont(font);
                
                // 处理文字换行 contentLength 超过 productNameLimit 的一半时 换到第二行
                Integer contentLength = element.getContent().length();
                String[] strs;
                if (null != element.getTextLineLimit() && contentLength > element.getTextLineLimit()) {
                    int rowsCount = contentLength%element.getTextLineLimit()==0 ? contentLength/element.getTextLineLimit() : contentLength/element.getTextLineLimit()+1;
                    strs = new String[rowsCount];
                    for (int i = 0; i < rowsCount; i++) {
                        if (i == rowsCount-1) {
                            strs[i] = element.getContent().substring(i*element.getTextLineLimit());
                        } else {
                            strs[i] = element.getContent().substring(i*element.getTextLineLimit(), (i+1)*element.getTextLineLimit());
                        }
                    }
                } else {
                    strs = new String[]{element.getContent()};
                }
                
                // 文字类型的y轴对应的是文字的基线baseline so:y+=baseline
                FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
                
                for (int i = 0; i < strs.length; i++) {
                    AttributedString as = new AttributedString(strs[i]);
                    as.addAttribute(TextAttribute.FONT, font);
                    if ("line-through".equals(element.getTextDecoration())) {
                        // 添加删除线
                        as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    } else if ("underline".equals(element.getTextDecoration())) {
                        // 添加下划线
                        as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    }
                    // 表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
                    g.drawString(as.getIterator(), element.getLeft().intValue(), element.getTop().add(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(element.getFontSize()))).intValue() + metrics.getAscent()); 
                }
            }
        });
        
        g.dispose();
        OutputStream os = response.getOutputStream();
        // 创键编码器，用于编码内存中的图象数据。
//        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//        en.encode(buffImg);
        ImageIO.write(buffImg, "jpg", os);
        is.close();
        os.close();
    }

    /**
     * @Title getStreamByUrl 
     * @Description 从url获取InputStream
     * @param fileUrl
     * @return
     */
    public static InputStream getStreamByUrl(String fileUrl){
        if (StringUtils.isNotBlank(fileUrl)) {
            try {
                URL url = new URL(fileUrl);
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
    
    public static void getSystemFonts(){
        String[] fonts = 
            GraphicsEnvironment  // GraphicsEnvironment(抽象类)  图形环境类
                .getLocalGraphicsEnvironment()  // 获取本地图形环境
                    .getAvailableFontFamilyNames();  // 获取可用字体family名
        
        int fontCount = 0;   // 字体数统计
        for(String font : fonts) {
            fontCount ++;
            System.out.println(font);
        }
        System.out.println("系统字体数:" + fontCount);
    }
}
