package helper;

import helper.Cache.Container;

public class ServiceLocator {

    private static boolean _isContainerInitialized;

    private static Container _container;

    public static Container GetContainer() {
        if (!_isContainerInitialized) {
            /*
             * throw new
             * Exception("You must set the container before using it with ServiceLocator.");
             */
            return null;
        }

        return _container;
    }

    public static void SetContainer(Container container) {
        if (!_isContainerInitialized) {
            _container = container;
            _isContainerInitialized = true;
        }
    }

}
