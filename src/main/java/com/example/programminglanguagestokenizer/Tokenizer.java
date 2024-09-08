package com.example.programminglanguagestokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tokenizer {

    private TokenClassifier classifier;
    private GranularAnalyzer analyzer;

    public Tokenizer() {
        classifier = new TokenClassifier();
        analyzer = new GranularAnalyzer();
    }

    // Phase 1: Tokenization Based on Delimiter with Mixed Type
    public List<MixedTypeToken> tokenizeByDelimiter(String input, String delimiter) {
        List<MixedTypeToken> tokenList = new ArrayList<>();
        String[] rawTokens = input.split(delimiter);

        for (String rawToken : rawTokens) {
            String mixedType = determineMixedType(rawToken);
            tokenList.add(new MixedTypeToken(rawToken, mixedType));
        }

        return tokenList;
    }

    private String determineMixedType(String token) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasPunctuation = false;
        boolean isValidFloat = isValidFloat(token);

        if (isValidFloat) {
            return "Number";
        }

        for (char c : token.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isWhitespace(c)) {
                // Ignore whitespace
            } else {
                hasPunctuation = true;
            }
        }

        List<String> types = new ArrayList<>();
        if (hasLetter) types.add("Word");
        if (hasDigit) types.add("Number");
        if (hasPunctuation) types.add("Punctuation");

        return String.join(" and ", types);
    }

    private boolean isValidFloat(String token) {
        boolean hasDecimal = false;
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c == '.') {
                if (hasDecimal) {
                    return false;
                }
                hasDecimal = true;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return hasDecimal;
    }

    // Phase 2: Token Classification by Type
    public List<IToken> classifyTokens(List<MixedTypeToken> rawTokens) {
        List<IToken> tokenList = new ArrayList<>();

        for (MixedTypeToken rawToken : rawTokens) {
            tokenList.addAll(classifier.classifyToken(rawToken.getValue()));
        }

        // Add end-of-line token at the end of the list
//        tokenList.add(new EndOfLineToken("\n"));

        return tokenList;
    }

    // Phase 3: Granular Breakdown of Tokens
    public void printGranularComponents(List<IToken> tokens) {
        System.out.println("\nPhase 3: Granular breakdown of tokens:");
        for (IToken token : tokens) {
            if (!(token instanceof EndOfLineToken)) {
                List<String> components = analyzer.analyze(token);
                System.out.print("Token: \"" + token.getValue() + "\" → ");
                System.out.println("'" + String.join("', '", components) + "'");
            }
        }
    }

    // Phase 4: Extract Granular Breakdown of Tokens
    public List<String> extractGranularComponents(List<IToken> tokens) {
        List<String> granularComponents = new ArrayList<>();
        for (IToken token : tokens) {
            if (!(token instanceof EndOfLineToken)) {
                List<String> components = analyzer.analyze(token);
                granularComponents.add("Token: \"" + token.getValue() + "\" → '" + String.join("', '", components) + "'");
            }
        }
        return granularComponents;
    }

    public void printTokens(List<IToken> tokens) {
        System.out.println("\nPhase 2 - Token Classification by Type:");
        for (IToken token : tokens) {
            String tokenValue = token.getValue().equals("\n") ? "\\n" : token.getValue();
            System.out.println("Token: \"" + tokenValue + "\" - Type: " + token.getType().getDescription());
        }
    }

    public void printMixedTypeTokens(List<MixedTypeToken> tokens) {
        System.out.println("Phase 1 - Tokens based on delimiter with mixed type:");
        for (MixedTypeToken token : tokens) {
            System.out.println("Token: \"" + token.getValue() + "\" - Type: " + token.getMixedType());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to tokenize:");
        String input = scanner.nextLine();

        if (input == null || input.isEmpty()) {
            System.out.println("Input is empty. Exiting.");
            return;
        }

        Tokenizer tokenizer = new Tokenizer();

        // Phase 1: Tokenization Based on Delimiter with Mixed Type
        List<MixedTypeToken> rawTokens = tokenizer.tokenizeByDelimiter(input, "#");
        tokenizer.printMixedTypeTokens(rawTokens);

        // Phase 2: Token Classification by Type
        List<IToken> classifiedTokens = tokenizer.classifyTokens(rawTokens);
        tokenizer.printTokens(classifiedTokens);

        // Phase 3: Granular Breakdown of Tokens
        tokenizer.printGranularComponents(classifiedTokens);
    }
}