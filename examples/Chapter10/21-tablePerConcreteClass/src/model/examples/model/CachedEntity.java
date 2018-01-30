package examples.model;

public abstract class CachedEntity {
    private long createTime;

    public CachedEntity() {
        createTime = System.currentTimeMillis();
    }

    public long getCacheAge() {
        return System.currentTimeMillis() - createTime;
    }
}
