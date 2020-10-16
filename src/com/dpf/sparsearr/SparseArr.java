package com.dpf.sparsearr;

/**
 * @author dpf
 * @create 2019-10-24 9:53
 * @email 446933040@qq.com
 *
 * 稀疏数组转换
 *
 */
public class SparseArr {

    public static void main(String[] args) {


        //得到原始的二维数组
        int [][] chessArr = new int[4][4];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        System.out.println("原数组");
        for (int i = 0; i < chessArr.length ; i++) {
            for (int j = 0; j < chessArr[i].length ; j++) {
                System.out.printf("%d\t",chessArr[i][j]);
            }
            System.out.println();
        }

        //遍历  原始的二维数组，得到有效数据的个数 sum
        int sum = 0;
        for(int [] row : chessArr){
            for (int value : row){
                if(value>0){
                    sum ++;
                }
            }
        }

        //根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
        int[][] sparseArr = new int[sum+1][3];

        sparseArr[0][0] = chessArr.length;  //行
        sparseArr[0][1] = chessArr[0].length; //列
        sparseArr[0][2] = sum;

        //将二维数组的有效数据数据存入到 稀疏数组
        int count = 0 ;//用于存储存取了多少个有效数据
         for (int i = 0;i<chessArr.length;i++){
           for (int j=0;j<chessArr[i].length;j++){
               if(chessArr[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
               }
           }
       }

        System.out.println("得到的稀疏数组~~~~");
        for (int i = 0; i < sparseArr.length ; i++) {
            System.out.println(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]);
        }


        //先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        System.out.println(row);
        System.out.println(col);
        int[][] chessArr2 = new int[row][col];
        for (int i = 1; i < sparseArr.length ; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }


        System.out.println("还原后数组");
        for (int i = 0; i < chessArr2.length ; i++) {
            for (int j = 0; j < chessArr2[i].length ; j++) {
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }



    }

}
