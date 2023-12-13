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

import cs1302.api.LaunchpadResponse; // Import your LaunchpadResponse class

/**
 * Component class for the Launch Site Info panel UI element.
 */
public class LaunchSiteInfoPanel extends VBox {

    private Label titleLabel;
    private ImageView launchSiteImage;
    private Label fullName;
    private Label locality;
    private Label region;
    private Label latitude;
    private Label longitude;
    private Image launchSiteDefault;
    private Label launches;
    private Label details;
    private ScrollPane scrollPane;
    private Region spacer1;
    private Region spacer2;

    /**
     * Constructor for LaunchSiteInfoPanel class object.
     */
    public LaunchSiteInfoPanel() {
        // init UI javafx components
        setupUI();
    } // LaunchSiteInfoPanel

    /**
     * Initializes components for LaunchSiteInfoPanel class object.
     */
    private void setupUI() {
        // spacing and padding for the VBox
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.TOP_CENTER);

        // make component title
        titleLabel = new Label("Launch Site Information");

        // init ImageView for the launch site image
        launchSiteDefault = new Image("file:resources/download.png");
        launchSiteImage = new ImageView(launchSiteDefault);
        launchSiteImage.setFitHeight(200); // Set preferred height
        launchSiteImage.setFitWidth(300); // Set preferred width
        launchSiteImage.setPreserveRatio(true);

        // Initialize other labels for data
        fullName = new Label("Name: ");
        locality = new Label("Locality: ");
        region = new Label("Region: ");
        latitude = new Label("Latitude: ");
        longitude = new Label("Longitude: ");
        launches = new Label("Launches: ");
        details = new Label("Details: ");
        scrollPane = new ScrollPane();
        scrollPane.setContent(details);
        details.setWrapText(true);
        details.setMaxHeight(Double.MAX_VALUE);

        //init spacer between image and details
        spacer1 = new Region();
        spacer2 = new Region();
        spacer1.setPrefHeight(20);
        spacer2.setPrefHeight(20);

        // Add individual components to VBox
        this.getChildren().addAll(titleLabel, launchSiteImage, spacer1, fullName,
            locality, region, latitude, longitude, launches, details, spacer2);
    } // setupUI

    /**
     * Updates the UI elements with data from the given LaunchpadResponse.
     * Easiser to pass LaunchpadResponse object than setters/getters since LPR already
     * has them.
     *
     * @param launchpadResponse the LaunchpadResponse object containing launch site data.
     */
    public void updateUI(LaunchpadResponse launchpadResponse) {
        //Update elements with values from LaunchpadResponse
        Image image = new Image(launchpadResponse.getFirstLargeImageUrl(), true);
        launchSiteImage.setImage(image);
        fullName.setText("Name: " + launchpadResponse.getFullName());
        locality.setText("Locality: " + launchpadResponse.getLocality());
        region.setText("Region: " + launchpadResponse.getRegion());
        latitude.setText("Latitude: " + launchpadResponse.getLatitude());
        longitude.setText("Longitude: " + launchpadResponse.getLongitude());
        launches.setText("Launches: " + launchpadResponse.getLaunches());
        details.setText("Details: " + launchpadResponse.getDetails());
        this.getChildren().remove(spacer2);
    } // updateUI

} // LaunchSiteInfoPanel
