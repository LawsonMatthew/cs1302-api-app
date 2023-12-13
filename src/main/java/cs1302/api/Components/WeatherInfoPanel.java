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
import java.nio.file.Files;
import java.nio.file.Paths;
import cs1302.api.WeatherResponse;

/**
 * Component class for the Weather Information panel UI element.
 */
public class WeatherInfoPanel extends VBox {

    private Label localObservationDateTime;
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
    private Image defaultWeatherIcon;
    private Label location;
    String precipType;

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
        titleLabel = new Label("Live Weather Information");

        // init ImageView for the weather icon (you can add this later)
        defaultWeatherIcon = new Image("file:resources/0.png");
        weatherIcon = new ImageView(defaultWeatherIcon);
        weatherIcon.setFitHeight(200); // Set preferred height
        weatherIcon.setFitWidth(300); // Set preferred width
        weatherIcon.setPreserveRatio(true);

        // Initialize labels for WeatherResponse elements
        location = new Label("Location: ");
        localObservationDateTime = new Label("Local Observation Date Time: ");
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
        this.getChildren().addAll(titleLabel, weatherIcon, spacer1, location,
            localObservationDateTime, weatherTextLabel, temperatureLabel, realFeelTemperatureLabel,
            precipitationTypeLabel, relativeHumidityLabel, uvIndexLabel, uvIndexTextLabel, spacer2);
    } // setupUI

    /**
     * Method to update the weathericon image based on api's passed icon value.
     *
     * @param iconValue - weather icon value
     */
    public void updateIcon(int iconValue) {
        //make file path
        String imagePath = "resources/" + iconValue + ".png"; // Remove "file:"

        // check if the file exists
        if (Files.exists(Paths.get(imagePath))) {
            //create new image with updated filepath
            Image newIcon = new Image("file:" + imagePath);

            //update the weatherIcon ImageView
            weatherIcon.setImage(newIcon);
        } else {
            //if file does not exist show placeholder
            Image placeholderIcon = new Image("file:resources/placeholder.png");
            weatherIcon.setImage(placeholderIcon);
        } //if-else

    } //updateIcon

    /**
     * Getter/ for location Label. Used in GetWeather handler to pull location
     * name from spacex launchpad response, only ID is available from
     * accuweather api for query.
     *
     * @param location - representing locality
     * @param region
     */
    public void setLocationLabel(String location, String region) {
        this.location.setText("Location: " + location + " , " + region);
    } //setLocationLabel

    /**
     * Updates the UI elements with data from the given WeatherResponse.
     *
     * @param weatherResponse the WeatherResponse object containing weather data.
     */
    public void updateUI(WeatherResponse weatherResponse) {
        // Update elements with values from WeatherResponse
        updateIcon(weatherResponse.getWeatherIcon());
        localObservationDateTime.setText("Local Observation Date Time: " +
            weatherResponse.getLocalObservationDateTime());
        weatherTextLabel.setText("Weather Text: " + weatherResponse.getWeatherText());
        temperatureLabel.setText("Temperature: " +
            weatherResponse.getTemperature().getImperial().getValue());
        realFeelTemperatureLabel.setText("Real Feel Temperature: " +
            weatherResponse.getRealFeelTemperature().getImperial().getValue() +
            " " + weatherResponse.getRealFeelTemperature().getImperial().getUnit());

        //special handler for precip type
        if (weatherResponse.getPrecipitationType() == null) {
            precipType = "None";
        } else {
            precipType = weatherResponse.getPrecipitationType();
        } //if -else
        precipitationTypeLabel.setText("Precipitation Type: " + precipType);

        relativeHumidityLabel.setText("Relative Humidity: "
            + weatherResponse.getRelativeHumidity() + "%");
        uvIndexLabel.setText("UV Index: " + weatherResponse.getUvIndex());
        uvIndexTextLabel.setText("UV Index Text: " + weatherResponse.getUvIndexText());

    } //updateUI

} // WeatherInfoPanel
