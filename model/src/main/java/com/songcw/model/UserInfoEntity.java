package com.songcw.model;

/**
 * Created by Sprout on 2018/8/29
 */
public class UserInfoEntity {
    /**
     * audit_status : 0
     * pledge_amount : 0
     * pledge_paied : true
     * reject_reason : fsdfs
     * balance : 26.54656
     */

    private int audit_status;
    private int pledge_amount;
    private boolean pledge_paied;
    private String reject_reason;
    private double balance;

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
    }

    public int getPledge_amount() {
        return pledge_amount;
    }

    public void setPledge_amount(int pledge_amount) {
        this.pledge_amount = pledge_amount;
    }

    public boolean isPledge_paied() {
        return pledge_paied;
    }

    public void setPledge_paied(boolean pledge_paied) {
        this.pledge_paied = pledge_paied;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
