/* Homework 9
 * @author NinnYu Chin
 * Instructions:	Write a program that has 4 separate threads. The threads will have the 
				following arrays:

				Thread1: A E I M Q U Y
				Thread2: B F J N R V Z
				Thread3: C G K O S W
				Thread4: D H L P T X

				Your goal is to synchronize the threads in such a way that they print out 
				all the letters of the alphabet in order.
 */

import java.util.concurrent.Semaphore;

public class Alphabet {
	public static void main (String[] args) {
		char[] charr1 = {'A', 'E', 'I', 'M', 'Q', 'U', 'Y'};
		char[] charr2 = {'B', 'F', 'J', 'N', 'R', 'V', 'Z'};
		char[] charr3 = {'C', 'G', 'K', 'O', 'S', 'W'};
		char[] charr4 = {'D', 'H', 'L', 'P', 'T', 'X'};

		ABCD t1 = new ABCD(charr1);
		ABCD t2 = new ABCD(charr2);
		ABCD t3 = new ABCD(charr3);
		ABCD t4 = new ABCD(charr4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class ABCD extends Thread {
	char[] arr;
	static Semaphore s = new Semaphore(1);
	
	ABCD() {}
	ABCD(char[] arr) {
		this.arr = arr;
	}
	
	public void run() {
		try {
			for (int i=0; i<arr.length; i++) {
				switch(getName()) {
				case "Thread-0": break;
				case "Thread-1": sleep(10); break;
				case "Thread-2": sleep(20);	break;
				case "Thread-3": sleep(30); break;
				}
				s.acquire();
				System.out.print(arr[i] + " ");
				s.release();
				sleep(500);
			}
		} catch (Exception e) {}
	}
}