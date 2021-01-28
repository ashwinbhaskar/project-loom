import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;

class VirtualThreadDemo{

	private static void resourceUseDemo() throws InterruptedException {
		final AtomicInteger i = new AtomicInteger(0);
 		for(int j = 0; j < 1000000; j++){
 			Thread.startVirtualThread(() -> i.incrementAndGet());
 		}

 		Thread.sleep(5000);

 		System.out.println("The value of i is " + i);
	}

	private static void outOfMemoryDemo() {
		List<Thread> ts = new ArrayList<Thread>();
		for(int j = 0; j < 1000000; j++){
 			Thread t = Thread.startVirtualThread(() -> {
 				try{
 					Thread.sleep(5000);
 				}catch(InterruptedException e){
 					System.out.println("exception " + e);
 				}
 			});
 			ts.add(t);
 		}

 		ts.forEach(t -> {
 			try{
 				t.join();
 			}catch(InterruptedException e){
 				System.out.println("exception " + e);
 			}
 		});

 		System.out.println(ts.size() + " threads have finished with their job");

	}

	public static void main(String[] args) throws InterruptedException{
		outOfMemoryDemo(); 
	}
}
