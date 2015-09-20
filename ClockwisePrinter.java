// 给定一个矩阵，实现顺时针打印

/**
*	思路：从外向内，一圈一圈的打印矩阵。
*	用两个整数来记录当前打印点的位置，
*	一开始，打印点从左上角打印到右上角，然后再从右上角打印到右下角，
*	再从右下角打印到左下角，最后从左下角打印到左上角。
*	然后移动到内层一圈继续上述打印步骤。
*
**/
public class ClockwisePrinter{
	public static void spiralOrderPrint(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		
		while(tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}
	
	public static void printEdge(int[][] mat, int tR, int tC, int dR, int dC) {
		if(tR == dR) {            // 子矩阵只有一行
			for(int i = 0; i <= dC; i++) {
				System.out.print(mat[tR][i] + " ");
			}
		} else if(tC == dC) {      // 只矩阵只有一列
			for(int i = 0; i <= dR; i++)
				System.out.print(mat[i][tC] + " ");
		} else {
			int curR = tR;
			int curC = tC;
			// 从左上角移动到右上角
			while(curC != dC) {
				System.out.print(mat[curR][curC++] + " ");
			}
			// 从右上角移动到右下角
			while(curR != dR) {
				System.out.print(mat[curR++][curC] + " ");
			}
			// 从右下角移动到左下角
			while(curC != tC) {
				System.out.print(mat[curR][curC--] + " ");
			}
			// 从左下角移动到左上角
			while(curR != tR) {
				System.out.print(mat[curR--][curC] + " ");
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		int[][] matrix = { 
			{ 1, 2, 3, 4 }, 
			{ 5, 6, 7, 8 }, 
			{ 9, 10, 11, 12 },
			{ 13, 14, 15, 16 } 
		};
		spiralOrderPrint(matrix);
	}
}