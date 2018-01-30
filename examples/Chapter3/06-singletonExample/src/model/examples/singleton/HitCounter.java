package model.examples.singleton;

import javax.ejb.Singleton;

@Singleton
public class HitCounter {
    int count;

    public void increment() { ++count; }
	
    public int getCount() { return count; }

    public void reset() { count = 0; }
}