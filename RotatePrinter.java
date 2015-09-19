// 给定一个n x m的矩阵，如何实现转圈答应？

public class RotatePrinter{
	public static void rotate(int[][] matrix) {
		int tR = 0;
		int tC = 0;						// 左上角元素起始位置
		int dR = matrix.length - 1;      
		int dC = matrix[0].length - 1;	// 右下角元素起始位置
		
		while (tR < dR) {  // 左上角元素的行号大于右下角的行号就停止
			//左上角元素向右下角移动，右下角元素向左上角移动
			rotateEdge(matrix, tR++, tC++, dR--, dC--); 
		}
	}
	
	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC){
		int times = dC - tC; // times就是总共的组数
		int tmp = 0;
		for(int i = 0; i != times; i++) {
			tmp = m[tR][tC + i];
			m[tR][tC + i] = m[dR - i][tC];
			m[dR - i][tC] = m[dR][dC - i];
			m[dR][dC - i] = m[tR + i][dC];
			m[tR + i][dC] = tmp;
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i != matrix.length; i++) {
			for(int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		int[][] matrix = { 
			{ 1, 2, 3, 4 }, 
			{ 5, 6, 7, 8 }, 
			{ 9, 10, 11, 12 },
			{ 13, 14, 15, 16 } 
		};
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("============");
		printMatrix(matrix);
	}
}