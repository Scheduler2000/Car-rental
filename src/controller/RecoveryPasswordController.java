package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import database.repository.AccountRepository;
import helper.ServiceLocator;
import helper.Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.account.AccountBuilder;

public class RecoveryPasswordController extends ViewController {

    private SceneController _sceneController;
    private EmailSender _emailSender;
    private AccountRepository _accountRepository;

    @FXML
    private Button _recoveryBtn;

    @FXML
    private TextField _emailTextField;

    @FXML
    private Hyperlink _loginBtn;

    public RecoveryPasswordController() {
        super();
    }

    public void OnLoginBtnPressed(ActionEvent event) {
        _sceneController.ChangeScene(getClass().getResource("../view/LoginView.fxml"), false);
    }

    public void OnRecoveryBtnPressed(ActionEvent event) throws MessagingException, IOException, SQLException {
        String email = _emailTextField.getText();

        if (email.isBlank())
            return;

        var predicate = new AccountBuilder().SetEmail(email).Build();

        var account = _accountRepository.GetEntity(predicate);

        if (account != null) {
            _emailSender.sendEmail(new String[] { email }, "RENT A CAR - RECOVERY ACCOUNT",
                    "REMINDER : Your password is [" + account.GetPassword() + "]");
            _sceneController.ChangeScene(getClass().getResource("../view/LoginView.fxml"), false);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Account Recovery Result");

            alert.setHeaderText("Database Information");
            alert.setContentText("No account found with the email : " + email);

            alert.showAndWait();
        }
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected void on_mounted() {
        this._sceneController = ServiceLocator.GetContainer().GetInstance(SceneController.class);
        this._emailSender = ServiceLocator.GetContainer().GetInstance(EmailSender.class);
        this._accountRepository = ServiceLocator.GetContainer().GetInstance(AccountRepository.class);
    }

}
