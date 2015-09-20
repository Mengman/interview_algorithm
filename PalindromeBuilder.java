//给定一个字符串str， 和这个字符串的最长子回文strLPS
//返回一个字符串，在str的基础上添加最少的字符，使其成为回文。


/**
*	思路： str中加载两个回文之间的字符不是回文，于是可以分别在两边插入另一边的倒序字符串。
*	这样处理方式就像剥洋葱一样。 最后处理完后的字符串长度为： 2 * str.length - strLPS.length
*
**/

public class PalindromeBuilder {
	public static String getPalindrome(String str, String strlps) {
		if(str == null || str.equals("")) {
			return "";
		}
		
		char[] chas = str.toCharArray();
		char[] lps = strlps.toCharArray();
		char[] res = new char[2 * chas.length - lps.length];
		int chasl = 0;
		int chasr = chas.length - 1;
		int lpsl = 0;
		int lpsr = lps.length - 1;
		int resl = 0;
		int resr = res.length - 1;
		int tmpl = 0;
		int tmpr = 0;
		
		while(lpsl <= lpsr) {
			tmpl = chasl;
			tmpr = chasr;
			while(chas[chasl] != lps[lpsl]) {
				chasl++;
			}
			while(chas[chasr] != lps[lpsr]) {
				chasr--;
			}
			set(res, resl, resr, chas, tmpl, chasl, chasr, tmpr);
			// 更新结果字符串插入点位置
			resl += chasl - tmpl + tmpr - chasr;
			resr -= chasl - tmpl + tmpr - chasr;
			// 插入回文字符
			res[resl++] = chas[chasl++];
			res[resr--] = chas[chasr--];
			
			lpsl++;
			lpsr--;
		}
		return String.valueOf(res);
	}
	
	/**
	*	向结果字符串中插入字符
	*	@param res 结果字符串
	*	@param resl 结果字符串左侧插入点
	*	@param resr 结果字符串右侧插入点
	*	@param chas 原字符串
	*	@param ls	原字符串当前层，左侧回文区间起始点
	*	@param le	原字符串当前层，左侧回文区间终止点
	*	@param rs	原字符串当前层，右侧回文区间起始点
	*	@param re	原字符串当前层，右侧回文区间终止点
	*
	**/
	public static void set(char[] res, int resl, int resr, char[] chas, 
							int ls, int le, int rs, int re) {
		// 处理原字符串左侧
		for(int i = ls; i < le; i++) {
			res[resl++] = chas[i];
			res[resr--] = chas[i];
		}
		
		// 处理原字符串右侧
		for(int i = re; i > rs; i--) {
			res[resl++] = chas[i];
			res[resr--] = chas[i];
		}
	}
	
	public static void main(String[] args) {
		String str = "AB1C2DE34F3GHJ21KL";
		String strLPS = "1234321";
		System.out.println(str);
		String res = getPalindrome(str, strLPS);
		System.out.println(res);
	}
}