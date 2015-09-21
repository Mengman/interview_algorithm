/**
* 给定两个有序数组arr1和arr2，两个数组长度都为N，求两个数组中所有数的上中位数。
* 例如：
* arr1 = {1,2,3,4};
* arr2 = {3,4,5,6};
* 一共8个数则上中位数是第4个数，所以返回3。
*
* arr1 = {0,1,2};
* arr2 = {3,4,5};
* 一共6个数则上中位数是第3个数，所以返回2。
*
* 要求：时间复杂度O(logN)
* 
*
* 思路：先考虑数组为偶数长度的情况，比较arr1的上中位数m1与arr2的上中位数m2：
* 	    如果m1 == m2: m1、m2就是合并后数组的上中位数。
*       如果m1 > m2:  那么上中位数应该在arr1的左半段和arr2的右半段之中
*	    如果m1 < m2:  那么上中位数应该在arr1的右半段和arr2的左半段之中
*	    
*		数组长度为奇数时：
*		如果m1 == m2: m1、m2就是合并后数组的上中位数。
*		如果m1 > m2:  那么上中位数应该在arr1的左半段和arr2的右半段之中（都要包含当前的上中位数）
*		如果m1 < m2:  那么上中位数应该在arr1的右半段和arr2的左半段之中（都要包含当前的上中位数）
*		虽然 在第二种情况的时候m1不可能是最后的上中位数，第三种情况m2不是最后的上中位数，但是为了方便二分，将它们继续加入下一次递归中。
**/

public class UpMedianFinder {
	public static int getUpMedian(int[] arr1, int[] arr2) {
		if(arr1 == null || arr2 == null || arr1.length != arr2.length) {
			throw new RuntimeException("Your arr is invalid!");
		}
		
		return findProcess(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
	}
	
	public static findProcess(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
		if(start1 == end1) {
			return Math.min(arr1[start1], arr2[start2]);
		}
		
		// 元素个数为奇数，则offset为0；元素个数为偶数，则offset为1；
		int offset = ( (end1 - start1 + 1) & 1 ) ^ 1; // “^”是异或运算
		int mid1 = (start1 + end1) / 2;
		int mid2 = (start2 + end2) / 2;
		if(arr1[mid1] > arr2[mid2]) {
			return findProcess(arr1, start1, mid1, arr2, mid2 + offset, end2);
		} else if(arr1[mid1] < arr2[mid2]) {
			return findProcess(arr1, mid1 + offset, end1, arr2, start1, mid2);
		} else {
			return arr1[mid1];
		}
	}
}