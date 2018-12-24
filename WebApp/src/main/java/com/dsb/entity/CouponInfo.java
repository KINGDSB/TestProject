package com.dsb.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 集团优惠券信息表
 * @author Created by DSB
 * @date 2018/11/12 10:16
 */
@Data
public class CouponInfo {

    /**
     * 优惠券id
     */
    private Long id;

    /** 券分类 1.微信优惠券 */
    private Integer category;

    /** 优惠券标题 */
    private String title;

    /** 背景图地址 */
    private String background;

    /** 券类型
     1.满减券：适用于指定商品金额满多少减多少 订单金额> number1 时 - number2
     2.立减券：不设消费门槛，指定商品买单即减 订单金额> number1(0) 时 - number2
     3.折扣券：指定商品满多少件总金额打多少折 订单金额> number1 时 X number2 X 0.1
     */
    private Integer type;

    /** 数字1 单位(元|件) */
    private BigDecimal number1;

    /** 数字2 单位(元|几折) */
    private BigDecimal number2;

    /** 有效期类型：
     1.固定区间：当前时间在 begin_date 到 end_date 之间时可使用
     2.固定时长：领取时间+begin_day 到 领取时间+begin_day+effective_day 之间时可使用 */
    private Integer timeLimitType;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    /** 领取后多少天生效 */
    private Integer beginDay;

    /** 有效天数 */
    private Integer effectiveDay;

    /** 库存数量 */
    private Integer amount;

    /** 已发送数量 */
    private Integer sended;

    /** 已核销/已使用数量 */
    private Integer used;

    /** 使用说明 */
    private String direction;

    /**
     * 条形码图片地址
     * 条形码信息为 1(集团端券)+id
     * */
    private String barcode;

    /**
     * 二维码图片地址
     * 二维码信息为 1(集团端券)+id
     * */
    private String qrcode;

    /** 是否可退 1.不可退 2.可退  */
    private Integer canReturn;

    /** 状态 1.开启 2.关闭 */
    private Integer couponStatus;

    /** 发送时间 */
    private Date sendTime;

    /** 发送时的文本内容 */
    private String remark;

    /** 创建人id (ehr用户id)*/
    private String creatorId;

    /** 操作人id/发券人 (ehr用户id)*/
    private String operatorId;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 软删除 0:有效 1:无效 */
    private Integer isDelete;

}
