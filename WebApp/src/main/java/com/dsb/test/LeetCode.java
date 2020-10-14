package com.dsb.test;

import java.math.BigDecimal;

public class LeetCode {
	
	public static void main(String[] args) {
//		System.out.println(reverse(-2147483648));
//		System.out.println(reverse2(442231));
		
		ListNode node = new LeetCode().new ListNode(1).next = new LeetCode().new ListNode(2).next = new LeetCode().new ListNode(3).next = new LeetCode().new ListNode(4);
		ListNode curr = node;
        while (curr != null) {
        	System.out.println(curr.val);
        	curr = curr.next;
        }
	}
    
    /**
     * <p>Title: 翻转整数</p> 
     * <p>Description: </p> 
     * @date 2019-10-21 14:10:30 
     * @author dsb
     * @param x
     * @return
     */
	public static int reverse(int x) {
		String xStr = String.valueOf(Math.abs(Long.valueOf(x)));
		int len = xStr.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = len-1; i >= 0; i--) {
			sb.append(xStr.charAt(i));
		}
		BigDecimal bdx = new BigDecimal(sb.toString());
		if (bdx.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE))>0 || bdx.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE))<0) {
			return 0;
		}
		return x < 0 ? -Integer.valueOf(sb.toString()) : Integer.valueOf(sb.toString());
	}
    public static int reverse2(int x) {
        long rs = 0;
        while(x != 0){
            rs = rs*10+x%10;
            x /= 10;
            System.out.println("rs="+rs+"\tx="+x);
            System.out.println("==================");
        }
        return (rs<Integer.MIN_VALUE || rs>Integer.MAX_VALUE) ? 0:(int)rs;
    }
    /**
     * 
     * @author dsb
     * @ClassName: ListNode 
     * @Description: 翻转单链表
     * @date 2019-10-21 16:49:45 
     *
     */
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
    public static ListNode reverseListIterative(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            curr = nextTemp; //当前指针后移
        }
        return prev;
    }
    public static ListNode reverseListIterative2(ListNode head) {
		return null;
    }
}
