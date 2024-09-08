package com.example.programminglanguagestokenizer;

import java.util.ArrayList;
import java.util.List;

public abstract class Token implements IToken {
    protected String value;

    public Token(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<String> getGranularComponents() {
        List<String> components = new ArrayList<>();
        for (char c : value.toCharArray()) {
            components.add(String.valueOf(c));
        }
        return components;
    }
}
