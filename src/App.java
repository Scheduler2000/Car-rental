import java.sql.SQLException;

import controller.SceneController;
import database.DatabaseConfiguration;
import database.DatabaseConnection;
import database.repository.AccountRepository;
import database.repository.BookingRepository;
import database.repository.CarRepository;
import helper.APICaller;
import helper.ServiceLocator;
import helper.Cache.ContainerBuilder;
import helper.Email.EmailConfiguration;
import helper.Email.EmailSender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConfiguration dbConfig = new DatabaseConfiguration("localhost", "3306", "root", "", "rent");
        DatabaseConnection dbConnection = new DatabaseConnection(dbConfig);
        dbConnection.OpenConnection();

        EmailConfiguration emailConfig = new EmailConfiguration("EMAIL HERE !!", "PASSWORD HERE !!", "smtp.gmail.com");

        EmailSender emailSender = new EmailSender(emailConfig);

        var container = new ContainerBuilder().RegisterService(DatabaseConnection.class, dbConnection)
                .RegisterService(AccountRepository.class, new AccountRepository(dbConnection.GetConnection()))
                .RegisterService(CarRepository.class, new CarRepository(dbConnection.GetConnection()))
                .RegisterService(BookingRepository.class, new BookingRepository(dbConnection.GetConnection()))
                .RegisterService(SceneController.class, new SceneController(primaryStage))
                .RegisterService(EmailSender.class, emailSender).RegisterService(APICaller.class, new APICaller())
                .Build();

        ServiceLocator.SetContainer(container);

        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rent a Car");
        primaryStage.setScene(new Scene(root, 779, 504));
        primaryStage.show();

    }

    @Override
    public void stop() throws SQLException {
        ServiceLocator.GetContainer().GetInstance(DatabaseConnection.class).CloseConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}