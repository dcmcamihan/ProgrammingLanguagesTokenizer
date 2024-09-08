package com.example.programminglanguagestokenizer;

public enum TokenType {
    WORD("Word"),
    NUMBER("Number"),
    PUNCTUATION("Punctuation"),
    ALPHANUMERIC("Alphanumeric"),
    END_OF_LINE("End of line");

    private final String description;

    TokenType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}