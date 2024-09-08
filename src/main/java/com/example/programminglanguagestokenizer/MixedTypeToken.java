package com.example.programminglanguagestokenizer;

public class MixedTypeToken {
    private String value;
    private String mixedType;

    public MixedTypeToken(String value, String mixedType) {
        this.value = value;
        this.mixedType = mixedType;
    }

    public String getValue() {
        return value;
    }

    public String getMixedType() {
        return mixedType;
    }
}