package com.example.mysport.ApplicationInterface;
import com.example.mysport.POJO.User;
public class FetchUserJob implements IFetchUserJob {
    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println(throwable);
    }
}