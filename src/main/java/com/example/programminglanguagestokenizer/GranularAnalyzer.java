package com.example.programminglanguagestokenizer;

import java.util.ArrayList;
import java.util.List;

public class GranularAnalyzer {

    public List<String> analyze(IToken token) {
        List<String> components = new ArrayList<>();
        String value = token.getValue();

        if (token instanceof EndOfLineToken) {
            components.add("\\n");
        } else {
            for (char c : value.toCharArray()) {
                components.add(String.valueOf(c));
            }
        }

        return components;
    }
}