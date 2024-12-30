package com.uydev.enums;

import lombok.Getter;

@Getter
public enum ConfigType {
    FIXED("Fixed"), MONTHLY("Monthly"), WEEKLY("Weekly");
    private final String value;
    ConfigType(String value){
        this.value = value;
    }
}
