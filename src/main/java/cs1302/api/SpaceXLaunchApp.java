package cs1302.api;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import cs1302.api.Components.TopBar;
import cs1302.api.Components.LaunchSiteInfoPanel;
import cs1302.api.Components.WeatherInfoPanel;
import cs1302.api.SpaceXResponse;
import cs1302.api.LaunchpadResponse;
import cs1302.api.LocationResponse;
import cs1302.api.WeatherResponse;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import javafx.scene.layout.Priority;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Class representing SpaceXLaunchApp object. The app prompts users to select a launchpad
 * from a dropdown, and select the "Search" button to query SpaceX api.
 * The SpaceX api is queried with the launchpad ID to get the location and region
 * for the actual launchpad which is fed to the weather api.
 * The user then presses the "Weather" button which sends a
 * query to the AccuWeather Locations autocomplete search api to pull a city/city id for the
 * launchpad. Then it queries the Current Weather AccuWeather api to pull current weather
 * for the launch pad location provided by SpaceX api.
 * This project includes 3 seperate api queries across two different apis,
 * all three of which involve using either prior query information or user information to generate.
 */
public class SpaceXLaunchApp extends Application {

    /** HTTP client. */
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

    private Stage stage;
    private Scene scene;
    HBox root;
    VBox mainContent;
    TopBar topBar;
    LaunchSiteInfoPanel launchSiteInfoPanel;
    WeatherInfoPanel weatherInfoPanel;
    HBox content;
    ImageView banner;
    SpaceXResponse spaceXResponse;
    LaunchpadResponse launchpadInfo;
    LocationResponse locationInfo;
    WeatherResponse weatherInfo;
    HBox infoPanels;
    boolean firstSearch;
    VBox bannerVbox;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public SpaceXLaunchApp() {
        this.stage = null;
        this.scene = null;
        this.root = new HBox();

        //Initialize component class objects
        this.topBar = new TopBar();
        this.infoPanels = new HBox();
        this.launchSiteInfoPanel = new LaunchSiteInfoPanel();
        this.weatherInfoPanel = new WeatherInfoPanel();
        this.firstSearch = true;

        // init the banner image
        Image bannerImage = new Image("file:resources/download.png");
        banner = new ImageView(bannerImage);
        banner.setPreserveRatio(false);

        // Create the bannerHbox to hold the banner in the center
        bannerVbox = new VBox(banner);
        bannerVbox.setVgrow(banner, Priority.ALWAYS);
        bannerVbox.setAlignment(Pos.CENTER);
        bannerVbox.setMinHeight(600);
        banner.setFitWidth(830);
        banner.setFitHeight(685);

        // Create the main content VBox
        mainContent = new VBox(topBar, bannerVbox);


        // Allow mainContent to grow horizontally within the root
        HBox.setHgrow(mainContent, Priority.ALWAYS);

    } // SpaceXLauncApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        // feel free to modify this method
        System.out.println("init() called");

        //Add components to mainContent
        this.mainContent = new VBox(topBar, bannerVbox);
        VBox.setVgrow(launchSiteInfoPanel, Priority.ALWAYS);
        mainContent.setMaxWidth(Double.MAX_VALUE);
        // Allow mainContent to grow horizontally within the root
        HBox.setHgrow(mainContent, Priority.ALWAYS);

        // Set the minimum width for the TopBar based on its children's combined width
        topBar.setMinWidth(Region.USE_PREF_SIZE);

        //Add mainContent to root
        this.root = new HBox(this.mainContent);
        root.setHgrow(mainContent, Priority.ALWAYS);

        //Init button handlers
        this.topBar.getSearchButton().setOnAction(event -> handleSearch());
        this.topBar.getWeatherButton().setOnAction(event -> handleGetWeather());
        //Setup

    } // init


    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;
        this.scene = new Scene(this.root, 825, 725);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("SpaceXLaunchApp!");
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));

    } // start

    /**
     * Parses a JSON-formatted string into an object of the specified type.
     * Either SpaceXResponse or LaunchpadInfo based on passed type param.
     *
     * @param <T> The type of the object to be returned.
     * @param response The JSON-formatted string.
     * @param type The class of the type to parse into.
     * @return An object of type T.
     */
    public static <T> T parseResponse(String response, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    } // parseResponse


    /**
     * Sends a GET request to the provided URI and returns the response as a string.
     *
     * @param uri The URI to send the request to.
     * @return The response body as a string.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    private String sendRequest(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to get a valid response: " + response);
        } //if

        return response.body();
    } //sendRequest()

    /**
     * Handles the search button action.
     * Sends a request to the SpaceX API, processes the response,
     * and updates the UI.
     */
    public void handleSearch() {
        //changed below to implement combobox user choice. Leaving for potential later use.
        //String nextLaunchUri = "https://api.spacexdata.com/v4/launches/next";
        Thread searchThread = new Thread (() -> {
            try {
                // Get next launch info - ommitted in favor of comboBox leaving for
                // if I want to revist after project submission
                //String response = sendRequest(nextLaunchUri);
                //SpaceXResponse spaceXResponse = parseResponse(response, SpaceXResponse.class);
                //this.spaceXResponse = spaceXResponse;

                //pull chosen combobox ID
                String launchpadId = topBar.getComboBoxId();

                // Now Get launchpad info
                String launchpadUri = "https://api.spacexdata.com/v4/launchpads/" + launchpadId;
                String launchpadResponse = sendRequest(launchpadUri);
                LaunchpadResponse launchpadInfo = parseResponse(launchpadResponse,
                    LaunchpadResponse.class);
                this.launchpadInfo = launchpadInfo;
                launchpadInfo.processImages();

                Platform.runLater(() -> {
                    // Update UI with both launch and launchpad info
                    topBar.getWeatherButton().setDisable(false);
                    topBar.setInstructionsText("Press Get Weather" +
                        " to retrieve live weather data.");
                    //updateUI(spaceXResponse, launchpadInfo);
                    launchSiteInfoPanel.updateUI(launchpadInfo);

                    //add eelements if first time search is pressed
                    if (this.firstSearch) {

                        //replace banner with mainContent (add info panel)
                        infoPanels.getChildren().addAll(launchSiteInfoPanel, weatherInfoPanel);
                        mainContent.getChildren().remove(bannerVbox);
                        mainContent.getChildren().addAll(infoPanels);

                        //update boolean
                        this.firstSearch = false;
                    } //if
                }); //runLater()

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } //try-catch

        }); //searchThread

        searchThread.setDaemon(true);
        searchThread.start();
    } //handleSearch

    /**
     * Constructs the request URI for the AccuWeather Locations Autocomplete API.
     *
     * @param locationsUri The base URI for the locations API.
     * @param key The API key for AccuWeather.
     * @param launchpadFullName The full name of the launchpad.
     * @return The complete request URI.
     */
    public String buildLocationRequestUri(String locationsUri, String key,
        String launchpadFullName) {
        String encodedLaunchpadName = URLEncoder.encode(launchpadFullName, StandardCharsets.UTF_8);
        return locationsUri + "?apikey=" + key + "&q=" + encodedLaunchpadName;
    } //buildLocationRequestUri

    /**
     * Handles the Get Weather button functionality.
     * First queries accuWeather Location api to get Location key for launchpad location.
     * Then submits request to current weather api to pull current weather for that location.
     * Lastly updates UI elements to display weather data.
     */
    public void handleGetWeather() {
        //variables for later
        String locationsUri = "http://dataservice.accuweather.com/locations/v1/cities/autocomplete";
        String launchpadLocality = launchpadInfo.getLocality();

        //Special fix for Vandenberg and Boca Chica to match locations api format
        if (launchpadLocality.equals("Vandenberg Space Force Base")) {
            launchpadLocality = "Vandenberg";
        } else if (launchpadLocality.equals("Boca Chica Village")) {
            launchpadLocality = "Brownsville";
        } //if elseif

        String launchpadRegion = launchpadInfo.getRegion();
        String key = "nE4t7HuR6KI1hwaABe6UKvn7Xul8I6iG";
        String requestUri = buildLocationRequestUri(locationsUri, key, launchpadLocality);

        //base api endpoint
        String currentWeatherUri = "http://dataservice.accuweather.com/currentconditions/v1/";

        try {
            String locationResponse = sendRequest(requestUri);
            // parse the response into an array of LocationResponse objects
            LocationResponse[] locationResponsesArray = new Gson().fromJson(locationResponse,
                LocationResponse[].class);

            if (locationResponsesArray.length > 0) {
                this.locationInfo = locationResponsesArray[0];
                // Continue with the weather request
                String locationKey = locationInfo.getKey();
                String weatherRequestUri = currentWeatherUri + locationKey + "?apikey=" + key +
                    "&details=true";
                String weatherApiResponse = sendRequest(weatherRequestUri);
                WeatherResponse[] weatherResponsesArray = new Gson().fromJson(weatherApiResponse,
                WeatherResponse[].class);
                //check respose is not 0
                if (weatherResponsesArray.length > 0) {
                    this.weatherInfo = weatherResponsesArray[0];
                    // Update UI
                    weatherInfoPanel.setLocationLabel(launchpadLocality, launchpadRegion);
                    weatherInfoPanel.updateUI(weatherInfo);
                } else {
                    // display an alert for empty weather response
                    displayAlert("No weather information available for this location.");
                } //if-else
            } else {
                // display an alert for empty location response
                displayAlert("Bad Response from Location API: No available data for location.");
            } //if-else
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // display alert for anything else
            displayAlert("An error occurred while pulling weather information.");
        } //trt-catch
    } //handleGetWeather

    /**
     * Displays a default information alert with the given content text.
     *
     * @param content The content text of the alert.
     */
    public void displayAlert(String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null); // You can set a header text if needed
        alert.setContentText(content);
        alert.showAndWait();
    } //displayAlert

} // SpaceXLaunchApp
