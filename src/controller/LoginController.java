package controller;

import java.sql.SQLException;

import database.repository.AccountRepository;
import helper.ServiceLocator;
import helper.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.account.Account;
import model.account.AccountBuilder;
import model.account.AccountTypeEnum;

public class LoginController extends ViewController {

    private AccountRepository _accountRepository;
    private SceneController _sceneController;

    @FXML
    private TextField _emailTextField;

    @FXML
    private PasswordField _passwordTextField;

    @FXML
    private Hyperlink _signupBtn;

    @FXML
    private Hyperlink _forgotPasswordBtn;

    public LoginController() {
        super();
    }

    public void OnLoggedIn(ActionEvent event) throws SQLException {
        var predicate = new AccountBuilder().SetEmail(_emailTextField.getText())
                .SetPassword(_passwordTextField.getText()).Build();
        Account account = null;
        try {
            account = _accountRepository.GetEntity(predicate);
        } catch (Exception ex) {
        }
        if (account != null) {
            Session.Initiliaze(account);
            System.out.println(account.toString());
            _sceneController.ChangeScene(
                    getClass().getResource(account.GetType() == AccountTypeEnum.Customer ? "../view/MarketView.fxml"
                            : "../view/EmployeeView.fxml"),
                    false);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Account Login Result");

            alert.setHeaderText("Database Information");
            alert.setContentText("Invalid Credentials !");

            alert.showAndWait();
        }
    }

    public void OnSignup(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/SignupView.fxml"), false);
    }

    public void OnForgotPasswordBtnPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/RecoveryPasswordView.fxml"), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void on_mounted() {
        this._accountRepository = ServiceLocator.GetContainer().GetInstance(AccountRepository.class);
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
    }

}