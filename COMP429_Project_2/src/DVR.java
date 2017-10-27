public class DVR {
	AdjMatrix matrix;
	
	public DVR(int i) {
		initializeMatrix(i);
	}

	void initializeMatrix(int size){
		matrix = new AdjMatrix(size);
		
		for(int i = 0;i<size;i++){
			for(int j = 0;j<size;j++){
				if(i==j){
					matrix.array[i][j] = 0;
				} else {
					matrix.array[i][j] = -1;
				}
				
			}
		}
	}
	
	void updateMatrix(int start,int finish,int weight){
		matrix.insert(start, finish, weight);
	}
	
}

class AdjMatrix {
	 int[][] array;

	 public AdjMatrix(int s){
		 array = new int[s][s];
	 }
	 
	 public void insert(int row,int column, int data){
		 array[row][column] = data;
	 }
	 public void delete(int row,int column){
		 array[row][column] = 0;
	 }
	public void printMatrix(){
		System.out.print("        ");
		for(int i = 0;i<array.length;i++){
			System.out.print(" " + i + "  ");
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i< array.length;i++){
			System.out.print(i + "        ");
			for(int j = 0; j < array.length;j++){
			
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
		}
	}
	}