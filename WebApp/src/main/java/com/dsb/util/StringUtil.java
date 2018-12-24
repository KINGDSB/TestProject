package com.dsb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dsb.entity.UseCouponProductDTO;

public class StringUtil {

    public static void main(String[] args) {
        // 允许的分类
        List<String> allowProductCodes = null;
        // 允许的品牌
        List<String> allowProductBrands = Arrays.asList(new String[]{"brand2"});
        // 允许的sku
        List<String> allowProductSKUs = Arrays.asList(new String[]{"119541"});
        // 不允许的sku
        List<String> notAllowProductSKUs = null;
        
        List<UseCouponProductDTO> list = Arrays.asList(new UseCouponProductDTO[]{new UseCouponProductDTO("119541","brand1","category1"),});
        for (UseCouponProductDTO product : list) {
            /**
             * 允许使用优惠券的商品需满足以下条件
             * (1.不限制分类或者该商品属于适用分类 ||
             * 2.不限制品牌或者该商品属于适用品牌 ||
             * 3.该商品属于允许的sku) &&
             * 4.该商品不属于不允许的sku
             */
            if ((!(null != allowProductCodes && !allowProductCodes.contains(product.getProductCategory())) ||
                !(null != allowProductBrands && !allowProductBrands.contains(product.getBrand())) ||
                !(null != allowProductSKUs && !allowProductSKUs.contains(product.getProductCode()))) ||
                (null == notAllowProductSKUs || !notAllowProductSKUs.contains(product.getProductCode()))) {
                System.out.println("ok : "+ product.getProductCode());
            }
        }
    }
    
}
