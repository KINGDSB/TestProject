package com.dsb.entity;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 金根那边来的 Product.cs
 * @author Created by DSB
 * @date 2018/11/12 10:16
 */
@Data
public class UseCouponProductDTO {

    @JsonProperty("Guid")
    private String guid;

    /// <summary>
    /// 序号
    /// </summary>
    @JsonProperty("No")
    private Integer no;

    /// <summary>
    /// 产品名称 ServiceName
    /// </summary>
    @JsonProperty("ProductName")
    private String productName;

    /// <summary>
    /// 产品Code
    /// </summary>
    @JsonProperty("ProductCode")
    private String productCode;

    /// <summary>
    /// 条形码
    /// </summary>
    @JsonProperty("BarCode")
    private String barCode;

    /// <summary>
    /// 产品数量
    /// </summary>
    @JsonProperty("Number")
    private Integer number;

    /// <summary>
    /// 商品零售单价
    /// </summary>
    @JsonProperty("UnitPrice")
    private BigDecimal unitPrice;

    /// <summary>
    /// 商品折扣后单价
    /// </summary>
    @JsonProperty("ActualBefDiscountUnitPrice")
    private BigDecimal actualBefDiscountUnitPrice;

    /// <summary>
    /// 商品折扣前单价
    /// </summary>
    @JsonProperty("ActualAftDiscountUnitPric")
    private BigDecimal actualAftDiscountUnitPric;

    /// <summary>
    /// 类别商品折扣后总价
    /// 折扣总价如果只取公式价格，则需要保证单价整除，否则会引起多出来小数位
    /// </summary>
    @JsonProperty("TotalBefDiscountAmount")
    private BigDecimal totalBefDiscountAmount;

    /// <summary>
    /// 类别商品折扣前总价
    /// </summary>
    @JsonProperty("TotalAftDiscountAmount")
    private BigDecimal totalAftDiscountAmount;

    /// <summary>
    /// 类别商品零售价总价
    /// </summary>
    @JsonProperty("TotalUnitPriceAmount")
    private BigDecimal totalUnitPriceAmount;

    @JsonProperty("ProductPromotion")
    private Integer productPromotion;

    /// <summary>
    /// 单个打折 折扣 David
    /// </summary>
    @JsonProperty("ProductDisCount")
    private BigDecimal productDisCount;

    /// <summary>
    /// 产品单个打折后价格 David
    /// </summary>
    @JsonProperty("ProductAfterDisCountPrice")
    private BigDecimal productAfterDisCountPrice;

    /// <summary>
    /// 实际折扣前单价
    /// </summary>
    @JsonProperty("ActualUnitPrice")
    private BigDecimal actualUnitPrice;

    /// <summary>
    /// 实际折扣总和
    /// </summary>
    @JsonProperty("ActualTotalAmount")
    private BigDecimal actualTotalAmount;

    /// <summary>
    /// 零售总和
    /// </summary>
    @JsonProperty("OrderTotalAmount")
    private BigDecimal orderTotalAmount;
    /// <summary>
    /// 零售单价
    /// </summary>
    @JsonProperty("OrderUnitPrice")
    private BigDecimal orderUnitPrice;
    /// <summary>
    /// 产品品牌
    /// </summary>
    @JsonProperty("Brand")
    private String brand;
    /// <summary>
    /// 单位
    /// </summary>
    @JsonProperty("Unit")
    private String unit;
    /// <summary>
    /// 尺寸
    /// </summary>
    @JsonProperty("Size")
    private String size;
    /// <summary>
    /// 产地
    /// </summary>
    @JsonProperty("H_Place")
    private String h_Place;
    /// <summary>
    /// 产品版本号
    /// </summary>
    @JsonProperty("ProductVersionID")
    private String productVersionID;
    /// <summary>
    /// 商品分类 /服务类型 ServiceType
    /// </summary>
    @JsonProperty("ProductCategory")
    private String productCategory;
    /// <summary>
    /// 商品分类名称/服务类型名称 ServiceTypeName
    /// </summary>
    @JsonProperty("ProductCategoryName")
    private String productCategoryName;
    /// <summary>
    /// 箱规
    /// </summary>
    @JsonProperty("BoxSpecification")
    private String boxSpecification;
    /// <summary>
    /// 规格/详情 ServiceDetail
    /// </summary>
    @JsonProperty("Specification")
    private String specification;
    /// <summary>
    /// 颜色
    /// </summary>
    @JsonProperty("H_Color")
    private String h_Color;
    /// <summary>
    /// 尺寸/颜色
    /// </summary>
    @JsonProperty("ColorSize")
    private String colorSize;
    /// <summary>
    /// 商品使用码
    /// </summary>
    @JsonProperty("H_UsedCode")
    private String h_UsedCode;
    /// <summary>
    /// 导购
    /// </summary>
    @JsonProperty("ShopGuideUserID")
    private String shopGuideUserID;

    /// <summary>
    /// 库存数量
    /// </summary>
    @JsonProperty("InventoryCount")
    private Integer inventoryCount;

    /// <summary>
    /// 库存价格
    /// </summary>
    @JsonProperty("InventoryPrice")
    private BigDecimal inventoryPrice;
    /// <summary>
    /// PCID
    /// </summary>
    @JsonProperty("PCID")
    private String pcId;
    /// <summary>
    /// PGID
    /// </summary>
    @JsonProperty("PGID")
    private String pgId;

    /// <summary>
    /// 门店编码
    /// </summary>
    @JsonProperty("StoreCode")
    private String storeCode;

    /// <summary>
    /// 仓库编码
    /// </summary>
    @JsonProperty("WarehouseCode")
    private String warehouseCode;

    /// <summary>
    /// 最大购买量限制
    /// </summary>
    @JsonProperty("Purchase")
    private Integer purchase;

    /// <summary>
    /// 是否有促销
    /// </summary>
    @JsonProperty("HasPromotion")
    private Boolean hasPromotion;
    /// <summary>
    /// 促销类型 1：特价 2：满减促销 3：满赠促销 4：套装促销
    /// </summary>
    @JsonProperty("PromotionType")
    private Integer promotionType;

    /// <summary>
    /// 促销显示 1：折 2：减 3：赠 4：特 5：兑
    /// </summary>
    @JsonProperty("PromotionVisible")
    private Integer promotionVisible;

    /// <summary>
    /// 是否满足促销条件
    /// </summary>
    @JsonProperty("IsPromotion")
    private Boolean isPromotion;
    /// <summary>
    /// 促销编码
    /// </summary>
    @JsonProperty("PromotionCode")
    private String promotionCode;
    /// <summary>
    /// 促销名称
    /// </summary>
    @JsonProperty("PromotionName")
    private String promotionName;
    /// <summary>
    /// 实际下单数量
    /// </summary>
    @JsonProperty("OrderCount")
    private Integer orderCount;
    /// <summary>
    /// 兑换积分规则编码
    /// </summary>
    @JsonProperty("ProductIntegralRuleCode")
    private String productIntegralRuleCode;
    /// <summary>
    /// 兑换积分规则版本
    /// </summary>
    @JsonProperty("ProductIntegralVersionID")
    private String productIntegralVersionID;
    /// <summary>
    /// 是否可积分兑换 1：是
    /// </summary>
    @JsonProperty("IsIntergralExchange")
    private Integer isIntergralExchange;

    /// <summary>
    /// 员工价
    /// </summary>
    @JsonProperty("EmployeePrice")
    private BigDecimal employeePrice;
    /// <summary>
    /// 黄金会员
    /// </summary>
    @JsonProperty("GoldPrice")
    private BigDecimal goldPrice;

    /// <summary>
    /// 钻石会员
    /// </summary>
    @JsonProperty("DiamondPrice")
    private BigDecimal diamondPrice;

    /// <summary>
    ///Col1（商品类型）1=正常商品 2=赠品 3=兑换商品
    /// </summary>
    @JsonProperty("Col1")
    private Integer col1;
    /// <summary>
    ///Col2（是否应用促）1=不应用促销 2=应用促销
    /// </summary>
    @JsonProperty("Col2")
    private Integer col2;
    /// <summary>
    /// 促销折扣金额
    /// </summary>
    @JsonProperty("PromotionalDiscountAmount")
    private BigDecimal promotionalDiscountAmount;

    /// <summary>
    /// 积分兑换规则编码
    /// </summary>
    @JsonProperty("ExchangeCode")
    private String exchangeCode;

    /// <summary>
    /// 兑换的积分值
    /// </summary>
    @JsonProperty("ExchangeIntegral")
    private Integer exchangeIntegral;
    /// <summary>
    /// 获得积分值
    /// </summary>
    @JsonProperty("AddIntegral")
    private Integer addIntegral;
    /// <summary>
    /// 来源（主数据，外采）
    /// </summary>
    @JsonProperty("ProductSource")
    private String productSource;
    /// <summary>
    /// 是否是同一促销批次
    /// </summary>
    @JsonProperty("Batch")
    private String batch;

    /// <summary>
    /// 应用促销商品价格
    /// </summary>
    @JsonProperty("PromotionPrice")
    private BigDecimal promotionPrice;
    /// <summary>
    /// 摊分金额
    /// </summary>
    @JsonProperty("PromotionOverPuls")
    private BigDecimal promotionOverPuls;

    /// <summary>
    /// 应用促销的个数
    /// </summary>
    @JsonProperty("UsePromotionNum")
    private Integer usePromotionNum;
    /// <summary>
    /// 退货时销售明细ID
    /// </summary>
    @JsonProperty("SaleDetailID")
    private String saleDetailID;
    /// <summary>
    /// 产品类型,1 服务产品 未填正常商品
    /// </summary>
    @JsonProperty("ProductType")
    private String productType;
    /// <summary>
    /// 可用促销(即该商品所有可满足的促销条件)
    /// </summary>
//    private List<H_Member_PromotionRule> ValidPromotions;
    @JsonProperty("ValidPromotions")
    private JSONArray validPromotions;

    /// <summary>
    /// 已使用的促销(已经勾选的促销)
    /// </summary>
//    private List<H_Member_PromotionRule> UsedPromotions;
    @JsonProperty("UsedPromotions")
    private JSONArray usedPromotions;

    /// <summary>
    /// 有效期 1.30天，2.90天,3.一年,4.永久
    /// </summary>
    @JsonProperty("ValidityDay")
    private Integer validityDay;
    /// <summary>
    /// 最少提前预约时间（分钟）
    /// </summary>
    @JsonProperty("MinSubscribeMinutes")
    private Integer minSubscribeMinutes;
    /// <summary>
    /// 服务开始时间
    /// </summary>
    @JsonProperty("ServiceStartTime")
    private String serviceStartTime;
    /// <summary>
    /// 服务结束时间
    /// </summary>
    @JsonProperty("ServiceEndTime")
    private String serviceEndTime;
    /// <summary>
    /// 服务次数
    /// </summary>
    @JsonProperty("ServiceTimes")
    private Integer serviceTimes;
    /// <summary>
    /// 收费方式1次数,2套餐
    /// </summary>
    @JsonProperty("FeeType")
    private Integer feeType;
    /// <summary>
    /// 购买状态 1仅线下生效 2仅线上生效 3暂停购买 4线上线下同时可购买
    /// </summary>
    @JsonProperty("ProductStatus")
    private Integer productStatus;

    /// <summary>
    /// 泳疗师ID(次卡使用)
    /// </summary>
    @JsonProperty("TherapistID")
    private String therapistID;

    public UseCouponProductDTO(String productCode, String brand, String productCategory) {
        super();
        this.productCode = productCode;
        this.brand = brand;
        this.productCategory = productCategory;
    }
    public UseCouponProductDTO() {}
}
