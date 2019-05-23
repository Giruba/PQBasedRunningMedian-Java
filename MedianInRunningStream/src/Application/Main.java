package Application;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String []args) {
		
		System.out.println("Median in a running stream of integers");
		System.out.println("--------------------------------------");
		
		PriorityQueue<Integer> priorityQueueMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> priorityQueueMinHeap = new PriorityQueue<Integer>();
		
		try {
			int choice = 1;
			int median = 0;
			do {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the element");
				int element = scanner.nextInt();
				if(priorityQueueMaxHeap.size() == 0 && priorityQueueMinHeap.size() == 0) {
					priorityQueueMaxHeap.add(element);
					median = element;
					System.out.println("Median: "+element);
				}else {
					median = _GetMedian(priorityQueueMaxHeap, priorityQueueMinHeap, element, median);
				}
				System.out.println("Running median currently is "+median);
				System.out.println("Press 1 to continue");
				choice = scanner.nextInt();
			}while(choice == 1);
		}catch(Exception exception) {
			System.out.println("Thrown exception is "+exception.getMessage());
		}		
	}
	
	private static int _GetMedian(PriorityQueue<Integer> priorityQueueMaxHeap,PriorityQueue<Integer> priorityQueueMinHeap, int incomingValue, int currentMedian) {
				
		int sizeImbalance = priorityQueueMaxHeap.size() - priorityQueueMinHeap.size();
		
		switch(sizeImbalance) {
		case 0:
			//Both heaps are of the same size
			if(incomingValue < currentMedian) {
				//Insert in the left
				priorityQueueMaxHeap.add(incomingValue);
				currentMedian = priorityQueueMaxHeap.peek();
			}else {
				priorityQueueMinHeap.add(incomingValue);
				currentMedian = priorityQueueMinHeap.peek();
			}
			break;
		case 1:
			//Left has more elements than right
			if(incomingValue < currentMedian) {
				priorityQueueMinHeap.add(priorityQueueMaxHeap.remove());
				priorityQueueMaxHeap.add(incomingValue);
			}else {
				priorityQueueMinHeap.add(incomingValue);
			}
			currentMedian = (priorityQueueMaxHeap.peek() + priorityQueueMinHeap.peek())/2;
			break;
		case -1:
			//Size of right is more than that of left
			if(incomingValue < currentMedian) {
				priorityQueueMaxHeap.add(incomingValue);
			}else {
				priorityQueueMaxHeap.add(priorityQueueMinHeap.remove());
				priorityQueueMaxHeap.add(incomingValue);
			}
			currentMedian = (priorityQueueMaxHeap.peek() + priorityQueueMinHeap.peek())/2;
			break;
		}
		return currentMedian;
	}
}
