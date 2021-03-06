package cn.modesty.suanfa.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://blog.csdn.net/wljliujuan/article/details/79614019
 * 产生死锁的四个必要条件
 * 5.1 互斥条件：
 * 进程要求对所分配的资源（如打印机）进行排他性控制，即在一段时间内某资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
 * 5.2 不可剥夺条件:
 * 进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能由获得该资源的进程自己来释放（只能是主动释放)。
 * 5.3 请求与保持条件：
 * 进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
 * 5.4 循环等待条件:
 * 存在一种进程资源的循环等待链，链中每一个进程已获得的资源同时被 链中下一个进程所请求。即存在一个处于等待状态的进程集
 * 合{Pl, P2, …, pn}，其中Pi等 待的资源被P(i+1)占有（i=0, 1, …, n-1)，Pn等待的资源被P0占有，如图2-15所示。
 * 直观上看，循环等待条件似乎和死锁的定义一样，其实不然。按死锁定义构成等待环所 要求的条件更严，它要求Pi等待的资源必须
 * 由P(i+1)来满足，而循环等待条件则无此限制。 例如，系统中有两台输出设备，P0占有一台，PK占有另一台，且K不属于集合{0, 1, …, n}。
 * Pn等待一台输出设备，它可以从P0获得，也可能从PK获得。因此，虽然Pn、P0和其他 一些进程形成了循环等待圈，但PK不在圈内，
 * 若PK释放了输出设备，则可打破循环等待, 如图2-16所示。因此循环等待只是死锁的必要条件。
 *
 *
 * 运行时数据区和线程私有/共享（重点）
 * 1.运行时数据区包括：虚拟机栈区，堆区，方法区，本地方法栈，程序计数器
 * 2.虚拟机栈区 ：也就是我们常说的栈区，线程私有，存放基本类型，对象的引用和 returnAddress ，在编译期间完成分配。
 * 3.堆区 ， JAVA 堆，也称 GC 堆，所有线程共享，存放对象的实例和数组， JAVA 堆是垃圾收集器管理的主要区域。
 * 4.方法区 ：所有线程共享，存储已被虚拟机加载的类信息，常量，静态变量，即时编译器编译后的代码等数据。这个区域的内存回收目标主要是针对常量池的对象的回收和对类型的卸载。
 * 5.程序计数器 ：线程私有，每个线程都有自己独立的程序计数器，用来指示下一条指令的地址。
 *
 * 23、锁的几种状态？？
 */
public class LockMain {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //通过标志位停止线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    if (isInterrupted()) {
                        System.out.println(i);
                    }
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //thread.stop();??
        thread.isInterrupted();
    }


}
