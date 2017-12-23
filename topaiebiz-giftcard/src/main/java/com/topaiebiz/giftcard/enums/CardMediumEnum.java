package com.topaiebiz.giftcard.enums;

/**
 * @description: 卡介质枚举
 * @author: Jeff Chen
 * @date: created in 下午3:34 2017/12/18
 */
public enum CardMediumEnum {

    SOLID_CARD(1,"实体卡"),
    ELECT_CARD(2, "电子卡");

    private int mediumId;
    private String mediumName;

    CardMediumEnum(int mediumId, String mediumName) {
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumName() {
        return mediumName;
    }

    public void setMediumName(String mediumName) {
        this.mediumName = mediumName;
    }
}
