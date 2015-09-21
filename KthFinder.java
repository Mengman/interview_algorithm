/**
* 给定两个有序数组arr1和arr2，在给定一个整数k，返回两个数组的所有数中第K小的数。
* 例如：
* arr1 = {1,2,3,4,5};
* arr2 = {3,4,5};
* K = 1;
* 因为1为所有数中最小的，所以返回1；
* arr1 = {1,2,3};
* arr2 = {3,4,5,6};
* K = 4;
* 因为3为所有数中第4小的数，所以返回3；
* 要求：如果arr1的长度为N，arr2的长度为M，时间复杂度请达到O(log(min{M,N}))。
*
* 思路：设短数组长度为lenS，长数组长度为lenL；
*       如果 K < lenS ,那么从分别从两个数组中拿出前k个数，求它们的前中位数。
*		
*		如果 lenL < k < lenS + lenL, 那么短数组的前(K - lenL - 1)个数不可能是第k小的数；
*		长数组前(K - lenS - 1)个数不可能是第k小的数；这个时候答案在这两个数组的后(lenS + lenL - K + 1)个数字中；
*		在剩下的数组中求上中位数得到的是第K+1个数,同时为了保证(K=lenS + lenL)的情况，先验证两个数组的第(K - lenL)和第(K - lenS)个数；
*		如果短数组的第(K - lenL)大于长数组的最后一个数，那么它就是第K个数；
*		如果长数组的第(K - lenS)个数大于短数组的最后一个数，那么它就是第K个数；
*		否则，淘汰这两个数，从(K - lenL + 1)和(K - lenS + 1)中找上中位数。
*
*		如果 lenS < K < lenL, 那么短数组中的所有数都有可能是第K个数，长数组中前(K - lenS - 1)和从第(K + 1)个数开始的后面所有数都不可能。
*		则需要在长数组(K - lenS)~K之间去找一共有(lenS + 1)个数；先判断第(K - lenS)数，如果它大于短数组的最后一个数，那么它就是第K个数，
*		否则，就在短数组和长数组(K - lenS + 1)~K之间去找上中位数。
**/

public class KthFinder{
	public static int findKthNum (int[] arr1, int[] arr2, int kth) {
		if(arr1 == null || arr2 == null) {
			throw new RunntimeException("Your arr is invalid!");
		}
		
		if(kth < 1 || kth > arr1.length + arr2.length) {
			throw new RunntimeException("K is invalid");
		}
		
		int[] longArr = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shortArr = arr1.length < arr2.length ? arr1 : arr2;
		int lenL = longArr.length;
		int lenS = shortArr.length;
		
		if(kth <= lenS) {
			return getUpMedian(shortArr, 0, kth - 1, longArr, 0, kth - 1);
		}
		
		if(kth > lenL) {
			if(shortArr[kth - lenL - 1] >= longArr[lenL - 1]) {
				return shortArr[kth - lenL - 1];
			}
			if(longArr[kth - lenS - 1] >= shortArr[lenS - 1]) {
				return longArr[kth - lenS - 1];
			}
			return getUpMedian(shortArr, kth - lenL, lenS - 1, longArr, kth - lenS, lenL - 1);
		}
		
		if(longArr[kth - lenS - 1] >= shortArr[lenS - 1]) {
			return longArr[kth - lenS - 1];
		}
		
		return getUpMedian(shortArr, 0, lenS - 1, longArr, kth - lenS, kth - 1);
	}
	
	
	public static int getUpMedian(int[] arr1, int[] arr2) {
		if(arr1 == null || arr2 == null || arr1.length != arr2.length) {
			throw new RuntimeException("Your arr is invalid!");
		}
		
		return findProcess(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
	}
	
	public static int getUpMedian(int[] arr1, int start1, int end1, int[] arr2,
			int start2, int end2) {
		if (start1 == end1) {
			return Math.min(arr1[start1], arr2[start2]);
		}
		int offset = ((end1 - start1 + 1) & 1) ^ 1;
		int mid1 = (start1 + end1) / 2;
		int mid2 = (start2 + end2) / 2;
		if (arr1[mid1] > arr2[mid2]) {
			return getUpMedian(arr1, start1, mid1, arr2, mid2 + offset, end2);
		} else if (arr1[mid1] < arr2[mid2]) {
			return getUpMedian(arr1, mid1 + offset, end1, arr2, start2, mid2);
		} else {
			return arr1[mid1];
		}
	}
}