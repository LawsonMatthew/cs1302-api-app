package cs1302.api.Components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

import cs1302.api.WeatherResponse;

/**
 * Component class for the Weather Information panel UI element.
 */
public class WeatherInfoPanel extends VBox {

    private Label localObservationDateTimeLabel;
    private Label weatherTextLabel;
    private Label temperatureLabel;
    private Label realFeelTemperatureLabel;
    private Label precipitationTypeLabel;
    private Label relativeHumidityLabel;
    private Label uvIndexLabel;
    private Label uvIndexTextLabel;
    private ImageView weatherIcon;
    private Label titleLabel;
    private Region spacer1;
    private Region spacer2;

    /**
     * Constructor for WeatherInfoPanel class object.
     */
    public WeatherInfoPanel() {
        // init UI javafx components
        setupUI();
    } // WeatherInfoPanel

    /**
     * Initializes components for WeatherInfoPanel class object.
     */
    private void setupUI() {
        // spacing and padding for the VBox
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.TOP_CENTER);

        // make component title
        titleLabel = new Label("Weather Information");

        // init ImageView for the weather icon (you can add this later)
        weatherIcon = new ImageView();
        weatherIcon.setFitHeight(200); // Set preferred height
        weatherIcon.setFitWidth(300); // Set preferred width
        weatherIcon.setPreserveRatio(true);

        // Initialize labels for WeatherResponse elements
        localObservationDateTimeLabel = new Label("Local Observation Date Time: ");
        weatherTextLabel = new Label("Weather: ");
        temperatureLabel = new Label("Temperature: ");
        realFeelTemperatureLabel = new Label("Real Feel Temperature: ");
        precipitationTypeLabel = new Label("Precipitation Type: ");
        relativeHumidityLabel = new Label("Relative Humidity: ");
        uvIndexLabel = new Label("UV Index: ");
        uvIndexTextLabel = new Label("UV Index Label: ");

        //init spacer between image and details
        spacer1 = new Region();
        spacer2 = new Region();
        spacer1.setPrefHeight(20);
        spacer2.setPrefHeight(20);

        // Add individual components to VBox
        this.getChildren().addAll(titleLabel, weatherIcon, spacer1,
            weatherTextLabel, temperatureLabel, realFeelTemperatureLabel, precipitationTypeLabel,
            relativeHumidityLabel, uvIndexLabel, uvIndexTextLabel, spacer2);
    } // setupUI

    /**
     * Updates the UI elements with data from the given WeatherResponse.
     *
     * @param weatherResponse the WeatherResponse object containing weather data.
     */
    public void updateUI(WeatherResponse weatherResponse) {
        // Update elements with values from WeatherResponse
        localObservationDateTimeLabel.setText("Local Observation Date Time: " +
            weatherResponse.getLocalObservationDateTime());
        weatherTextLabel.setText("Weather Text: " + weatherResponse.getWeatherText());
        temperatureLabel.setText("Temperature: " +
            weatherResponse.getTemperature().getImperial().getValue());
        realFeelTemperatureLabel.setText("Real Feel Temperature: " +
            weatherResponse.getRealFeelTemperature().getImperial().getValue() +
            " " + weatherResponse.getRealFeelTemperature().getImperial().getUnit());
        precipitationTypeLabel.setText("Precipitation Type: " +
            weatherResponse.getPrecipitationType());
        relativeHumidityLabel.setText("Relative Humidity: "
            + weatherResponse.getRelativeHumidity() + "%");
        uvIndexLabel.setText("UV Index: " + weatherResponse.getUvIndex());
        uvIndexTextLabel.setText("UV Index Text: " + weatherResponse.getUvIndexText());

    } //updateUI

} // WeatherInfoPanel
