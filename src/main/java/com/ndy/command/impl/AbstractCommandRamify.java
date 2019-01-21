package com.ndy.command.impl;

import java.util.regex.Pattern;

public abstract class AbstractCommandRamify {

    private String regex, message = "";

    public AbstractCommandRamify(String... args) {
        this.regex = getRegex(args);
    }

    public abstract boolean execute(Object... objects);

    public void setMessage(String message) { this.message = message.replace("&", "§"); }
    public String getMessage() { return this.message; }
    public boolean isMatched(String command) { return Pattern.matches(regex, command); }

    private String getRegex(String... args) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");

        for(String element : args) {
            builder.append(element).append("|");
        }
        builder.append(")");

        return builder.toString();
    }

}
