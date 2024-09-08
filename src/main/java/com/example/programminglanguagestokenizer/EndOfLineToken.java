package com.example.programminglanguagestokenizer;

public class EndOfLineToken extends Token {
    public EndOfLineToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.END_OF_LINE;
    }
}