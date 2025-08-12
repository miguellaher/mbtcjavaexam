package com.miguellaher.mbtcexam.entity;

public enum AccountType {
    S("Saving"),
    C("Checking");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
