package com.dsb.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 提成方案类型
 * @author Created by LDH
 * @date 2019/6/5 8:54
 */
@Entity
public class PushMoneyStoreType implements Serializable {
    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 门店提成ID */
    private Long pushMoneyStoreId;
    /** 最小区间 */
    private BigDecimal miniNumber;
    /** 最大区间 */
    private BigDecimal maxNumber;
    /** 提成额 */
    private BigDecimal pushMoney;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /** 软删除 0：否 1：是 */
    private Integer isDelete;
    
    public static void main(String[] args) {
        List<PushMoneyStoreType> list = new ArrayList<PushMoneyStoreType>(){{
            add(new PushMoneyStoreType(BigDecimal.ZERO, BigDecimal.valueOf(99.00), BigDecimal.valueOf(1.00)));
            add(new PushMoneyStoreType(BigDecimal.valueOf(100.00), BigDecimal.valueOf(199.00), BigDecimal.valueOf(2.00)));
            add(new PushMoneyStoreType(BigDecimal.valueOf(200.00), BigDecimal.valueOf(399.00), BigDecimal.valueOf(3.00)));
        }};
        System.out.println(getMoneyByLimit(BigDecimal.valueOf(37196.42), BigDecimal.valueOf(37196.42), list, 1));
    }
    
    private static BigDecimal getMoneyByLimit(BigDecimal number1, BigDecimal number2, List<PushMoneyStoreType> pushMoneyStoreTypes, Integer type) {
        number1 = number1.setScale(0, BigDecimal.ROUND_DOWN);
        for (int i = 0, len = pushMoneyStoreTypes.size(), last = len-1; i < len; i++) {
            PushMoneyStoreType pushMoneyStoreType = pushMoneyStoreTypes.get(i);
            // 等于的值是否提成
            if (number1.compareTo(pushMoneyStoreType.getMiniNumber()) < 0) {
                return BigDecimal.ZERO;
            } else if (number1.compareTo(pushMoneyStoreType.getMiniNumber()) >= 0 && number1.compareTo(pushMoneyStoreType.getMaxNumber()) <= 0) {
                return type.equals(1)?pushMoneyStoreType.getPushMoney():pushMoneyStoreType.getPushMoney().divide(BigDecimal.valueOf(100)).multiply(number2);
            } else if (i == last && number1.compareTo(pushMoneyStoreType.getMaxNumber()) >= 0){
                // number 大于最后一栏时按最后一栏算
                return type.equals(1)?pushMoneyStoreType.getPushMoney():pushMoneyStoreType.getPushMoney().divide(BigDecimal.valueOf(100)).multiply(number2);
            }
        }
        return BigDecimal.ZERO;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPushMoneyStoreId() {
        return pushMoneyStoreId;
    }
    public void setPushMoneyStoreId(Long pushMoneyStoreId) {
        this.pushMoneyStoreId = pushMoneyStoreId;
    }
    public BigDecimal getMiniNumber() {
        return miniNumber;
    }
    public void setMiniNumber(BigDecimal miniNumber) {
        this.miniNumber = miniNumber;
    }
    public BigDecimal getMaxNumber() {
        return maxNumber;
    }
    public void setMaxNumber(BigDecimal maxNumber) {
        this.maxNumber = maxNumber;
    }
    public BigDecimal getPushMoney() {
        return pushMoney;
    }
    public void setPushMoney(BigDecimal pushMoney) {
        this.pushMoney = pushMoney;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    public PushMoneyStoreType(BigDecimal miniNumber, BigDecimal maxNumber,BigDecimal pushMoney) {
        this.miniNumber = miniNumber;
        this.maxNumber = maxNumber;
        this.pushMoney = pushMoney;
    }
    public PushMoneyStoreType() {}
}
