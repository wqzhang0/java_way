package com.dramakill.batch.dbread.base;

public class DealBean {
    private Long person_id;
    private Boolean SendPubBefore;
    private Boolean addExp;
    private Boolean SendPubAfter;

    public DealBean(Long person_id, Boolean sendPubBefore, Boolean addExp, Boolean sendPubAfter) {
        this.person_id = person_id;
        this.SendPubBefore = sendPubBefore;
        this.addExp = addExp;
        this.SendPubAfter = sendPubAfter;
    }

    public DealBean(Long person_id) {
        this.person_id = person_id;
    }

    public DealBean() {
    }


    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Boolean getSendPubBefore() {
        return SendPubBefore;
    }

    public void setSendPubBefore(Boolean sendPubBefore) {
        SendPubBefore = sendPubBefore;
    }

    public Boolean getAddExp() {
        return addExp;
    }

    public void setAddExp(Boolean addExp) {
        this.addExp = addExp;
    }

    public Boolean getSendPubAfter() {
        return SendPubAfter;
    }

    public void setSendPubAfter(Boolean sendPubAfter) {
        SendPubAfter = sendPubAfter;
    }
}
