package com.example.demo.result;

public class ResponseData extends Response{
    private Object Data;

    public ResponseData(Object data) {
        Data = data;
    }

    public ResponseData(ExceptionMsg msg) {
        super(msg);
    }

    public ResponseData(ExceptionMsg msg, Object data) {
        super(msg);
        Data = data;
    }

    public ResponseData(String rspCode, String rspMsg, Object data) {
        super(rspCode, rspMsg);
        Data = data;
    }

    public ResponseData(String rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "Data=" + Data +
                "} " + super.toString();
    }
}
