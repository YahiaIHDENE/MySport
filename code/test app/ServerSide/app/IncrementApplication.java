package com.IncrementRestService.app;

// import the rest service you created!
import com.IncrementRestService.rest.IncrementRestService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class IncrementApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public IncrementApplication() {
        // Register our Increment service
        singletons.add(new IncrementRestService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
