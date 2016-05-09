class SOP {
    public static void print(String s)
    {
        System.out.println(s);
    }
}

class TestThread extends Thread
{
    String name;
    SynchronisedDemo synchronisedDemo;
    public TestThread(String name,SynchronisedDemo synchronisedDemo)
    {
        this.synchronisedDemo = synchronisedDemo;
        this.name = name;
        start();
    }

    @Override
    public void run()
    {
        synchronisedDemo.test(name);
    }
}

public class SynchronisedDemo {
	synchronized
    public void test(String name)
    {
        for(int i=0;i<10;i++)
        {
            SOP.print(name + " :: "+i);
        }
        SOP.print("\n");
    }

    public static void main(String[] args)
    {
        SynchronisedDemo synchronisedDemo = new SynchronisedDemo();
        new TestThread("THREAD 1",synchronisedDemo);
        new TestThread("THREAD 2",synchronisedDemo);
        new TestThread("THREAD 3",synchronisedDemo);
    }
}