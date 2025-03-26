package app;

import app.view.CalculatorWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    // TODO add limits to not be able to type unreasonable things(dots without a number,
    //  numbers after brackets without operator,...) and to be able to type operators from keyboard
    //  also fix more complex decimal calculations

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new CalculatorWindow());
        stage.setScene(sc);
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.show();
    }
}
