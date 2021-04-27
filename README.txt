Java多线程

线程和进程

进程：当一个程序进入内存运行时，即变成一个进程。是出于运行过程中的程序，具有一定的独立功能。是系统进行资源分配和调度的一个基本单位
        进程的三个特征：
            1. 独立性：进程是系统中独立存在的实体。有独立的资源，私有的地址空间。在没有经过允许的情况下，一个用户进程不可以直接访问其他进程的地址空间
            2. 动态性：进程是在系统中活动的指令集合。加入了时间的概念。进程有生命周期和各种状态。
            3. 并发性：多个进程可以在单个处理器上并发执行，进程执行间互不影响。
                        并发：同一时刻只能有一条指令执行。但多个进程指令被快速的轮换执行，看起来就像多个进程同时执行的效果（指令轮换执行）。
                             现代操作系统都支持多进程并发，根据硬件和操作系统采用不同的并发策略
                                1.协作式的多任务操作策略 （小型设备。一个线程调用自己的sleep(),yield()才会放弃占用的资源【主动放弃资源】）
                                2.抢占式的多任务操作策略 （所有现代的桌面和服务器操作系统都采用抢占式调度策略）
                        并行：同一时刻，有多条指令在多个处理器上同时执行。

线程：多线程扩展了多进程的概念，同一个进程可以同时并发处理多个任务。也被称为轻量级进程（LightWeight Process）
     线程是进程的执行单元，是程序中独立的，并发的执行流。是CPU调度的基本单位
     一个进程可以有多个线程，一个线程必须有一个父进程。
     线程有自己的堆栈，程序计数器，局部变量，但是不拥有系统资源。和父进程的其他线程共享该进程的进程资源。
     线程是独立运行的，不知道进程中其他线程的存在
     线程执行是抢占式，当前运行的线程随时都有可能被挂起
     线程可以创建和撤销另一个线程，同进程中的多个线程可以并发执行

多线程的优势
    1. 同进程下的多线程可以共享内存
    2. 创建线程消耗的系统资源相对进程来说更小。使用多线程实现多任务并发比多进程的效率高
    3. Java支持并简化了多线程编程

线程的创建和启动

继承Thread类，重写run()方法
实现Runnable接口，重写run()方法
实现Callable接口，重写call()方法，将Callable实现类对象放入FutureTask类中，将FutureTask对象作为Thread类的Target


接口方式创建线程的优点：
    1.线程可以有其他的继承类（Java是单继承，多实现）
    2.多个线程共享Target对象，适合多个线程处理同一份资源的情况。将CPU，代码，数据分开，形成清晰的模型。更符合Java面向对象的编程思想
    接口方式访问当前线程，使用Thread.currentThread()
    继承方式访问当前线程，使用this获取当前线程

线程的生命周期：
    1.新建 New
        此时没有表现出线程的动态特性，只是一般的Java对象，分配内存，初始化变量值。
    2.就绪 Runnable
        调用线程对象的start()方法，该线程处于就绪状态。JVM为其创建方法调用栈和程序计数器。什么时候运行取决于JVM线程调度器的调度
    3.运行 Running
        处于就绪状态的线程获取到CPU时间片，开始执行run()方法。
    4.阻塞 Blocked
        发生阻塞状态的情形：
            一. 线程调用sleep()方法，主动放弃占用的cpu资源
            二. 线程调用要给阻塞式的IO方法，该方法返回前，线程被阻塞
            三. 线程试图获取一个同步监视器，该同步监视器被其他线程持有
            四. 线程等待某个通知,notify
            五. 程序调用了线程的suspend()方法将该线程挂起。
                    suspend()方法容易导致死锁，尽量避免使用该方法
        解除阻塞的情形：
            一. sleep()方法经过了指定时间
            二. 线程调用的阻塞式IO方法已经返回
            三. 线程获取到同步监视器
            四. 其他线程发出了一个通知,notify
            五. 调用了resume()恢复方法
        解除阻塞后的线程处于就绪状态(Runnable)，等待JVM线程调度器的调度
    5.死亡 Dead
        发生线程死亡的情形：
            一. run()或者call()方法执行完毕，线程正常结束
            二. 线程抛出一个未捕获的exception或者error
            三. 调用线程的stop()方法结束该线程
                stop()方法易导致死锁，不推荐使用
        主线程结束时，不会影响其他线程
        JVM退出的时机：JVM中所有存活的线程都是守护线程
        判断线程是否存活：isAlive()
                       【true】：就绪，运行，阻塞状态时
                       【false】：新建，死亡状态时
        IllegalThreadStateException：对新建线程多次调用start(),或对已经死亡的线程调用start()，引发该异常

控制线程
   1. join()线程
            程序执行流中调用其他线程的join()方法时，调用线程会被阻塞，直到被join()方法加入的join线程执行完为止。
            join()方法的三个重载形式
                1.join():等待被join的线程执行完成
                2.join(long millis):等待被join的线程millis毫秒后，则不再等待
                3.join(long millis,int nanos) ：等待被join的线程时间为millis + nanos[微秒]

   2.后台线程
        又称为“守护线程”，“精灵线程”。该线程的任务是为其他线程提供服务。JVM的GC线程就是守护线程。
        特点：所有的前台线程死亡，则后台线程自动死亡。JVM中只剩下后台线程时，JVM就会退出。
        设置守护线程：setDaemon(true)，将线程设置为守护线程。在该线程调用start()之前设置，否则引发IllegalThreadStateException异常
        是否为守护线程：isDaemon()
        前台线程死亡后，JVM会通知守护线程死亡，但是守护线程从接收指令到做出响应需要一定时间。

   3.线程睡眠 sleep
        sleep(long millis)方法，Thread类的静态方法。 该方法受系统计时器和线程调度器的精度和准确度的影响
        让当前正在执行的线程暂停一段时间，并使该线程进入阻塞状态。
        在指定sleep时间内，即使系统中没有其他线程执行，处于sleep状态下的线程也不会执行

   4.线程让步 yield
        yield()方法，Thread类的静态方法。
        让当前正在执行的线程暂停，进入就绪状态。
        yield只是让当前线程暂停一下，让系统调度器重新调度线程。执行yield()方法的线程可能会被再次调度执行
        在多CPU并行的环境下，yield()方法有时候并不明显

   sleep() 与 yield() 的区别
        1.sleep使当前线程处于阻塞状态，让出cpu执行权，不会让出同步监视器。
          yield使当前线程处于就绪状态
        2.sleep使当前线程暂停，给其他线程执行机会，无视其他线程的优先级(Priority).
          yield只会给优先级相同，或优先级更高的线程执行机会
        3.调用yield方法的线程可能会被再次执行
        4.sleep方法需要抛出InterceptedException异常。yield方法无需抛出异常
        5.sleep方法比yield方法有更好的可移植性。通常不建议使用yield方法控制并发线程的执行

   5.线程优先级
        每个线程执行时都具有一定的优先级
        Thread类提供了setPriority(int priority),getPriority(int priority) 设置/返回当前线程的优先级
        线程的优先级设置为1~10之间的整数。不同的操作系统优先级不同，如win2000系统只有7个优先级，不能与Java的10个优先级对应。
        推荐使用Thread类的三个静态常量:
            1.Thread.MAX_PRIORITY ，值为10
            2.Thread.MIN_PRIORITY ，值为1
            3.Thread.NORM_PRIORITY，值为5

线程同步
    锁
        synchronized可以修饰方法，代码块。不能修饰构造器，成员变量。
        同步代码块
            语法：
                synchronized(obj){ //临界区
                    代码
                }
            作用：防止多个线程对统一资源进行并发访问
            obj: Java允许任何对象作为同步监视器，建议"obj"使用可能被并发访问的共享资源

        同步方法
            使用 synchronized 关键字修饰的方法就是同步方法
            对于 synchronized 修饰的实例方法（非static方法），无须显式指定同步监视器，同步方法的同步监视器就是this，调用该方法的对象。
            面向对象中的一种设计方式：
                Domain Driven Design （领域驱动设计，DDD），每个类都应该是完备的领域对象
            可变类的线程安全是以降低程序的运行效率为代价的，为了降低负面影响，采取以下策略：
                1.不要对线程安全的所有方法都进行同步，除非业务需要。
                2.若可变类有多种运行环境：单线程和多线程，应该为其提供两个版本：线程安全版本和线程不安全版本。
                    比如JDK中的StringBuild 和 StringBuffer

        释放同步监视器的锁定
            1.当前线程的同步方法，同步代码块执行结束
            2.遇到break，return
            3.出现未处理的Error和Exception
            4.执行wait()方法

        不会释放同步监视器的锁定
            1.Thread.sleep(),Thread.yield()
            2.其他线程调用该线程的suspend()方法

    同步锁（Lock）
        Lock是控制多个线程对共享资源进行访问的工具。线程访问共享资源前需要线获取Lock对象。
        一些锁允许对共享资源的并发访问。比如ReadWriteLock（读写锁）
        Lock，ReadWriteLock是Java5提供的两个根接口
            实现类
                Lock：ReentrantLock（可重入锁）
                ReadWwirteLock：ReentrantReadWriteLock（Java8的StampedLock类可以替代该类）
                                ReentrantReadWriteLock为读写操作提供了三种锁模式：Writing,ReadingOptimistic,Reading
    synchronized是隐式的加锁释放锁的方式
    Lock对象是显式的加锁释放锁的方式

线程通信
    一.Object类提供的三个方法：
        1.wait()
            导致当前线程等待，直到其他线程调用该同步监视器的notify()或者notifyAll()来唤醒该线程。
            调用wait()方法的当前线程会释放对该同步监视器的锁定，同时让出cpu的执行权。
            该方法有三个重载方法
            wait();无参
            wait(long timeout);等待指定timeout时间后自动苏醒
            wait(long timeout, int nanos);等待指定timeout时间后自动苏醒
        2.notify()
            唤醒在此同步监视器上等待的单个线程。若所有线程都在此同步器上等待，则唤醒其中一个线程，唤醒线程的选择是随机的。
            只有当前线程放弃对同步监视器的锁定（使用wait()方法）才可以执行被唤醒的线程（进入就绪状态）。
        3.notifyAll()
            唤醒在此同步监视器上等待的全部线程。
            只有当前线程放弃对同步监视器的锁定（使用wait()方法）才可以执行被唤醒的线程（进入就绪状态）。
       三个方法必须由同步监视器对象来调用，分为两种情况：
        1.synchronized修饰的同步方法，该类的默认实例就是同步监视器，可以在同步方法中直接调用三个方法
        2.synchronized修饰的同步代码块，同步监视器是括号里的对象，必须使用该对象调用

    二.使用Condition控制线程通信
        当使用Lock对象控制线程同步时，就不存在饮食的同步监视器，也就不能使用wait(),notify(),notifyAll()方法了
        可以使用Condition，效果同Object类的wait(),notify(),notifyAll()方法
        Condition相当于替代了同步监视器的功能。
        Condition将同步监视器的wait(),notify(),notifyAll()方法封装成不同的对象。
        Condition实例绑定在Lock对象上
        获取Condition实例：lock.newCondition();
            Condition类提供了三个方法：
                1.await()
                    类似OBject的wait()方法。
                2.signal()
                    类似OBject的notify()方法
                3.signalAll()
                    类似OBject的notifyAll()方法

    三.使用BlockQueue控制线程通信
        BlockQueue 是Java提供作为线程同步的接口
        内部实现是通过ReentrantLock实现。其中定义两个Condition
            1.notFull
            2.notEmpty
        当插入数据成功后将调用notEmpty，这样所有在队列为空时获取数据线程唤醒。
        当移除数据成功后将调用notFull，这样所有在队列满时插入数据的线程唤醒。
        blockQueue提供了两个支持阻塞的方法
            put(E e) : 当生产者向blockQueue中放入元素时，若该Queue中已满，则该线程被阻塞
            take(E e) : 当消费者向blockQueue中取出元素时，若该queue中为空，则该线程被阻塞
        BlockQueue继承了Queue接口，也可以使用Queue接口中的方法
            1.插入元素
                当队列已满时：
                    add(E e)：抛出异常
                    offer(E e)：返回false
                    put(E e)：阻塞队列
            2.删除并返回删除元素
                当队列为空时
                    remove(Object o)：抛出异常
                    poll()：返回false
                    take()：阻塞队列
            3.在队列头部取出但不删除元素
                当队列为空时：
                    element()：抛出异常
                    peek()：返回false
        BlockQueue的实现类
            1.ArrayBlockQueue:基于数组实现
            2.LinkedBlockQueue：基于链表实现
            3.PriorityBlockQueue：取出队列中最小元素，元素类需要实现Comparable接口
            4.SynchronousQueue：同步队列，存，取操作必须交替进行
            5.DelayQueue：底层基于PriorityBlockQueue实现。元素需要实现Delay接口。DelayQueue根据集合元素返回的getDalay()值进行排序

线程组和未处理异常
