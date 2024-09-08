package com.example.programminglanguagestokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenClassifier {

    public List<IToken> classifyToken(String token) {
        List<IToken> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);

            // Handle letters and alphanumeric words
            if (Character.isLetter(c) || (Character.isDigit(c) && containsLetter(currentToken))) {
                if (currentToken.length() > 0 && !Character.isLetterOrDigit(currentToken.charAt(0))) {
                    tokens.add(createToken(currentToken.toString()));
                    currentToken.setLength(0);
                }
                currentToken.append(c);
            }
            // Handle digits and floating point numbers
            else if (Character.isDigit(c) || (c == '.' && isPartOfNumber(currentToken, token, i))) {
                if (currentToken.length() > 0 && !Character.isDigit(currentToken.charAt(0)) && currentToken.charAt(0) != '.') {
                    tokens.add(createToken(currentToken.toString()));
                    currentToken.setLength(0);
                }
                currentToken.append(c);
            }
            // Handle punctuation
            else if (Character.isWhitespace(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(createToken(currentToken.toString()));
                    currentToken.setLength(0);
                }
                if (c == '\n') {
                    tokens.add(new EndOfLineToken("\n"));
                }
            } else {
                if (currentToken.length() > 0) {
                    tokens.add(createToken(currentToken.toString()));
                    currentToken.setLength(0);
                }
                tokens.add(createToken(String.valueOf(c)));
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(createToken(currentToken.toString()));
        }

        return tokens;
    }

    // Helper method to determine if the '.' is part of a floating-point number
    private boolean isPartOfNumber(StringBuilder currentToken, String token, int index) {
        // The '.' must have digits before and after it to be part of a number
        return currentToken.length() > 0 &&
                Character.isDigit(currentToken.charAt(0)) &&
                index + 1 < token.length() &&
                Character.isDigit(token.charAt(index + 1));
    }

    // Helper method to check if the current token contains any letter
    private boolean containsLetter(StringBuilder currentToken) {
        for (int i = 0; i < currentToken.length(); i++) {
            if (Character.isLetter(currentToken.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private IToken createToken(String token) {
        if (isWord(token)) {
            return new WordToken(token);
        } else if (isAlphanumeric(token)) {
            return new AlphanumericToken(token); // Treat alphanumeric as Alphanumeric
        } else if (isNumber(token)) {
            return new NumberToken(token);
        } else if (isPunctuation(token)) {
            return new PunctuationToken(token);
        } else {
            // Handle unexpected tokens as WORD or PUNCTUATION based on the context
            return new WordToken(token);
        }
    }

    private boolean isWord(String token) {
        for (char c : token.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlphanumeric(String token) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : token.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                return false;
            }
        }
        return hasLetter && hasDigit;
    }

    private boolean isNumber(String token) {
        boolean hasDecimal = false;
        boolean hasDigit = false;
        for (char c : token.toCharArray()) {
            if (c == '.') {
                if (hasDecimal) {
                    return false;
                }
                hasDecimal = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                return false;
            }
        }
        return hasDigit;
    }

    private boolean isPunctuation(String token) {
        for (char c : token.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return true;
            }
        }
        return false;
    }
}