package com.example.programminglanguagestokenizer;

import java.util.List;

public interface IToken {
    String getValue();
    TokenType getType();
    List<String> getGranularComponents();
}



