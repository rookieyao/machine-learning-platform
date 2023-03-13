package com.pzj.project.common.enums;

public enum OriginDataProcessStateEnum {
    //创建枚举类的对象
    STATE_ONE(1,"未处理"),

    STATE_TWO(2,"处理成功"),

    STATE_THREE(3,"处理失败"),
    ;
    //需要枚举的属性
    private final int stateNum;
    private final String stateStr;

    OriginDataProcessStateEnum(int stateNum, String stateStr) {
        this.stateNum = stateNum;
        this.stateStr = stateStr;
    }


    public int getStateNum() {
        return stateNum;
    }

    public String getStateStr() {
        return stateStr;
    }

    @Override
    public String toString() {
        return "StateEnum{" +
                "stateNum=" + stateNum +
                ", stateStr='" + stateStr + '\'' +
                '}';
    }
}
