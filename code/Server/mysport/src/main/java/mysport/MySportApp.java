package mysport;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MySportApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public MySportApp() {
        // Register our services here
        singletons.add(new UserHandler());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}