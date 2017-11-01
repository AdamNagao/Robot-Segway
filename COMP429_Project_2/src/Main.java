import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfDevices = 5;
		int numOfReady = 0;
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		ArrayList<DVR> network = new ArrayList<DVR>();
		
		for(int i = 0;i<numOfDevices;i++){
			DVR dvr = new DVR(i,numOfDevices);
			dvr.networkChanged = true;
			network.add(dvr);
			dvr.start();
		}

		while(running){
			if((numOfReady == 5) && DVR.semaphore.tryAcquire()){

				System.out.println("What is the Starting Node");
				int start = scanner.nextInt();
				System.out.println("What is the Ending Node");
				int end = scanner.nextInt();
				System.out.println("What is the Cost");
				int cost = scanner.nextInt();
				
				for(int i = 0;i<numOfDevices;i++){
					network.get(i).s = start;
					network.get(i).e = end;
					network.get(i).c = cost;
					network.get(i).networkChanged = true;
				}
				DVR.semaphore.release();
				numOfReady = 0;
			} else {
				for(int i = 0;i<numOfDevices;i++){
					if(network.get(i).isReady){
						numOfReady++;
					} 
					
				}
			}

		}
		

	}

}
