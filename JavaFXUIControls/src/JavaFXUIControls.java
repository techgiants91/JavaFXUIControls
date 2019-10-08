
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFXUIControls extends Application {

    private LinkedList<Car> cars = new LinkedList<Car>();

    @Override
    public void start(Stage primaryStage) {

        Label lblMake = new Label("Make");
        Label lblModel = new Label("Model");
        Label lblYear = new Label("Year");
        Label lblSelectedYear = new Label("2000");
        Label lblTranmissionType = new Label("TranmissionType");
        Label lblInsurance = new Label("Insurance");
        Label lblBodyType = new Label("BodyType");

        Label lblOthers = new Label("Others");
        Label lblMessage = new Label("");

        TextField tfMake = new TextField();
        TextField tfModel = new TextField();
        Slider sliderProductionYear = new Slider(2000, 2019, 2000);
        sliderProductionYear.setBlockIncrement(1000);

        // TranmissionType
        ToggleGroup groupTranmissionType = new ToggleGroup();
        RadioButton automatic = new RadioButton("Autmatic");
        automatic.setSelected(true);
        RadioButton manual = new RadioButton("Manual");

        automatic.setToggleGroup(groupTranmissionType);
        manual.setToggleGroup(groupTranmissionType);

        HBox transmissionType = new HBox();
        transmissionType.setSpacing(10);
        transmissionType.getChildren().addAll(automatic, manual);

        // Body Type
        ComboBox cbBodyType = new ComboBox();
        cbBodyType.getItems().add("SUV");
        cbBodyType.getItems().add("Sedan");
        cbBodyType.getItems().add("Hatch");
        cbBodyType.getItems().add("Ute");
        cbBodyType.setValue("SUV");

        // Insurance list
        final ListView<String> listInsurance = new ListView<>();
        // Add the items to the List 
        ArrayList<String> insuranceProviders = new ArrayList<String>();
        insuranceProviders.add("Collision Damage Waiver (CDW)");
        insuranceProviders.add("Supplemental Liability Protection (SLP)");
        insuranceProviders.add("Personal Accident Insurance (PAI)");
        insuranceProviders.add("Personal Effects Coverage (PEC)");

        listInsurance.getItems().addAll(insuranceProviders);
        // Set the size of the ListView
        listInsurance.setPrefSize(120, 120);
        // Enable multiple selection
        listInsurance.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listInsurance.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov,
                    final String oldvalue, final String newvalue) {
            }
        });

        // Others
        CheckBox gps = new CheckBox("GPS");
        CheckBox satelliteNavigation = new CheckBox("Satellite navigation");
        HBox others = new HBox();
        others.getChildren().addAll(gps, satelliteNavigation);
        others.setSpacing(5);

        Button btnNewCar = new Button("NewCar");
        Button btnAddCar = new Button("AddCar");

        // Lisenter of NewCar adding
        btnNewCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Empty all the fields, set defaults
                tfMake.setText("");
                tfModel.setText("");
                sliderProductionYear.setValue(2000);
                cbBodyType.setValue("SUV");
                gps.setSelected(false);
                satelliteNavigation.setSelected(false);
                listInsurance.getSelectionModel().clearSelection();
                lblMessage.setText("");

            }
        });

        // Lisenter of add car adding
        btnAddCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String make = tfMake.getText();
                String model = tfModel.getText();
                int selectedYear = (int) sliderProductionYear.getValue();
                String bodyType = cbBodyType.getValue().toString();
                Toggle toggle = groupTranmissionType.getSelectedToggle();
                String transmissionType;
                if (((RadioButton) toggle) == automatic) {
                    transmissionType = "Automatic";
                } else {
                    transmissionType = "Manual";
                }

                HashSet<String> extraOptions = new HashSet();
                if (gps.isSelected()) {
                    extraOptions.add("GPS");
                }
                if (satelliteNavigation.isSelected()) {
                    extraOptions.add("SatelliteNavigation");
                }

                // insurance provider
                HashSet<String> selectedInsuranceProviders = new HashSet();
                for (String provider : listInsurance.getSelectionModel().getSelectedItems()) {
                    selectedInsuranceProviders.add(provider);
                }

                // Create car objects and stores the car object in the list
                Car car = new Car();
                car.setMake(make);
                car.setModel(model);
                car.setYear(selectedYear);
                car.setBodyType(bodyType);
                car.setExtraOptions(extraOptions);
                car.setTransmission(transmissionType);
                car.setInsurance(selectedInsuranceProviders);

                cars.add(car);
                lblMessage.setText("Car added!");
            }
        });

        // year slider change listener
        sliderProductionYear.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                lblSelectedYear.setText(newValue.intValue() + "");
            }
        });

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(30);
        root.setVgap(10);

        // Adding all items to root GridPane
        root.addRow(0, lblMake, tfMake);
        root.addRow(1, lblModel, tfModel);
        root.addRow(2, lblYear, sliderProductionYear, lblSelectedYear);
        root.addRow(3, lblTranmissionType, transmissionType);
        root.addRow(4, lblBodyType, cbBodyType);
        root.addRow(5, lblInsurance, listInsurance);
        root.addRow(6, lblOthers, others);
        root.addRow(7, btnNewCar, btnAddCar, lblMessage);

        // Add GridPane to Scene
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Rental Company");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
