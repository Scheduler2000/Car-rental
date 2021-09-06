package helper;

/* 
 * We cannot use generic with static so we can't do 
 * in-memory caching like in C# ... It's sad
 */
public /* static */ abstract class Singleton<T> {

    protected T instance;

    protected Singleton(Class<T> reflection) {
        try {
            this.instance = reflection.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The Singleton couldnt be constructed, check if " + reflection.getName()
                    + " has a default constructor.");
        }
    }

}
