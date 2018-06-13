package com.dsb.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class readFile {
  
  public void writeFile(){
    
  }
  
  public static String readFile(String fileName) throws Exception{
    String fileNameTxt = null;
    if(fileName.lastIndexOf(".zip") > -1){
      //先解压
//      fileNameTxt = GoodZipTest.unZipFiles(fileName,  new File(fileName).getParent()); 
    }else{
      fileNameTxt = fileName;
    }
    

    
    StringBuffer strb = new StringBuffer();  
    FileInputStream fs = new FileInputStream(new File(fileNameTxt));  
    InputStreamReader isr = new InputStreamReader(fs,"UTF-8");  
    BufferedReader br = new BufferedReader(isr);  
    String data = "";  
    while((data = br.readLine()) != null){  
        strb.append(data + "\n");  
 
    }  
    br.close();  
    fs.close();  
    isr.close();  
    System.out.println(strb.toString()); 
    return strb.toString();  
      
  }
  
  
  public static String readFile(File file) throws Exception{
    
    StringBuffer strb = new StringBuffer();  
    FileInputStream fs = new FileInputStream(file);  
    InputStreamReader isr = new InputStreamReader(fs,"UTF-8");  
    BufferedReader br = new BufferedReader(isr);  
    String data = "";  
    while((data = br.readLine()) != null){  
        strb.append(data + "\n");  
    }  
    br.close();  
    fs.close();  
    isr.close();  
    System.out.println(strb.toString()); 
    return strb.toString();  
      
  }
  
  
  /**
   * 读取某个文件夹下的所有文件
   * @throws Exception 
   */
  public static List<String> readFilesDirectory(String filepath) throws Exception {
      List<String> listTextJson = new ArrayList<String>(); 
          try {

                  File file = new File(filepath);
                  if (!file.isDirectory()) {
                          System.out.println("文件");
                          System.out.println("path=" + file.getPath());
                          System.out.println("absolutepath=" + file.getAbsolutePath());
                          System.out.println("name=" + file.getName());
                          
                          
                          //解析读取文件
                          String json = readFile(file.getAbsolutePath());
                          listTextJson.add(json);
                  } else if (file.isDirectory()) {
                          System.out.println("文件夹");
                          String[] filelist = file.list();
                          for (int i = 0; i < filelist.length; i++) {
                                  File readfile = new File(filepath + "\\" + filelist[i]);
                                  if (!readfile.isDirectory()) {
                                          System.out.println("path=" + readfile.getPath());
                                          System.out.println("absolutepath="+ readfile.getAbsolutePath());
                                          System.out.println("name=" + readfile.getName());
                                          
                                          //解析文件
                                          String json = readFile(readfile.getAbsolutePath());
                                          listTextJson.add(json);
                                  } else if (readfile.isDirectory()) {
                                        readFilesDirectory(filepath + "\\" + filelist[i]);
                                          
                                  }
                          }

                  }

          } catch (FileNotFoundException e) {
                  System.out.println("readfile()   Exception:" + e.getMessage());
          }
          return listTextJson;
  }
  
 /* public static String readFileByFiles(String fileName) throws Exception{
    
    
    StringBuffer strb = new StringBuffer();  
    FileInputStream fs = new FileInputStream(new File(fileName));  
    InputStreamReader isr = new InputStreamReader(fs,"UTF-8");  
    BufferedReader br = new BufferedReader(isr);  
    String data = "";  
    while((data = br.readLine()) != null){  
        strb.append(data + "\n");  
    }  
    br.close();  
    fs.close();  
    isr.close();  
    System.out.println(strb.toString()); 
    return strb.toString();  
      
  }*/
  
  
  public static void main(String [] args) throws Exception {
    String content = readFile("D://zipfile//2016-04-19-21");
    System.out.println("content:"+content);
    ContentTOJson(content);
    /**
     * 
2016/04/19 21:10:48{"appId":"8brlm7ufrnbx3","fromUserId":"2000000243","targetId":"1000000355","targetType":1,"GroupId":"","classname":"app:chatNotice","content":{"notice":"已成功付款","objuserid":"1000000355","selfuserid":"2000000243"},"dateTime":"2016-04-19 21:10:48.889","msgUID":"5A2T-98B7-454E-L6O2"}
2016/04/19 21:14:41{"appId":"8brlm7ufrnbx3","fromUserId":"2000000243","targetId":"1000000355","targetType":1,"GroupId":"","classname":"app:chatNotice","content":{"notice":"已成功付款","user":{"id":"2000000243","portrait":"http:\/\/dd-face.digi123.cn\/201604\/6e4016d5af5009ae.jpg","name":"小曦"},"objuserid":"1000000355","selfuserid":"2000000243"},"dateTime":"2016-04-19 21:14:41.311","msgUID":"5A2T-A4N3-S54E-L6O2"}


     * 
     * */
  }
  
  
  public static void ContentTOJson(String content) throws Exception{
    String[] strs = content.split("\n");
    
    for(int i = 0 ; i < strs.length ; i++){
      String jsonContent = strs[i];
      int firstNum = jsonContent.indexOf("{");
      int lastNum = jsonContent.lastIndexOf("}");

      String jsonContents = jsonContent.substring(firstNum, lastNum+1);
      
      Map map = JsonUtil.getMapFromJsObject(jsonContents);
      String appId = (String)map.get("appId");
      System.out.println(appId);
      
  
      
      System.out.println("jsonContents:"+jsonContents);
      
      
    }
    
   
    ///
    //JsonUtil.getJsonFromString();
    
   
    
    
    
    System.out.println("strs:"+strs[0]);
    System.out.println("strs:"+strs[1]);
  }

}
