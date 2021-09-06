package helper.Cache;

import java.util.HashMap;

/* 
 * Here we can add lifecycle management for our objects.
 * Singleton - Transient - Scoped 🤔
 * But like that it's fine.
*/
public class Container {
    private final HashMap<Class<?>, Object> _registeredTypes;

    public Container(HashMap<Class<?>, Object> _registeredTypes) {
        this._registeredTypes = _registeredTypes;
    }

    public <T> T GetInstance(Class<T> typeof) {
        return (T) (_registeredTypes.get(typeof));
    }

}
