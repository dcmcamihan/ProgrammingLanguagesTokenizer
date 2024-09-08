package com.example.programminglanguagestokenizer;

public class AlphanumericToken extends Token {
    public AlphanumericToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.ALPHANUMERIC;
    }
}