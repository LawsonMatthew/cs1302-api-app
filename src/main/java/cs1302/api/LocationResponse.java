package cs1302.api;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a location response from the AccuWeather Locations API.
 * Should really only use the key variable but includes getters and
 * setters for all api response information variables.
 */
public class LocationResponse {

    //Api response names are capitalized, use Serialized Names again
    @SerializedName("Version")
    private int version;
    @SerializedName("Key")
    private String key;
    @SerializedName("Type")
    private String type;
    @SerializedName("Rank")
    private int rank;
    @SerializedName("LocalizedName")
    private String localizedName;


    /**
     * Gets the API version.
     *
     * @return the API version.
     */
    public int getVersion() {
        return version;
    } //getVersion

    /**
     * Sets the API version.
     *
     * @param Version the API version to set.
     */
    public void setVersion(int Version) {
        this.version = Version;
    } //setVersion

    /**
     * Gets the location key.
     *
     * @return the location key.
     */
    public String getKey() {
        return key;
    } //getKey

    /**
     * Sets the location key.
     *
     * @param Key the location key to set.
     */
    public void setKey(String Key) {
        this.key = Key;
    } //setKey

    /**
     * Gets the location type.
     *
     * @return the location type.
     */
    public String getType() {
        return type;
    } //getType

    /**
     * Sets the location type.
     *
     * @param Type the location type to set.
     */
    public void setType(String Type) {
        this.type = Type;
    } //setType

    /**
     * Gets the rank.
     *
     * @return the rank.
     */
    public int getRank() {
        return rank;
    } //getRanks

    /**
     * Sets the rank.
     *
     * @param Rank the rank to set.
     */
    public void setRank(int Rank) {
        this.rank = Rank;
    } //setRank

    /**
     * Gets the localized name.
     *
     * @return the localized name.
     */
    public String getLocalizedName() {
        return localizedName;
    } //getLocalizedNamee

    /**
     * Sets the localized name.
     *
     * @param LocalizedName the localized name to set.
     */
    public void setLocalizedName(String LocalizedName) {
        this.localizedName = LocalizedName;
    } //setLocalizedName

} //LocationResponse
