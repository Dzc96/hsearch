package com.gdei.hsearch.base;

/**
 * API返回数据格式的封装
 */
public class ApiResponse {
    private int code;
    private String message;
    private Object data;
    private boolean more;  //是否有更多信息

    //返回数据格式的构造器定义
    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    //通过静态方法快速构建返回状态(主要是正常的返回状态)，而不是自己慢慢new
    //静态方法有的没数据，有的有数据
    //offMessage类似offStatus，说明请求不一定成功，返回提示信息，不返回数据
    public static ApiResponse ofMessage(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    //ofSuccess说明请求成功，并返回数据
    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    //ofStatus说明请求不一定成功，说明请求不一定成功，返回提示信息，不返回数据
    public static ApiResponse ofStatus(Status status) {
        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
    }


    //用枚举来定义一些常用的返回状态
    public enum Status{

        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
        NOT_LOGIN(50000, "Not Login");


        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

        public void setStandardMessage(String standardMessage) {
            this.standardMessage = standardMessage;
        }
    }

}
