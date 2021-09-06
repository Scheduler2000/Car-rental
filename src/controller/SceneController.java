package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SceneController {

    private final Stage _stage;
    private final HashMap<String, Parent> _cache;

    public Stage GetStage() {
        return _stage;
    }

    public SceneController(Stage scene) {
        this._stage = scene;
        this._cache = new HashMap<String, Parent>();
    }

    public void ChangeScene(URL location, boolean caching, Callback<Class<?>, Object> controllerFactory) {
        try {
            Parent view = caching_scene(location, caching, controllerFactory);
            _stage.getScene().setRoot(view);
            _stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The change of scene failed. Check the path of the view {" + location.toString() + "}");
        }
    }

    public void ChangeScene(URL Location, boolean caching) {
        ChangeScene(Location, caching, null);
    }

    private Parent caching_scene(URL location, boolean caching, Callback<Class<?>, Object> controllerFactory)
            throws IOException {
        Parent view = null;
        if (!_cache.containsKey(location.toString())) {
            var loader = new FXMLLoader(location);

            if (controllerFactory != null)
                loader.setController(controllerFactory);
            view = loader.load();

            if (caching)
                _cache.put(location.toString(), view);
        } else {
            view = _cache.get(location.toString());
        }

        return view;
    }
}
