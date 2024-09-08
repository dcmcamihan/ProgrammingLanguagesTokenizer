package com.example.programminglanguagestokenizer;

public class PunctuationToken extends Token {
    public PunctuationToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.PUNCTUATION;
    }
}