package enums;

import lombok.Getter;

public enum TestCacheKey {

    EMAIL("EMAIL"),
    LOAN_DATA("LOAN_DATA");

    @Getter
    private final String key;

    TestCacheKey(String key) { this.key = key; }
}
