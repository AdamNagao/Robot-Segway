import java.util.concurrent.Semaphore;

public class DVR extends Thread{
	AdjMatrix matrix;
	int num = 0;
	boolean isReady = true;
	boolean networkChanged = false;
	int s;
	int e;
	int c;
	
	static Semaphore semaphore = new Semaphore(1, true);
	
	public DVR(int i, int numOfDevices) {
		this.num = i;
		initializeMatrix(numOfDevices);
	}

	@Override
	public void run(){
		while(true){
			if(networkChanged){
				if(semaphore.tryAcquire() && (isReady == true)){
					isReady = false;
					updateMatrix(s,e,c);
				}
				printMatrix();
				semaphore.release();
				networkChanged = false;
				isReady = true;
			}
			
		}
	}
	
	void printMatrix(){
		System.out.print("Device Number: " + num + " Table initialized");
		matrix.printMatrix();

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
		printMatrix();
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
		 System.out.println();
		for(int i = 0; i< array.length;i++){
			for(int j = 0; j < array.length;j++){
			
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
		}
	}
}