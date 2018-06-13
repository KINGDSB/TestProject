package com.dsb.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * 百度地图API
 * Created in 17-5-24 by tongzhenke.
 */
public class LngAndAatUtils {

    /**
     * 根据地址转换成经纬度数据
     * @param address
     * @return
     * @throws Exception 
     */
    public static Map<String, Double> getLngAndLat(String address) throws Exception{
        Map<String, Double> map = new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=HDh0KvqX1bxu09KxbIAbudkWAqzT24K2";
        String json = loadJSON(url);
        
        Map response = JsonUtil.getMapFromJsObject(json);

        if(response.get("status").toString().equals("0")){
        	double lng = Double.parseDouble(((Map<String, String>)((Map)response.get("result")).get("location")).get("lng"));
        	double lat = Double.parseDouble(((Map<String, String>)((Map)response.get("result")).get("location")).get("lat"));
//            double lng = object.getJSONObject("result").getJSONObject("location").getDouble("lng");
//            double lat = object.getJSONObject("result").getJSONObject("location").getDouble("lat");

            map.put("lng", lng);
            map.put("lat", lat);
        }

        return map;
    }

    public static String loadJSON(String url){
        StringBuilder json = new StringBuilder();
        try {
            URL u = new URL(url);
            URLConnection connection = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null){
                json.append(inputLine);
            }
            in.close();
        }catch (Exception e){

        }
        return json.toString();
    }

    /**
     * 计算两个经纬度之间的距离(单位:米)
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2){
        // 纬度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;
        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;
        // 地球半径
        double R = 6371;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d * 1000;
    }

    public static void main(String[] args) throws Exception{
//        Map<String, Double> map = LngAndAatUtils.getLngAndLat("中国湖北省武汉市蔡甸区龙阳大道114号");
//        System.out.println("经度："+map.get("lng")+",纬度："+map.get("lat"));
//
//        Map<String, Double> map2 = LngAndAatUtils.getLngAndLat("中国湖北省武汉市蔡甸区新艺街177");
//
//        double distance = getDistance(map.get("lng"), map.get("lat"), map2.get("lng"), map2.get("lat"));
//        System.out.println("距离：" + distance + "米");
    	

        double distance = getDistance(114.231605, 30.614406, 114.241893, 30.631091);
        System.out.println("距离：" + distance + "米");

    }

}
