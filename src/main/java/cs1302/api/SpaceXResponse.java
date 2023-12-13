package cs1302.api;

/**
 * Represents a response from the SpaceX API for the next upcoming launch.
 */
public class SpaceXResponse {

    private String flightNumber;
    private String name;
    private String date;
    private String launchpad;
    private String webcastLink;

    /**
     * Gets the flight number.
     *
     * @return the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    } //getFlightNumber()

    /**
     * Sets the flight number.
     *
     * @param flightNumber the flight number to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    } //setFlightNumber()

    /**
     * Gets the name of the launch.
     *
     * @return the name of the launch
     */
    public String getName() {
        return name;
    } //getName()

    /**
     * Sets the name of the launch.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    } //setName()

    /**
     * Gets the launch date.
     *
     * @return the launch date
     */
    public String getDate() {
        return date;
    } //getDtae()

    /**
     * Sets the launch date.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    } //setDate()

    /**
     * Gets the launchpad identifier.
     *
     * @return the launchpad identifier
     */
    public String getLaunchpad() {
        return launchpad;
    } //getLaunchPad

    /**
     * Sets the launchpad identifier.
     *
     * @param launchpad the launchpad identifier to set
     */
    public void setLaunchpad(String launchpad) {
        this.launchpad = launchpad;
    } //setLaunchPad

    /**
     * Gets the webcast link.
     *
     * @return the webcast link
     */
    public String getWebcastLink() {
        return webcastLink;
    } //getWebcast()

    /**
     * Sets the webcast link.
     *
     * @param webcastLink the webcast link to set
     */
    public void setWebcastLink(String webcastLink) {
        this.webcastLink = webcastLink;
    } //setWebcast()

} //SpaceXResponse
