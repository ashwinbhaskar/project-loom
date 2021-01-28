import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;


class ThreadDemo{

	private static void outOfMemoryDemo() {
		List<Thread> ts = new ArrayList<Thread>();
		for(int j = 0; j < 25000; j++){
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
