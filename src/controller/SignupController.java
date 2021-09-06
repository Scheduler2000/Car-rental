package controller;

import java.sql.SQLException;

import database.repository.AccountRepository;
import helper.ServiceLocator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.account.Account;
import model.account.AccountBuilder;
import model.account.AccountTypeEnum;

public class SignupController extends ViewController {

    private SceneController _sceneController;
    private AccountRepository _accountRepository;
    private AccountTypeEnum _accountType;

    @FXML
    private Button _signupButton;

    @FXML
    private Hyperlink _loginBtn;

    @FXML
    private TextField _firstnameTextField;

    @FXML
    private TextField _lastnameTextField;

    @FXML
    private TextField _emailTextField;

    @FXML
    private PasswordField _passwordTextField;

    @FXML
    private RadioButton _employeeRadioBtn;

    @FXML
    private RadioButton _customerRadioBtn;

    public SignupController() {
        super();
    }

    public void OnSignup(ActionEvent event) throws SQLException {
        String firstname = _firstnameTextField.getText();
        String lastname = _lastnameTextField.getText();
        String email = _emailTextField.getText();
        String password = _passwordTextField.getText();

        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank()
                || _accountType == AccountTypeEnum.None)
            return;

        Account account = new AccountBuilder().SetFirstName(firstname).SetLastName(lastname).SetEmail(email)
                .SetType(_accountType).SetPassword(password).Build();

        if (!_accountRepository.Create(account)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Account Creation Result");

            alert.setHeaderText("Database Information");
            alert.setContentText(
                    "An error occured during the creation of the following account : " + account.toString());

            alert.showAndWait();
        } else {
            _sceneController.ChangeScene(getClass().getResource("../view/loginView.fxml"), false);
        }
    }

    public void OnLoginPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/LoginView.fxml"), false);
    }

    public void OnEmployeeRadioBtnPressed(ActionEvent event) {
        _customerRadioBtn.setSelected(false);
        _accountType = AccountTypeEnum.Employee;

    }

    public void OnCustomerRadioBtnPressed(ActionEvent event) {
        _employeeRadioBtn.setSelected(false);
        _accountType = AccountTypeEnum.Customer;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void on_mounted() {
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
        this._accountRepository = ServiceLocator.GetContainer().GetInstance(AccountRepository.class);
        this._accountType = AccountTypeEnum.None;
    }

}
