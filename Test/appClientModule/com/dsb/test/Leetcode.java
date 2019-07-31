package com.dsb.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode {
    
    public static void main(String[] args) {
        // 无重复字符的最长子串
        System.out.println(lengthOfLongestSubstring1("pwwkew"));
    }
    
    
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int length = s.length();
        Set<Character> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            set.add(s.charAt(i));
            for (int j = i+1; j < length; j++) {
                if (!set.add(s.charAt(j))) {
                    break;
                }
            }
            int size = set.size();
            max = max > size ? max :size;
            set.clear();
        }
        return max;
    }
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

}
