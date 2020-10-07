package com.sorry.buskingbusking.setting;

public enum FileSetting {

    MOCK_FILE_PATH("/buskingbusking/test"),
    NOTICE_PATH("/busking/image/notice"),
    PERFORMANCE_PATH("/buskingbusking/image/performance");

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
