package cs1302.api.Components;

import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import java.util.HashMap;
import java.util.Map;

/**
 * The TopBar class represents the top section of the app's user interface.
 * It contains a section for instructions and two buttons for user interactions.
 */
public class TopBar extends HBox {

    private Label instructions;
    private Button searchButton;
    private Button weatherButton;
    private ComboBox<String> comboBox;
    private Map<String, String> launchpadMap;

    /**
     * Constructor for TopBar.
     */
    public TopBar() {
        // Initialize components
        instructions = new Label("Please Select a Launchpad and Press Search");
        searchButton = new Button("Search");
        comboBox = new ComboBox<String>();
        weatherButton = new Button("Get Weather");
        weatherButton.setDisable(true);

        //let items grow based on content


        //create map for name-padID for combobox
        launchpadMap = new HashMap<String, String>() {{
                put("Kennedy Space Center Historic Site", "5e9e4502f509094188566f88");
                put("Vandenberg Space Force Base - 4E", "5e9e4502f509092b78566f87");
                put("Cape Canaveral Launch Complex 40", "5e9e4501f509094ba4566f84");
                put("SpaceX South Texas Launch Site", "5e9e4502f5090927f8566f85");
                put("Kwajalein Atoll Omelek Island", "5e9e4502f5090995de566f86");
                put("Vandenberg Space Force Base - 3W", "5e9e4501f5090910d4566f83");
            }}; //launchpadMap

        //add names to combobox
        comboBox.getItems().addAll(launchpadMap.keySet());
        comboBox.setValue("Kennedy Space Center Historic Site");

        // Set alignment and spacing
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        // Add components to the HBox
        this.getChildren().addAll(instructions, comboBox,  searchButton, weatherButton);
    } //TopBar() constructor

    /**
     * Gets the search button.
     * @return The search button.
     */
    public Button getSearchButton() {
        return searchButton;
    } //getSearchButton()

    /**
     * Gets the weather button.
     * @return The weather button.
     */
    public Button getWeatherButton() {
        return weatherButton;
    } //getWeatherButton()

    /**
     * Sets the text of the instructions label.
     * @param text The text to set for the instructions.
     */
    public void setInstructionsText(String text) {
        instructions.setText(text);
    } //setInstructions

    /**
     * Method to pull cobobox value, find associated ID and return it.
     *
     * @return combobox
     */
    public String getComboBoxId() {
        String selectedLaunchpadName = comboBox.getValue();
        return launchpadMap.get(selectedLaunchpadName);
    } //getComboBoxId
} //topbar
