package controller;

import java.net.URL;
import java.util.ResourceBundle;

import helper.APICaller;
import helper.HTMLBuilder;
import helper.ServiceLocator;
import helper.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EmployeeController extends ViewController implements Initializable {

    private SceneController _sceneController;
    private WebEngine _webEngine;
    private APICaller _apiCaller;

    @FXML
    private AnchorPane _sidebar;

    @FXML
    private Button _accountBtn;

    @FXML
    private Button _addCarBtn;

    @FXML
    private Button _logoutBtn;

    @FXML
    private Button _sidebarToggleBtn;

    @FXML
    private Button _reloadBtn;

    @FXML
    private WebView _webView;

    public EmployeeController() {
        super();
    }

    public void OnSidebarTogglePressed(ActionEvent event) {
        _sidebar.setVisible(!_sidebar.isVisible());
    }

    public void OnAccountBtnPressed(ActionEvent event) {
        System.out.println("to define");
    }

    public void OnLogoutBtnPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/LoginView.fxml"), false);
        Session.Clear();
    }

    public void OnAddCarBtnPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/AddCarView.fxml"), false);
    }

    public void OnReloaded(ActionEvent event) {
        load_market_place();
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        _webEngine = _webView.getEngine();
        load_market_place();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void on_mounted() {
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
        this._apiCaller = ServiceLocator.GetContainer().GetInstance(APICaller.class);
    }

    /**
     * WebEngine loads content just one time and do not refresh after DOM
     * modifications... So we can't use for example an html file with VueJs/Axios SO
     * for fetch/load data we've to rendering our html from scratch..
     */
    private void load_market_place() {
        try {
            _webEngine.loadContent(HTMLBuilder.RenderMarketPlace(_apiCaller.GetCars()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
