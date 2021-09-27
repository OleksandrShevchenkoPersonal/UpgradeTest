package core.cache;

import enums.TestCacheKey;
//import org.assertj.core.api.Assertions;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class TestCache {

    private static final ThreadLocal<TestSessionMap> CACHE = new InheritableThreadLocal<>();

    private static class TestSessionMap extends ConcurrentHashMap<Object, Object> {
        public  Object put(final Object key, final Object value) {
            return Objects.isNull(value) ? super.remove(key) : super.put(key, value);
        }
    }

    private static TestSessionMap getCache() {
        return CACHE.get();
    }

    public static void initializeTestCache() {
        CACHE.set(new TestSessionMap());
    }

    public static Object get(final TestCacheKey key) {
        return getCache().get(key);
    }

    public static <T> T get(final TestCacheKey key, final Class<?> type) {
        Object value = getCache().get(key);
        if (Objects.nonNull(value)) {
            // Assertions.assertThat.as("Incompatible type of returned object").isInstanceOf(type);
        }
        return Objects.nonNull(value) ? (T) value : null;
    }

    public static void putDataInCache(final TestCacheKey key, final Object value) {
        getCache().put(key, value);
    }
}
