package com.example.mysport.ApplicationInterface;
import com.example.mysport.POJO.User;

public interface IFetchUserJob {
    public void onSuccess(User user);
    public void onFailure(Throwable throwable);
}