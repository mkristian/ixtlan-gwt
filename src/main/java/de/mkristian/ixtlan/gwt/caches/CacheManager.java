package de.mkristian.ixtlan.gwt.caches;

public interface CacheManager {

    void addCache(Cache<?> cache);
    
    void purgeCaches();

}