package com.dsb.freemark.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented // ע�����ʾ�����ɵ��ĵ���
@Retention(RetentionPolicy.RUNTIME) // ��������Ϊ����ʱ
@Inherited // ��ע��ɱ�����̳�
@Target(ElementType.FIELD) // �����ʾ�������
public @interface QueryField {

	/**
	 * Ŀ���ֶ�����
	 * 
	 * @return
	 */
	String targetColumn() default "";

	/**
	 * ��ѯ��ʽ
	 * 
	 * @return
	 */
	QueryType queryType() default QueryType.EQ;

	/**
	 * �Ƿ��ǽ���ֶ�;������ǣ��������ʵ�����ת�������н����Ը��ֶ�
	 * 
	 * @return
	 */
	boolean resultField() default false;

	/**
	 * ��ѯ��ʽ
	 *
	 */
	public enum QueryType {
		/**
		 * like
		 */
		LIKE,
		/**
		 * %LIKE
		 */
		LIKE_LEFT,
		/**
		 * LIKE%
		 */
		LIKE_RIGHT,
		/**
		 * %LIKE%
		 */
		LEFT_LIKE_RIGHT,
		/**
		 * >=
		 */
		GT,
		/**
		 * <=
		 */
		ST,
		/**
		 * =
		 */
		EQ,
		/**
		 * <>
		 */
		NEQ 
	}

}