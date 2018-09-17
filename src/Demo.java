import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
	Semaphore mutex = new Semaphore(1);
	Lock lock = new ReentrantLock();
	// Lock lock2ndWay = new Lock();

	public static void main(String args[]) throws InterruptedException {
		Demo d = new Demo();

		d.nonReentrantFun(4);
		// d.reEntrantFun(4);
		// Demo.syncByDefaultReentrantFun(4);

		System.out.println("kkkkkkkkkkkkkkk");
	}

	long nonReentrantFun(int n) throws InterruptedException {
		mutex.acquire();
		System.out.println(n);
		try {
			if (n <= 1)
				return 1;
			return nonReentrantFun(n - 1) + 1;
		} finally {
			mutex.release();
		}
	}

	long reEntrantFun(int n) throws InterruptedException {
		lock.lock();
		System.out.println(n);
		try {
			if (n <= 1)
				return 1;
			return reEntrantFun(n - 1) + 1;
		} finally {
			lock.unlock();
		}
	}

	// By default synchronized locks are Reentrant
	static long syncByDefaultReentrantFun(int n) throws InterruptedException {
		System.out.println(n);
		synchronized (Object.class) {
			if (n <= 1)
				return 1;
			return Demo.syncByDefaultReentrantFun(n - 1) + 1;
		}
	}

	// Eventhoug this code is not complete, this is non-reentrant, due to an outer
	// lock
	// public outer(){
	// lock2ndWay.lock();
	// inner();
	// lock2ndWay.unlock();
	// }
	//
	// public synchronized inner(){
	// lock2ndWay.lock();
	// //do something
	// lock2ndWay.unlock();
	// }
}
