package utn.tacs.common.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);

    private int evicted;

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("Cache event {} for item with key {}. Old value = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
        if(cacheEvent.getType() == EventType.EVICTED){
            evicted++;
        }
    }

    public void resetEvictionCount() {
        evicted = 0;
    }

    public int evicted() {
        return evicted;
    }

}
