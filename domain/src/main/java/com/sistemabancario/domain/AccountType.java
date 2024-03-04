package com.sistemabancario.domain;

public enum AccountType {
    CHECKING_ACCOUNT(1),
    DEPOSIT_ACCOUNT(2);

    public final int type;

    AccountType(final int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
