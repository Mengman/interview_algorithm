// 给定一个矩阵，实现“之”字打印

public class ZigZagPrinter{
	public static void printMatrixZigZag(int[][] matrix) {
		//“之”字的上顶点
		int tR = 0;
		int tC = 0;
		
		//“之”字的下顶点
		int dR = 0;
		int dC = 0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		
		boolean fromUp = false;
		while(tR != endR + 1) {
			printLevel(matrix, tR, tC, dR, dC, fromUp);
			// 如果“上顶点”已经到达最右边，则向下移动一行, 列号不变
			// 如果没有，继续向右移动一列
			tR = tC == endC ? tR + 1 : tR;  
			tC = tC == endC ? tC : tC + 1;
			
			// 如果“下顶点”已经达到最下边，则向右移动一列
			// 如果没有，继续向下移动一行
			dC = dR == endR ? dC + 1: dC;
			dR = dR == endR ? dR : dR + 1;
			fromUp = !fromUp; // 变换打印方向
		}
		System.out.println();
	}
	
	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
		if(f) {
			while(tR != dR + 1) {
				// 从上向下打印
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while(dR != tR - 1) {
				// 从下向上打印
				System.out.print(m[dR--][dC++] + " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[][] matrix = { 
			{ 1, 2, 3, 4 }, 
			{ 5, 6, 7, 8 }, 
			{ 9, 10, 11, 12 } 
		};
		printMatrixZigZag(matrix);
	}
}