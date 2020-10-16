package com.dpf.sparsearr;

/**
 * @author dpf
 * @create 2020-09-08 10:23 下午
 * @email 446933040
 */
public class SparseArr2 {
    public static void main(String[] args) {

        // 获取原始数组
        int chessArr [][] = new int[4][4];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        // 获取有效数据个数
        System.out.println("--------------原数组-----------");
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                System.out.print(chessArr[i][j]+"\t");
                if(chessArr[i][j]>0){
                    sum++;
                }
            }
            System.out.println();
        }
        System.out.println("有效个数"+sum);

        // 创建稀疏数组 原数组几行几列几个有效值
        int sparseArr [][] = new int[sum+1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //给稀疏数组赋值
        int count = 0; //用于存储存取了多少个有效数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j]>0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        System.out.println("-------------稀疏数组------------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.println(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]+"\t");
        }
        System.out.println();

        //稀疏数组还原为原数组
        int oldChessArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //给原数组赋值
        for (int i = 1; i < sparseArr.length; i++) {
           oldChessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("--------------原数组-----------");

        for (int i = 0; i < oldChessArr.length; i++) {
            for (int j = 0; j < oldChessArr[i].length; j++) {
                System.out.print(oldChessArr[i][j]+"\t");
            }
            System.out.println();
        }

    }
}
