package com.systemspecs.evoting.domain.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum  ElectionYearConstant {
    TWENTY23(2023), TWENTY27(2027), TWENTY31(2031);

    private int value;
    private static Map map = new HashMap<>();

    private ElectionYearConstant(int value) {
        this.value = value;
    }

    static {
        for (ElectionYearConstant pageType : ElectionYearConstant.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ElectionYearConstant valueOf(int pageType) {
        return (ElectionYearConstant) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
