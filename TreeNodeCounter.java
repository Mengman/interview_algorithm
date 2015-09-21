/**
*	给定一棵完全二叉树的头节点head，返回这棵树的节点个数。
*	如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
*	
*	思路：利用分治法，判断左右子树是否是满二叉树，如果是满二叉树，直接计算出其节点数；
*	如果不是满二叉树，那么继续对该子树进行判断。
*	时间复杂度应为 O(logN)
**/

public class TreeNodeCounter{
	
	public int countNode(Node head){
		if(head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}
	
	/**
	* 递归计算子树的高度
	*
	* @param node  子树根节点
	* @param l	   当前子树根节点，在原树中的高度
	* @param h	   原树的高度
	**/
	public int bs(Node node, int l, int h) {
		if(l == h) {
			return 1;
		}
		
		// 检查右子树高度
		if(mostLeftLevel(node.right, l + 1) == h) {
			// 这里计算节点数不减1的原因是，加上了当前子树的根节点
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
		
	}
	
	/**
	* 返回二叉树最左节点的高度
	*
	* @param node  根节点
	* @param level 当前子树根节点，在原树中的高度
	**/
	public int mostLeftLevel(Node node, int level) {
		while(node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}
}

class Node{
	public Node left = null;
	public Node right = null;
	private int value;
	
	public Node(int v){
		value = v;
	}
	
	public Node(int v, Node left, Node right) {
		this(v);
		this.left = left;
		this.right = right;
	}
}
