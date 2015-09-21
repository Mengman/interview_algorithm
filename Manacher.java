/**
*	【原问题】给定一个字符串str，返回str中的最长回文子串的长度。
*	【进阶问题】给定一个字符串str，想通过添加字符的方式使得str整体都变成回文字符串，
*				但要求只能在str的末尾添加字符，请返回在str后面添加的最短字符串。
*	如果str长度为N，解决原问题和进阶问题的时间复杂度都达到O(N)。
*	
*	Mancher算法：设字符串长度为N，在str中插入(2N+1)个辅助字符‘#’，这步之后统一了奇数长度和偶数长度的问题。
*				pArr[]回文半径数组，长度等于插入辅助字符后的字符串长度，当中记录了i位置的回文半径长度(如
*				果是一个字符长度就是1，如果是‘121’那‘2’的回文半径就是2)
*				pR回文半径外的下一个字符的位置；eg：‘#1#2#1#’，最开始pR=0, 扫描0位置之后，pR=1；扫描完1位置之后pR=3；
*				index：pR更新时，回文中心的位置，如上例，pR=0时index=0;pR=1时index=0；pR=3时index=1;
*
*				在计算i位置的回文长度
					1） index < i < (pR - 1)：
						找到index有左边和i对应的i'位置，如果i'的回文半径在index回文半径内，那么i的回文半径就等于i'的回文半径；
						如果i'位置的回文半径不在index回文半径内，那么i的回文半径就是i到pR的长度。
						如果i'位置的左回文半径和index的左回文半径重叠，那么这个时候需要对i进行外扩检查，但是只需要从pR位置开始检查
					2） (pR - 1) < i:
						直接外扩检查
**/

public class Mancher{
	// 插入辅助字符
	 public char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
			// 0和偶数位插入'#',其他位置插入原str
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
	
	 public int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
			// 这一句非常的神，它完全处理了1）的前两种情况，还预处理了第三种情况
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                // 外扩检查的过程
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
			// 需要对pR进行更新的情况
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
		// 由于插入了辅助字符，所以不用乘2
        return max - 1;
    }
}