package org.example;

public enum  BizLogType {
    ALL(0,null),
    BUSINESS(1,"业务日志"),
    EXCEPTION(2,"异常日志");

    private final Integer val;
    private final String message;

    BizLogType(Integer val, String message) {
        this.val = val;
        this.message = message;
    }

    public Integer getVal() {
        return val;
    }


    public String getMessage() {
        return message;
    }

    public static String valueof(Integer value){
        if (value == null){
            return null;
        }else{
            for (BizLogType bizLogType : BizLogType.values()) {
                if(bizLogType.getVal().equals(value)){
                    return bizLogType.getMessage();
                }
            }
            return null;
        }
    }

}
