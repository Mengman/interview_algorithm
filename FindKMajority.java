// 问题：在一个无序数据组中找到重复超过N/k次的元素

/**
*	思路：举例，k=2时，遍历一次数据，每次删掉两个不同的元素，则最后剩下的元素就有可能是要找的数字。
*	最后，再遍历一次数组，确认候选数字是否是要找的数字。
*	
*	当k为大于2的任意数字时，需要记录K-1个候选数字，而每次要删掉K个不同的元素，最后在遍历数组，
*	确认候选数字。
*
**/
import java.util.*;

public class FindKMajority {
	public static void printHalfMajor(int[] arr) {
		int cand = 0;
		int times = 0;
		for(int i = 0; i != arr.length; i++) {
			if(times == 0) {
				cand = arr[i];
				times = 1;
			} else if(arr[i] == cand) {
				times++;
			} else {
				times--;
			}
		}
		times = 0;
		
		// 检查候选数字是否确实是重复了超过N/2次
		for(int i = 0; i != arr.length; i++) {
			if(arr[i] == cand) {
				times++;
			}
		}
		if(times > arr.length / 2) {
			System.out.println(cand);
		} else {
			System.out.println("no such number.");
		}
	}
	
	public static void printKMajor(int[] arr, int k) {
		if(K < 2) {
			System.out.println("The value of K is invalid.");
			return ;
		}
		Map<Integer, Integer> cands = new HashMap<Integer, Integer>();
		for(int i = 0; i != arr.length; i++) {
			if(cands.containsKey(arr[i])) {
				cands.put(arr[i], cands.get(arr[i]) + 1);
			} else {
				// 同时删除K个不同的数
				if(cands.size() == K - 1) {
					allCandsMinusOne(cands);
				} else {
					cands.put(arr[i], 1);
				}
			}
		}
		Map<Integer, Integer> reals = getReals(arr, cands); // 检查候选数字真正的重复次数
		boolean hasPrint = false;
		for(Entry<Integer, Integer> set : cands.entrySet()) {
			Integer key = set.getKey();
			if(reals.getKey(key) > arr.length / K) {
				hasPrint = true;
				System.out.print(key + " ");
			}
		}
		System.out.println(hasPrint ? "" : "no such number.");
	}
	
	public static void allCandsMinusOne(Map<Integer, Integer> map) {
		List<Integer> removeList = new LinkedList<Integer>();
		for(Entry<Integer, Integer> set : map.entrySet()) {
			Integer key = set.getKey();
			Integer value = set.getValued();
			// 删除times=0的候选数字
			if(value == 1) {
				removeList.add(key);
			}
			map.put(key, value - 1);
		}
		
		for(Integer removeKey : removeList) {
			map.remove(removeKey);
		}
	}
	
	public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
		HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
		for(int i = 0; i != arr.length; i++) {
			int curNum = arr[i];
			if(cands.containsKey(curNum)) {
				// 检查候选数字真正的重复次数
				if(reals.containsKey(curNum)) {
					reals.put(curNum, reals.get(curNum) + 1);
				} else {
					reals.put(curNum, 1);
				}
			}
		}
		return reals;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 1, 1, 2, 1};
		printHalfMajor(arr);
		printKMajor(arr, 4);
	}
}