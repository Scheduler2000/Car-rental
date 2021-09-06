package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;

import database.repository.CarRepository;
import helper.ServiceLocator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import model.car.CarBuilder;

public class AddCarController extends ViewController implements Initializable {

    private WebEngine _webEngine;
    private SceneController _sceneController;
    private CarRepository _carRepository;
    private String _thumbnailBase64;

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
    private Button _loadThumbnailBtn;

    @FXML
    private Button _addToMarketBtn;

    @FXML
    private Button _cancelBtn;

    public AddCarController() {
        super();
    }

    public void OnLoadThumbnailBtnPressed(ActionEvent event) throws IOException {
        var fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(null);

        if (selected == null)
            return;

        byte[] fileContent = Files.readAllBytes(selected.toPath());
        _thumbnailBase64 = Base64.getEncoder().encodeToString(fileContent);
        _webEngine.loadContent("<img height=300px src=\"data:image;base64," + _thumbnailBase64 + "\"/>");
    }

    public void OnAddToMarketBtnPressed(ActionEvent event) throws SQLException {
        String brand = _brandTextField.getText();
        String model = _modelTextField.getText();
        String rentalPrice = _rentalPriceTextField.getText();
        String description = _descriptionTextField.getText();

        if (brand.isBlank() || model.isBlank() || rentalPrice.isBlank() || description.isEmpty()
                || _thumbnailBase64.isBlank())
            return;

        var car = new CarBuilder().SetBrand(brand).SetModel(model).SetRentalPrice(Float.parseFloat(rentalPrice))
                .SetDescription(description).SetThumbnail(_thumbnailBase64).Build();

        if (_carRepository.Create(car)) {
            _sceneController.ChangeScene(getClass().getResource("../view/EmployeeView.fxml"), false);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Car Creation Result");

            alert.setHeaderText("Database Information");
            alert.setContentText("An error occured during the creation of the car : " + car.toString());

            alert.showAndWait();
        }
    }

    public void OnCancelled(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/EmployeeView.fxml"), false);
    }

    @Override
    protected void on_mounted() {
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
        this._thumbnailBase64 = null;
        this._carRepository = ServiceLocator.GetContainer().GetInstance(CarRepository.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this._webEngine = _webView.getEngine();

    }

}
