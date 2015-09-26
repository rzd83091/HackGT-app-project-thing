import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;
//Created by Shantanu Mantri//
public class Main extends Application {

    private ArrayList<String> usern = new ArrayList<>(100);
    private ArrayList<String> passwd = new ArrayList<>(100);
    private String leaderName = "";
    private String countryName= "";
    private Country country;

    public boolean login(String username, String password, Label label, Button login, Stage stage) {
        if (!usern.contains(username) || !passwd.contains(password)) {
            label.setText("ERROR: Username/Password was Incorrect!\nPlease register if you have not!");
        } else {
            //MOVE TO THE NEXT SCENE//
            label.setText("Login Successful!");
            countryNameScene(stage);
        }
        return usern.contains(username) && password.contains(password);
    }

    public boolean createAccount(String username, String password, Label label) {
        if (!(usern.contains(username)) && !username.equals("") && !password.equals("")) {
            usern.add(username);
            passwd.add(password);
            label.setText("Registration Successful!");
            return  true;
        } else {
            label.setText("Registration Unsuccessful!");
            return false;
        }
    }

    public void makeCountry(TextField one, TextField two, Stage stage, RadioButton a, RadioButton b, RadioButton c) {
        this.leaderName = one.getText();
        this.countryName = two.getText();
        String ans;
        if (a.isSelected()) {
            ans = "small";
        } else if (b.isSelected()) {
            ans = "medium";
        } else if(c.isSelected()) {
            ans = "large";
        } else {
            ans = "colossal";
        }
        this.country = new Country(this.countryName, this.leaderName,ans,20f,.1f);
        //GO TO THE NEXT SCENE//
        countryDisplayScene(stage);
    }

    public void update(Stage stage) {
        this.country.Update();
        countryDisplayScene(stage);
    }

    @Override public void start(Stage stage)throws Exception{
        StackPane backgroundSettings = new StackPane();

        // load the image
        Image image = new Image("back2.jpg");
        Image title = new Image("Title.png");

        //load the sound(s)//
        String musicFile = "Opening.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        //A button with the specified text caption.
        Button play = new Button("Play");
        DropShadow shadow = new DropShadow();

        //Adding the shadow when the mouse cursor is on
        play.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        play.setEffect(shadow);
                    }
                });

        //Removing the shadow when the mouse cursor is off
        play.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        play.setEffect(null);
                    }
                });
        play.setStyle("-fx-font: 22 arial; -fx-base:   #b6e7c9;");

        // simple displays BACKGROUND IMAGEVIEW/BACKGROUND SCENE
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        ImageView iv2 = new ImageView();
        iv2.setImage(title);
        iv1.fitWidthProperty().bind(stage.widthProperty());
        iv2.fitWidthProperty().bind(stage.widthProperty());
        StackPane.setAlignment(iv2,Pos.TOP_CENTER);
        StackPane.setAlignment(play,Pos.BOTTOM_CENTER);
        backgroundSettings.getChildren().addAll(iv1,iv2,play);
        Scene scene = new Scene(backgroundSettings);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(800);
        stage.sizeToScene();
        stage.show();

        //CREATES the SELECTION Stage//
        play.setOnAction(e -> {registrationScene(stage);});
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void registrationScene(Stage stage) {
        //Make Buttons//
        Button login = new Button("Login");
        Button create = new Button("Create new Account");
        Label user = new Label("Username: ");
        Label pass = new Label("Password: ");
        Label temp = new Label();
        TextField userText = new TextField();
        TextField passText = new TextField();
        GridPane grid = new GridPane();
        GridPane.setConstraints(login, 0, 9);
        GridPane.setConstraints(create, 0, 10);
        GridPane.setConstraints(user, 0, 7);
        GridPane.setConstraints(pass, 0, 8);
        GridPane.setConstraints(userText, 1, 7);
        GridPane.setConstraints(passText, 1, 8);
        GridPane.setConstraints(temp, 1, 9);
        grid.getChildren().addAll(login, create, user, pass, userText, passText, temp);
        Scene select = new Scene(grid);
        stage.setTitle("Login");
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setScene(select);
        stage.show();

        //Action Buttons for Login/Create Account//
        login.setOnAction(e -> login(userText.getText(), passText.getText(), temp, login, stage));
        create.setOnAction(e -> createAccount(userText.getText(), passText.getText(), temp));
    }

    public void countryNameScene(Stage stage) {
        Button confirm = new Button("Confirm");
        TextField leader = new TextField();
        TextField country = new TextField();
        Label lName = new Label("Leader Name: ");
        Label cName = new Label("Country Name: ");
        Label popName = new Label("Population Size: ");
        Label gdpName = new Label("GDP (START): ");
        //RadioButtons for Population//
        ToggleGroup swagger = new ToggleGroup();
        RadioButton smallPop = new RadioButton("Small");
        RadioButton mediumPop = new RadioButton("Medium");
        RadioButton largePop = new RadioButton("Large");
        RadioButton colossalPop = new RadioButton("Colossal");
        smallPop.setToggleGroup(swagger);
        smallPop.setSelected(true);
        mediumPop.setToggleGroup(swagger);
        largePop.setToggleGroup(swagger);
        colossalPop.setToggleGroup(swagger);
        VBox buttBox = new VBox(smallPop,mediumPop,largePop,colossalPop);
        //RadioButtons for GDP (START)//
        ToggleGroup nice = new ToggleGroup();
        RadioButton third = new RadioButton("Third World");
        RadioButton second = new RadioButton("Second World");
        RadioButton first = new RadioButton("First World");
        third.setToggleGroup(nice);
        second.setToggleGroup(nice);
        first.setToggleGroup(nice);
        first.setSelected(true);
        VBox assBox = new VBox(first,second,third);
        //Setting where the contents go//
        GridPane grid = new GridPane();
        GridPane.setConstraints(confirm, 6, 9);
        GridPane.setConstraints(leader,6,1);
        GridPane.setConstraints(country,6,2);
        GridPane.setConstraints(buttBox,6,3);
        GridPane.setConstraints(assBox, 6, 4);
        GridPane.setConstraints(lName, 4, 1);
        GridPane.setConstraints(cName, 4, 2);
        GridPane.setConstraints(popName, 4, 3);
        GridPane.setConstraints(gdpName, 4, 4);
        grid.getChildren().addAll(confirm, leader, country, lName, cName, popName, gdpName, buttBox, assBox);
        Scene name = new Scene(grid);
        stage.setTitle("Country Basics!");
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setScene(name);
        stage.show();

        //Button actions//
        confirm.setOnAction(e -> {
            makeCountry(leader, country, stage, smallPop, mediumPop, largePop);
        });
    }

    public void reportCardScene(Stage stage) {
        Button next = new Button("Next Year");
        Label countryName = new Label("Country Name: ");
        Label leaderName = new Label("Leader Name: ");
        Label mainLabel = new Label("Progress Report: ");
        Label gdpLabel = new Label("GDP Rate of Change: ");
        Label popLabel = new Label("Population Rate of Change: ");
        Label taxLabel = new Label("Tax Rate: ");
        Label yearLabel = new Label("Year: ");
        TextField country = new TextField();
        TextField leader = new TextField();
        TextField gdp = new TextField();
        TextField pop = new TextField();
        TextField year = new TextField();
        TextField tax = new TextField();
        tax.setEditable(false);
        country.setEditable(false);
        leader.setEditable(false);
        gdp.setEditable(false);
        pop.setEditable(false);
        year.setEditable(false);
        country.setText(this.countryName);
        leader.setText(this.leaderName);
        float b = this.country.getPop();
        double c = this.country.getGDPRate();
        double d = this.country.getTaxRate();
        gdp.setText(Double.toString(c));
        pop.setText(Float.toString(b));
        tax.setText(Double.toString(d));
        GridPane grid = new GridPane();
        GridPane.setConstraints(countryName, 0, 1);
        GridPane.setConstraints(leaderName, 0, 2);
        GridPane.setConstraints(gdpLabel, 0, 3);
        GridPane.setConstraints(popLabel, 0, 4);
        GridPane.setConstraints(taxLabel,0, 5);
        GridPane.setConstraints(yearLabel,0, 6);
        GridPane.setConstraints(country, 2, 1);
        GridPane.setConstraints(leader, 2, 2);
        GridPane.setConstraints(gdp, 2, 3);
        GridPane.setConstraints(pop, 2, 4);
        GridPane.setConstraints(next,2,10);
        GridPane.setConstraints(year,2,6);
        GridPane.setConstraints(tax, 2,5);
        grid.getChildren().addAll(tax, taxLabel,gdp,pop,year,yearLabel, next, countryName, leaderName, mainLabel, gdpLabel, popLabel, country, leader);
        Scene now = new Scene(grid);
        stage.setTitle("REPORT CARD");
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setScene(now);
        stage.show();
        next.setOnAction(e -> update(stage));
    }

    public void countryDisplayScene(Stage stage) {
        Button next = new Button("End of Year");
        Label countryName = new Label("Country: ");
        Label leaderName = new Label("Leader Name: ");
        Label gdpLabel = new Label("GDP: ");
        Label popLabel = new Label("Population: ");
        TextField country = new TextField();
        TextField leader = new TextField();
        TextField gdp = new TextField();
        TextField pop = new TextField();
        country.setEditable(false);
        leader.setEditable(false);
        gdp.setEditable(false);
        pop.setEditable(false);
        country.setText(this.countryName);
        leader.setText(this.leaderName);
        float a = this.country.getGDP();
        float b = this.country.getPop();
        gdp.setText(Float.toString(a));
        pop.setText(Float.toString(b));
        GridPane grid = new GridPane();
        GridPane.setConstraints(countryName, 0, 1);
        GridPane.setConstraints(leaderName, 0, 2);
        GridPane.setConstraints(gdpLabel, 0, 3);
        GridPane.setConstraints(popLabel, 0, 4);
        GridPane.setConstraints(country, 2, 1);
        GridPane.setConstraints(leader, 2, 2);
        GridPane.setConstraints(gdp,2,3);
        GridPane.setConstraints(pop,2,4);
        GridPane.setConstraints(next,2,10);
        grid.getChildren().addAll(next,countryName, leaderName, gdpLabel, popLabel, country, leader,gdp,pop);
        Scene now = new Scene(grid);
        stage.setTitle("Current Progress");
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setScene(now);
        stage.show();
        //Button Actions//
        next.setOnAction(e -> reportCardScene(stage));
    }
}
