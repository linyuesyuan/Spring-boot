package com.example.demo.result;

public enum ExceptionMsg {


    SUCCESS("200", "操作成功"),
    FAILED("999999", "操作失敗"),
    ParamError("000001", "參數錯誤！"),
    FileEmpty("000400", "上傳文件為空"),
    LimitPictureSize("000401", "圖片大小必須小於2M"),
    LimitPictureType("000402", "圖片格式必須為'jpg'、'png'、'jpge'、'gif'、'bmp'");
    private ExceptionMsg(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
