package cs1302.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/**
 * Represents information about a SpaceX launchpad.
 * Maps to the JSON structure returned by the SpaceX API for launchpad data.
 */
public class LaunchpadResponse {

    private String name;
    private Map<String, List<String>> images;
    private String firstLargeImageUrl;
    @SerializedName("full_name")
    private String fullName;  // Java variable in camelCase, JSON in snake case, conversion.
    private String locality;
    private String region;
    private double latitude;
    private double longitude;
    private String timezone;
    private String details;
    @SerializedName("launch_attempts")
    private int launches;

    /**
     * Processes the images field to extract the first large image URL.
     */
    public void processImages() {
        if (images != null && images.containsKey("large") && !images.get("large").isEmpty()) {
            firstLargeImageUrl = images.get("large").get(0);
        } //if
    } //processImages

    /**
     * Method to get the url of the first image.
     * @return firstLargeImageUrl
     */
    public String getFirstLargeImageUrl() {
        return firstLargeImageUrl;
    } //getFirstLargeImageUrl

    /**
     * Gets the name of the launchpad.
     *
     * @return the name of the launchpad.
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Sets the name of the launchpad.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    } // setName

    /**
     * Gets the full name of the launchpad.
     *
     * @return the full name of the launchpad.
     */
    public String getFullName() {
        return fullName;
    } // getFull_name

    /**
     * Sets the full name of the launchpad.
     *
     * @param full_name the full name to set.
     */
    public void setFullName(String full_name) {
        this.fullName = fullName;
    } // setFull_name

    /**
     * Gets the locality of the launchpad.
     *
     * @return the locality of the launchpad.
     */
    public String getLocality() {
        return locality;
    } // getLocality

    /**
     * Sets the locality of the launchpad.
     *
     * @param locality the locality to set.
     */
    public void setLocality(String locality) {
        this.locality = locality;
    } // setLocality

    /**
     * Gets the region where the launchpad is located.
     *
     * @return the region of the launchpad.
     */
    public String getRegion() {
        return region;
    } // getRegion

    /**
     * Sets the region where the launchpad is located.
     *
     * @param region the region to set.
     */
    public void setRegion(String region) {
        this.region = region;
    } // setRegion

    /**
     * Gets the latitude of the launchpad's location.
     *
     * @return the latitude of the launchpad.
     */
    public double getLatitude() {
        return latitude;
    } // getLatitude

    /**
     * Sets the latitude of the launchpad's location.
     *
     * @param latitude the latitude to set.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    } // setLatitude

    /**
     * Gets the longitude of the launchpad's location.
     *
     * @return the longitude of the launchpad.
     */
    public double getLongitude() {
        return longitude;
    } // getLongitude

    /**
     * Sets the longitude of the launchpad's location.
     *
     * @param longitude the longitude to set.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    } // setLongitude

    /**
     * Gets the timezone of the launchpad.
     *
     * @return the timezone of the launchpad.
     */
    public String getTimezone() {
        return timezone;
    } // getTimezone

    /**
     * Sets the timezone of the launchpad.
     *
     * @param timezone the timezone to set.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    } // setTimezone

    /**
     * Gets the details information about the launchpad.
     *
     * @return the details of the launchpad.
     */
    public String getDetails() {
        return details;
    } // getDetails

    /**
     * Gets the number of completed launches from the launchpad.
     *
     * @return launches
     */
    public int getLaunches() {
        return this.launches;
    } //getLaunches

} // LaunchpadResponse
