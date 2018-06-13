package com.dsb.freemark.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented//注解会显示在生成的文档中
@Retention(RetentionPolicy.RUNTIME)//保留级别为运行时
@Inherited//该注解可被子类继承
@Target(ElementType.FIELD)//这里表示类的类型
public @interface ExportField {
	/**
	 * 名称
	 * @return
	 */
	public String name();
	
	/**
	 * 序号
	 * @return
	 */
	public int index() default 1000;
}
