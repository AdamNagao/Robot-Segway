import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfDevices = 5;
		DVR dvr = new DVR(numOfDevices);
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		System.out.println("Table Initialized:");
		dvr.matrix.printMatrix();
		
		while(running){
			
			System.out.println("Please enter an update to the network");
			System.out.println("What Node are we starting at?");
			int start = scanner.nextInt();
			System.out.println("What Node are we finishing at?");
			int finish = scanner.nextInt();
			System.out.println("What is the cost of going from Node " + start + " to Node " + finish);
			int cost = scanner.nextInt();
			
			System.out.println("Table Updated!");
			dvr.updateMatrix(start,finish,cost);
			dvr.matrix.printMatrix();
			
			System.out.println("Would you like to keep updating the network? Y/N");
			String m = scanner.next();
			
			if(m.toUpperCase().contentEquals("Y")){
				running = true;
			} else {
				running = false;
			}
		}

	}

}
