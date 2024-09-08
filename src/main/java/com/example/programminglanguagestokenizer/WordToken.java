package com.example.programminglanguagestokenizer;

public class WordToken extends Token {
    public WordToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.WORD;
    }
}