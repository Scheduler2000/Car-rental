package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.repository.CarRepository;
import helper.APICaller;
import helper.HTMLBuilder;
import helper.ServiceLocator;
import helper.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.car.Car;
import model.car.CarBuilder;

public class MarketController extends ViewController implements Initializable {

    private SceneController _sceneController;
    private WebEngine _webEngine;
    private CarRepository _CarRepository;
    private APICaller _apiCaller;

    @FXML
    private AnchorPane _sidebar;

    @FXML
    private Button _accountBtn;

    @FXML
    private Button _reservationBtn;

    @FXML
    private Button _invoiceBtn;

    @FXML
    private Button _logoutBtn;

    @FXML
    private Button _sidebarToggleBtn;

    @FXML
    private Button _reloadBtn;

    @FXML
    private WebView _webView;

    @FXML
    private Button _bookingBtn;

    @FXML
    private TextField _carIdTextField;

    public MarketController() {
        super();
    }

    public void OnSidebarTogglePressed(ActionEvent event) {
        _sidebar.setVisible(!_sidebar.isVisible());
    }

    public void OnAccountBtnPressed(ActionEvent event) {
        System.out.println("to define");
    }

    public void OnReservationBtnPressed(ActionEvent event) {
        load_reservations();
    }

    public void OnInvoiceBtnPressed(ActionEvent event) {
        System.out.println("to define");
    }

    public void OnLogoutBtnPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/LoginView.fxml"), false);
        Session.Clear();
    }

    public void OnReloaded(ActionEvent event) {
        load_market_place();
    }

    public void OnBooked(ActionEvent event) throws NumberFormatException, SQLException, IOException {
        String id = _carIdTextField.getText();
        Car wantedCar = _CarRepository.GetEntity(new CarBuilder().SetId(Integer.parseInt(id)).Build());

        if (id.isBlank() || wantedCar == null)
            return;

        Stage stage = _sceneController.GetStage();
        var loader = new FXMLLoader(getClass().getResource("../view/BookingView.fxml"));
        Parent view = loader.load();
        BookingController controller = loader.getController();
        controller.Initialize(wantedCar);
        stage.getScene().setRoot(view);
        stage.show();
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
        this._CarRepository = ServiceLocator.GetContainer().GetInstance(CarRepository.class);
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

    private void load_reservations() {
        try {
            _webEngine.loadContent(
                    HTMLBuilder.RenderReservations(_apiCaller.GetReservations(Session.GetAccount().GetId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
