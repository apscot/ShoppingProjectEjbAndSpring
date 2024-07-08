package customershoppingcart.cacheConfig;

import java.util.ArrayList;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;



public class LocalInMemoryCache<K, T> {
    private final long timeToLive;
    
    private final LRUMap<K, T> localCacheMap;
    protected class LocalCacheObject {
        
        public long lastAccessed = System.currentTimeMillis();
        public T value;
        protected LocalCacheObject(T value) {
            this.value = value;
        }
    }
    public LocalInMemoryCache(long localTimeToLive, final long localTimerInterval, int maxItems) {
        this.timeToLive = localTimeToLive * 1000;
        localCacheMap = new LRUMap<K, T>(maxItems);
        if (timeToLive > 0 && localTimerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            
                            Thread.sleep(localTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        localCleanup();
                    }
                }
            });
            
            t.setDaemon(true);
            
             t.start();
        }
    }
    public void put(K key, T value) {
    	
        synchronized (localCacheMap) {
        	System.out.println("Keeping in Cache");
            localCacheMap.put(key, (T) new LocalCacheObject(value));
        }
    }
    public T get(K key) {
    	
        synchronized (localCacheMap) {
        	System.out.println("Fetching from Cache");
            LocalCacheObject c;
            c = (LocalCacheObject) localCacheMap.get(key);
            if (c == null) {
            	System.out.println("Cache miss");
                return null;
            }
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }
    public void remove(K key) {
        synchronized (localCacheMap) {
            localCacheMap.remove(key);
        }
    }
    public int size() {
        synchronized (localCacheMap) {
            return localCacheMap.size();
        }
    }
    public void localCleanup() {
    	long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;
        synchronized (localCacheMap) {
            MapIterator itr = localCacheMap.mapIterator();
            deleteKey = new ArrayList<K>((localCacheMap.size() / 2) + 1);
            K key = null;
            LocalCacheObject c = null;
            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (LocalCacheObject) itr.getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    
                    // add(): Appends the specified element to the end of this list.
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (localCacheMap) {
                
                localCacheMap.remove(key);
                System.out.println("Cache deleted.");
            }
            
            Thread.yield();
        }
    }
}
