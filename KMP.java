/*
*	给定两个字符串str和match，长度分别为N和M。实现一个算法，如果字符串str中含有字串match，则返回match在str中的开始位置，不含有则返回-1。
*	如果match的长度大于str长度(M>N)，str必然不会含有match，可直接返回-1。但如果N>=M，要求算法复杂度O(N)。
*	
*	思路：KMP中有一个next辅助数组，它与match数组等长；它的作用是记录match数组i元素之前的字符串中前缀与后缀相等的最大长度；
*			eg: match = 'aaab' next = {-1, 0, 1, 2}; 第一个元素对应的next中元素默认为-1；由于前缀必须包含第一个字符，
			同时又不能包含最后一个字符；后缀必须包含最后一个字符，同时又不能包含第一个字符。所以next[2] = 1 
*
**/

public class KMP {
	public static int getIndexOf(String s, String m) {
		if(s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while(si < ss.length && mi < ms.length) {
			if(ss[si] == ms[mi]) {
				si++;
				mi++;
				// 如果mi是match中的第一个字符，si就移动到下一个字符开始匹配，mi就不用移动了
			} else if(next[mi] == -1) {
				si++;
				// 如果mi不是第一个字符，mi移动到next[mi]处开始匹配
			} else {
				mi = next[mi];
			}
		}
		// 如果遍历完数组之后，mi到match尾，返回si-mi作为地址
		return mi == ms.length ? si - mi : -1;
	}
	
	public static int[] getNextArray(char[] ms) {
		if(ms.length == -1) {
			return new int[]{-1};
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while(pos < next.length) {
			// 检查前后缀长度，在前一个字符的前后缀长度的基础上进行检查。
			// 直接检查前一个字符，和前一个字符的最大前缀的后一个字符是否相等
			if(ms[pos-1] == ms[cn]) {
				// 如果相等，那么当前字符的最大前后缀长度就是前一个的最大前后缀长度+1
				next[pos++] = ++cn;
			} else if (cn > 0) {
				// 如果不等，就取最大前缀的后一个字符开始判断
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next;
	}
}