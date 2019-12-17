package com.example.mysport;

public interface IFetchUserJob {
    public void onSuccess(User user);
    public void onFailure(Throwable throwable);
}