package cs1302.api.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * The TopBar class represents the top section of the app's user interface.
 * It contains a section for instructions and two buttons for user interactions.
 */
public class TopBar extends HBox {

    private Label instructions;
    private Button searchButton;
    private Button weatherButton;

    /**
     * Constructor for TopBar.
     */
    public TopBar() {
        // Initialize components
        instructions = new Label("Press Search to search for next upcoming SpaceX Launch!");
        searchButton = new Button("Search");
        weatherButton = new Button("Get Weather");
        weatherButton.setDisable(true);

        // Set alignment and spacing
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        // Add components to the HBox
        this.getChildren().addAll(instructions, searchButton, weatherButton);
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
} //topbar
