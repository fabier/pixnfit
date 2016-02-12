package pixnfit

/**
 * Based on : http://chriswu.me/blog/a-lru-cache-in-10-lines-of-java/
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, 0.75, true);
        this.cacheSize = cacheSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() >= cacheSize;
    }
}
