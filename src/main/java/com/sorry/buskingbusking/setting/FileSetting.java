package com.sorry.buskingbusking.setting;

public enum FileSetting {

    MOCK_FILE_PATH("/Users/sorry/buskingbusking/test"),
    PERFORMANCE_PATH("/Users/sorry/buskingbusking/image/performance");

    private String value;

    FileSetting(String value) {
        this.value = value;
    }


    public String getKey(){
        return name();
    }

    public String getValue(){
        return this.value;
    }

}
