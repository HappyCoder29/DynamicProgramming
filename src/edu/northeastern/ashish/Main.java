package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
//        int[][] arr = {
//                        { 1,10,3,5  },
//                        { 8,7,4,3  },
//                        { 2,3,1,2  },
//                        { 6,3,1,8  }
//                     };
//
//        System.out.println(getMinPathDP(arr));

//        int[] arr = {5,-2, 3, -7, 8,1,-15};
//
//        System.out.println(kadane(arr));

//        int[] arr = {3,10,5,4,6, -2};
//        System.out.println( longestIncreasingSubSequence(arr));

        //longestCommonSubstring("ABSTRIS", "AISSTRB");

        MaxSubSequence("abcdgh", "aedfhr");


    }

    public static int getMinPath(int[][] arr){
        return  getMinPath(arr, arr[0].length -1, arr[1].length -1 );

    }

    private static  int getMinPath(int[][] arr, int row, int col){

        if(row < 0 || col < 0){
            return  Integer.MAX_VALUE;
        }
        else if(row == 0 && col == 0){
            return  arr[row][col];
        }else{

            return  arr[row][col] + Math.min (  getMinPath(arr, row-1,col),
                                                Math.min(   getMinPath(arr, row,col-1),
                                                            getMinPath(arr, row-1,col-1))
                                            );
        }

    }

    public static int getMinPathDP(int[][] arr){
        int row = arr[0].length;
        int col = arr[1].length;

        int[][] table = new int[row][col];
        table[0][0] = arr[0][0];

        for(int i = 1 ; i < row; i ++){
            table[i][0] = table[i-1][0] + arr[i][0];
        }

        for(int i = 1 ; i < col; i ++){
            table[0][i] = table[0][i-1] + arr[0][i];
        }

        for(int i = 1; i < row; i ++){
            for(int j = 1; j < col; j ++){
                table[i][j] = arr[i][j] +  Math.min(    table[i-1][j-1],
                                                        Math.min(   table[i-1][j],
                                                                    table[i][j-1])
                                                    );
            }
        }

        return  table[row-1][col-1];

    }

    public static int kadane(int[] arr){

        int maxSum = arr[0];
        int sum = arr[0];


        for(int i = 1; i < arr.length; i ++){
            sum = sum + arr[i];

            if(sum > maxSum){
                maxSum = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }

        return  maxSum;


    }

    public  static int longestIncreasingSubSequence(int[] arr){
        int[] lis = new int[arr.length];
        int[] indexes = new int[arr.length];

        //O(n)
        for(int i = 0 ; i < arr.length; i ++){
            lis[i] = 1;
            indexes[i] = i;
        }


        //O(n^2)
        for(int i = 1 ; i < arr.length; i ++ ){
            for(int j = 0 ; j < i-1; j ++){
                if( arr[i] > arr[j]){

                    if(lis[j] + 1 > lis[i]){
                        lis[i] = lis[j] + 1;
                        indexes[i] = j;
                    }

                }
            }
        }


        return  lis[arr.length -1];
    }

    static int LongestBittonic(int[] arr){
        int[] lis = new int[arr.length];
        int[] lds = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            lis[i] = 1;
            lds[i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <  i-1; j++) {
                if(arr[i] > arr[j] && lis[i] < lis[j] +1){
                    lis[i] = lis[j] +1;

                }
            }
        }

        for (int i = arr.length -1; i >= 0; i--) {
            for (int j = arr.length -1; j > i ; j--) {
                if(arr[i] > arr[j] ){
                    lds[i] = Math.max( lds[i] , lds[j] +1);
                }
            }
        }
        int max = 0 ;

        for (int i = 1; i < arr.length; i++) {
            if(max < lis[i] + lds[i] -1)
                max = lis[i] + lds[i] -1;
        }

        return  max;

    }

    static void longestCommonSubstring(String str1, String str2){

        int row = str1.length();
        int col = str2.length();

        int[][] matrix = new int[row +1][col+1];

        int max = 0;
        int endIndex = 0;

        for(int i = 0 ; i < row; i ++)
            matrix[i][0] = 0;
        for(int i = 0 ; i < col; i ++)
            matrix[0][i] = 0;

        for (int i = 1; i <  row +1; i++) {
            for (int j = 1; j <  col +1; j++) {

                if(str1.charAt(i-1) ==  str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1] +1;

                    if(max <  matrix[i][j]){
                        max = matrix[i][j];
                        endIndex = i;
                    }
                }
                else{
                    matrix[i][j] = 0;
                }

            }

        }

        System.out.println("Max = " + max);

        System.out.println(str1.substring(endIndex - max, endIndex));

    }

    static void MaxSubSequence(String str1, String str2){


        int row = str1.length();
        int col = str2.length();

        int[][] matrix = new int[row+1][col+1];

        for(int i = 0 ; i < row; i ++)
            matrix[i][0] = 0;
        for(int i = 0 ; i < col; i ++)
            matrix[0][i] = 0;




        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <  row +1; i++) {
            for (int j = 1; j <  col +1; j++) {

                if(str1.charAt(i-1) ==  str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1] +1;
                    sb.append(str1.charAt(i-1));

                }
                else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }

        }

        System.out.println("Max = " + matrix[row][col]);
        System.out.println("String = " + sb.toString());
    }







}
