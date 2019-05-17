package com.kdg.cores.entity;

public class ExecuteResult {

    private Boolean isFinished;
    private Boolean isSuccess;

    public ExecuteResult(Boolean isFinished, Boolean isSuccess) {
        this.isFinished = isFinished;
        this.isSuccess = isSuccess;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }
}
