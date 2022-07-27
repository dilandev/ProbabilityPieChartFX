package com.codewithdilan;

public enum MyColor {
    Color1("#808000"),
    Color2("#326654"),
    Color3("#008000"),
    Color4("#800000"),
    Color5("#A52A2A"),
    Color6("#FFA500"),
    Color7("#000000"),
    Color8("#808080"),
    Color9("#C0C0C0"),
    Color10("#ADD8E6"),
    Color11("#FFC0CB"),
    Color12("#FF00FF"),
    Color13("#00FF00"),
    Color14("#FFFF00"),
    Color15("#800080"),
    Color16("#ADD8E6"),
    Color17("#00008B"),
    Color18("#0000FF"),
    Color19("#00FFFF"),
    Color20("#FF0000"),
    Color21("#728FCE"),
    Color22("#123456"),
    Color23("#566D7E"),
    Color24("#2916F5"),
    Color25("#3B9C9C"),
    Color26("#FFC0CB"),
    Color27("#C0C0C0");

    private String color;

    MyColor(String colorCode) {
        this.color = colorCode;
    }

    public String getColorCode() {
        return color;
    }
}
