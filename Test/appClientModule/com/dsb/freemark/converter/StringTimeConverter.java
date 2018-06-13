//package com.dsb.freemark.converter;
//
//import java.util.Date;
//
//import org.springframework.core.convert.converter.Converter;
//
//import com.navidata.util.DateFormatUtil;
//
//public class StringTimeConverter implements Converter<String, Date>  {
//
//    public StringTimeConverter() {
//        super();
//    }
//
//    @Override
//    public Date convert(String source) {
//    	if(source == null || source.trim().isEmpty()){
//    		return null;
//    	}
//    	
//        return DateFormatUtil.getDate(source);
//    }
//}
//
//
//
//
