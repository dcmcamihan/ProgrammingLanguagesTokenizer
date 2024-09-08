package com.example.programminglanguagestokenizer;

public class NumberToken extends Token {
    public NumberToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }
}