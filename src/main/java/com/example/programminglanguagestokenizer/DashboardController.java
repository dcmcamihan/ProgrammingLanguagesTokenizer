package com.example.programminglanguagestokenizer;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Collections;
import java.util.List;

public class DashboardController {

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    private int selectedPhase = 1;

    private Tokenizer tokenizer = new Tokenizer();

    @FXML
    protected void onHelloButtonClick() {
        var input = txtInput.getText();
        txtOutput.clear();
        getTokenBySelectedPhase(input).forEach(token -> txtOutput.appendText(stringify(token) + "\n"));
    }

    private String stringify(Object object) {
        if(object instanceof MixedTypeToken token) {
            return token.getValue() + " - " + token.getMixedType();
        } else if(object instanceof IToken token) {
            return token.getValue() + " - " + token.getType().getDescription();
        } else {
            return object.toString();
        }
    }

    private List<? extends Object> getTokenBySelectedPhase(String token) {
        switch (selectedPhase) {
            case 1:
                return tokenizer.tokenizeByDelimiter(token, "#");
            case 2:
                return tokenizer.classifyTokens(tokenizer.tokenizeByDelimiter(token, "#"));
            case 3:
                List<MixedTypeToken> rawTokens = tokenizer.tokenizeByDelimiter(token, "#");
                List<IToken> classifiedTokens = tokenizer.classifyTokens(rawTokens);
                return tokenizer.extractGranularComponents(classifiedTokens);
            default:
                return null;
        }
    }

    @FXML
    protected void onPhase1ButtonClick() {
        selectedPhase = 1;
        onHelloButtonClick();
    }

    @FXML
    protected void onPhase2ButtonClick() {
        selectedPhase = 2;
        onHelloButtonClick();
    }

    @FXML
    protected void onPhase3ButtonClick() {
        selectedPhase = 3;
        onHelloButtonClick();
    }

}