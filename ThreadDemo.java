import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;


class ThreadDemo{

	private static void resourceUseDemo() throws InterruptedException {
		final AtomicInteger i = new AtomicInteger(0);
 		for(int j = 0; j < 1000000; j++){
 			new Thread(() -> i.incrementAndGet()).start();
 		}

 		Thread.sleep(5000);

 		System.out.println("The value of i is " + i);
	}

	private static void outOfMemoryDemo() {
		final AtomicInteger i = new AtomicInteger(0);
		List<Thread> ts = new ArrayList<Thread>();
		for(int j = 0; j < 1000000; j++){
 			Thread t = new Thread(() -> {
 				try{
 				Thread.sleep(5000);
 			}catch(InterruptedException e) {
 				System.out.println("exception " + e);
 			}
 			});
 			t.start();
 			ts.add(t);
 		}
 		ts.forEach(t -> {
 			try{
 				t.join();
 			}catch(InterruptedException e){
 				System.out.println("exception " + e);
 			}
 		});
 		System.out.println("There are " + ts.size() + "threads");

	}
	
	public static void main(String[] args) throws InterruptedException{
		outOfMemoryDemo(); 
	}
}
