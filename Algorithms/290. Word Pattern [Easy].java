package com.homurax.algorithm;

import java.util.*;

/**
 * 290. Word Pattern
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 *
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 *
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 *
 * Example 4:
 *
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {


    public boolean wordPattern(String pattern, String str) {

        String[] word = str.split("\\s");

        if (pattern.length() != word.length) { return false; }

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c).equals(word[i])) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if (map.containsValue(word[i])) {
                    return false;
                }
            }
            map.put(c, word[i]);
        }

        return true;
    }


    public boolean wordPattern2(String pattern, String str) {

        int[] patternIndex = parsePattern(pattern);
        List<Integer> strIndex = parseStr(str);
        int patternLen = patternIndex.length, strLen = strIndex.size();
        if (patternLen != strLen) { return false; }

        for (int i = 0; i < patternLen; i++) {
            if (patternIndex[i] != strIndex.get(i)) {
                return false;
            }
        }

        return true;
    }

    private int[] parsePattern(String pattern) {

        int length = pattern.length();
        if (length == 0) { return new int[0]; }

        int[] indexArr = new int[length];
        int[] map = new int[26];
        int index = 1;
        for (int i = 0; i < length; i++) {
            char c = pattern.charAt(i);
            int count = map[c - 'a'];
            if (count == 0) {
                map[c - 'a'] = index;
                indexArr[i] = index;
                index++;
            } else {
                indexArr[i] = count;
            }
        }

        return indexArr;
    }

    private List<Integer> parseStr(String str) {

        char[] chars = str.toCharArray();
        if (chars.length == 0) { return Collections.emptyList(); }

        List<Integer> indexList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int begin = 0, end = 0, index = 1;

        while (true) {

            while (end < chars.length && chars[end] != ' ') { end++; }

            String s = new String(chars, begin, end - begin);
            Integer existedIndex = map.get(s);

            if (existedIndex == null) {
                map.put(s, index);
                indexList.add(index);
                index++;
            } else {
                indexList.add(existedIndex);
            }
            if (end == chars.length) {
                break;
            }
            begin = end = end + 1;
        }
        return indexList;
    }

}
