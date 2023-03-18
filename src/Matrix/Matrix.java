import java.io.*;
import java.util.*;
public class Matrix
{
	public Matrix()
	{
     	String matrix1  = "";
		String matrix2 = "";
		int ind = 0;

		File name = new File("MatrixInput.txt");
				try
				{
					BufferedReader input = new BufferedReader(new FileReader(name));
					String text,output="";
					while((text=input.readLine())!= null)
					{
					    ind = text.indexOf("}}");
						matrix1 = text.substring(0,ind+2);
						matrix2 = text.substring(ind+3,text.length());
						System.out.println("Matrix 1:");
						int[][] matrix = matrixMaker(matrix1);
						System.out.println("Matrix 2:");
						int[][] secondMatrix = matrixMaker(matrix2);
						System.out.println("Sum:");
						sum(matrix,secondMatrix);
						System.out.println("Difference:");
						difference(matrix,secondMatrix);
						System.out.println("Product:");
						product(matrix,secondMatrix);

					}

				}
				catch (IOException io)
				{
					System.err.println("Error reading file => "+io);
				}
	}

	public static int[][] matrixMaker(String str){
		int numRows = 1;
		int numCol = 1;
		ArrayList<String> num = new ArrayList<String>();

		str = str.replace("},{","_");
		str = str.replace("}","");
		str = str.replace("{","");

		for(int x = 0; x<str.length()-1; x++){
			if(str.substring(x,x+1).equals("0")||str.substring(x,x+1).equals("1")||str.substring(x,x+1).equals("2")||str.substring(x,x+1).equals("3")||str.substring(x,x+1).equals("4")||str.substring(x,x+1).equals("5")||str.substring(x,x+1).equals("6")||str.substring(x,x+1).equals("7")||str.substring(x,x+1).equals("8")||str.substring(x,x+1).equals("9")){
				numCol++;
				num.add(str.substring(x,x+1));
			}
			else{
				if(str.substring(x,x+1).equals("_")){
					numRows++;
					numCol=1;
				}

			}

		}
		num.add(str.substring(str.length()-1,str.length()));
		int z = 0;
		int[][] matrix = new int[numRows][numCol];
		for(int i = 0; i<numRows; i++){
			for(int y =0; y<numCol; y++){
				matrix[i][y]=Integer.parseInt(num.get(z));
				System.out.print("\t"+matrix[i][y]);
				z++;
			}
			System.out.println("");
	  }
	return matrix;
}

	public static void sum(int[][]mat1, int[][]mat2){
		int[][]matSum = new int[mat1.length][mat1[0].length];
		if(mat1[0].length == mat2[0].length && mat1.length == mat2.length){
			for(int i = 0; i<mat1.length; i++){
				for(int y =0; y<mat1[0].length; y++){
					matSum[i][y]=mat1[i][y]+mat2[i][y];
			  }
	        }
	        for(int x = 0; x<mat1.length; x++){
				for(int z = 0; z<mat1[0].length; z++){
					System.out.print("\t"+matSum[x][z]);
				}
				System.out.println("");
			}
		}else{
			System.out.println("\tSum is not possible.");
		}
   }


      public static void difference(int[][]mat1, int[][]mat2){
		  int[][] matDiff = new int[mat1.length][mat1[0].length];
		  if(mat1[0].length == mat2[0].length && mat1.length == mat2.length){
			  for(int i = 0; i<mat1.length; i++){
			  	  for(int y =0; y<mat1[0].length; y++){
			  			matDiff[i][y] = mat1[i][y]-mat2[i][y];
			   }
	        }
	          for(int x = 0; x<mat1.length; x++){
				  for(int z =0; z<mat1[0].length; z++){
					  System.out.print("\t"+matDiff[x][z]);;
			    }
				 System.out.println("");
	         }

		  }else{
			  System.out.println("\tDifference is not possible.");
		  }

	  }


	 public static void product(int[][]mat1, int[][]mat2){
	  int[][] matProduct = new int[mat1.length][mat2[0].length];
		if(mat1[0].length != mat2.length){
			System.out.println("\tProduct is not possible.");
	}else{
		for(int i = 0; i<mat1.length; i++){
		    for(int y =0; y<mat2[0].length; y++){
				for(int z = 0; z<mat2.length; z++){
						matProduct[i][y] += mat1[i][z]*mat2[z][y];
			   }
			 }
	     }
	     for(int x = 0; x<mat1.length; x++){
		 	for(int n =0; n<mat2[0].length; n++){
		 		System.out.print("\t"+matProduct[x][n]);;
		 	    }
		 		System.out.println("");
	      }
	}
}





	public static void main(String[] args)
	{
		Matrix app=new Matrix();
	}
}




