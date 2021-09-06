package controller;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import database.repository.BookingRepository;
import helper.ServiceLocator;
import helper.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.booking.BookingBuilder;
import model.car.Car;

public class BookingController extends ViewController {

    private SceneController _sceneController;
    private BookingRepository _bookingRepository;
    private WebEngine _webEngine;
    private int _carId;

    @FXML
    private WebView _webView;

    @FXML
    private TextField _brandTextField;

    @FXML
    private TextField _modelTextField;

    @FXML
    private TextField _rentalPriceTextField;

    @FXML
    private TextField _descriptionTextField;

    @FXML
    private Button _bookingBtn;

    @FXML
    private Button _cancelBtn;

    @FXML
    private DatePicker _pickupDate;

    @FXML
    DatePicker _returnDate;

    public BookingController() {
        super();
    }

    public void OnBookingBtnPressed(ActionEvent event) throws SQLException {

        Date pickupDate = java.util.Date
                .from(_pickupDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date returnDate = java.util.Date
                .from(_returnDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        if (returnDate.compareTo(pickupDate) == 0 || returnDate.compareTo(pickupDate) < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Date Booking Result");

            alert.setHeaderText("Date Information");
            alert.setContentText("Invalid Dates !");

            alert.showAndWait();
            return;
        }

        var booking = new BookingBuilder().SetCarId(_carId).SetCustomerId(Session.GetAccount().GetId())
                .SetPickUpDate(pickupDate).SetReturnDate(returnDate).Build();

        if (_bookingRepository.Create(booking)) {
            System.out.println("BOOKED");
            System.out.println(booking.toString());
            _sceneController.ChangeScene(getClass().getResource("../view/MarketView.fxml"), false);

        }
    }

    public void OnCancelled(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/MarketView.fxml"), false);
    }

    @Override
    protected void on_mounted() {
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
        this._bookingRepository = ServiceLocator.GetContainer().GetInstance(BookingRepository.class);
    }

    public void Initialize(Car car) {
        this._webEngine = _webView.getEngine();
        _webEngine.loadContent("<img height=300px src=\"data:image;base64," + car.GetThumbnail() + "\"/>");

        this._carId = car.GetId();
        this._brandTextField.setText(car.GetBrand());
        this._modelTextField.setText(car.GetModel());
        this._rentalPriceTextField.setText(car.GetRentalPrice() + " $");
        this._descriptionTextField.setText(car.GetDescription());
    }

}
