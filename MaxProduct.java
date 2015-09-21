/**
*	给定一个double类型的数组arr，其中的元素可正可负可0，返回子数组累乘的最大乘积。
*	例如arr=[-2.5，4，0，3，0.5，8，-1]，子数组[3，0.5，8]累乘可以获得最大的乘积12，所以返回12。
*
*	思路：当前位置的最大累乘结果，只能有三种情况：
*	1、前面累乘的最大值乘以当前值。
*	2、前面累乘的最小值乘以当前值（当前值和累乘最小值都为负数）。
*	3、当前值本身。
*	所以每次保存下，累乘的最大与最小值，在后续计算中继续使用。
**/

public class MaxProduct {
	
	public static double getmaxProduct(double[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		double max = arr[0];
		double min = arr[0];
		double res = arr[0];
		double maxEnd = 0;
		double minEnd = 0;
		for (int i = 1; i < arr.length; ++i) {
			maxEnd = max * arr[i];
			minEnd = min * arr[i];
			max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
			min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
			res = Math.max(res, max);
		}
		return res;
	}
	
	public static void main(String[] args) {
		double[] arr = {1, 2, 3, 4, 5};
		System.out.println(getmaxProduct(arr));
	}
}
