package helper.Cache;

import java.util.HashMap;

public class ContainerBuilder {
    private final HashMap<Class<?>, Object> _underlying;

    public ContainerBuilder() {
        this._underlying = new HashMap<Class<?>, Object>();
    }

    public <T> ContainerBuilder RegisterService(Class<T> typeof, T instance) {
        this._underlying.put(typeof, instance);
        return this;
    }

    public <T> ContainerBuilder RegisterService(Class<T> typeof) {
        try {
            this._underlying.put(typeof, typeof.getConstructor().newInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Default registration failed for service : " + typeof.getName());
        }
        return this;
    }

    public Container Build() {
        return new Container(_underlying);
    }
}
