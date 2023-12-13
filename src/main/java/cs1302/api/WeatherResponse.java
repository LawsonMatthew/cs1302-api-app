package cs1302.api;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a response from the AccuWeather Current Weather API.
 * The response format includes several different measurements which include
 * values for imperial and metric measurements. This file contains specific
 * sub classes for Temperature, RealFealTemperature, RelativeHumidity, UVindex,
 * which are necessitated by response format.
 */
public class WeatherResponse {
    @SerializedName("LocalObservationDateTime")
    private String localObservationDateTime;

    @SerializedName("WeatherText")
    private String weatherText;

    @SerializedName("WeatherIcon")
    private int weatherIcon;

    @SerializedName("Temperature")
    private TemperatureData temperature;

    @SerializedName("RealFeelTemperature")
    private TemperatureData realFeelTemperature;

    @SerializedName("PrecipitationType")
    private String precipitationType;

    @SerializedName("RelativeHumidity")
    private int relativeHumidity;

    @SerializedName("UVIndex")
    private int uvIndex;

    @SerializedName("UVIndexText")
    private String uvIndexText;

    /**
     * Gets the local observation date and time.
     *
     * @return the local observation date and time.
     */
    public String getLocalObservationDateTime() {
        return localObservationDateTime;
    } //getLocalObservatonDateTime

    /**
     * Gets the weather text description.
     *
     * @return the weather text description.
     */
    public String getWeatherText() {
        return weatherText;
    } //getWEatherText

    /**
     * Gets the weather icon ID.
     *
     * @return the weather icon ID.
     */
    public int getWeatherIcon() {
        return weatherIcon;
    } //getWeatherIcon

    /**
     * Gets the temperature data.
     *
     * @return the temperature data.
     */
    public TemperatureData getTemperature() {
        return temperature;
    } //getTemperature

    /**
     * Gets the real feel temperature data.
     *
     * @return the real feel temperature data.
     */
    public TemperatureData getRealFeelTemperature() {
        return realFeelTemperature;
    } //getRealFeelTemperature

    /**
     * Gets the type of precipitation.
     *
     * @return the type of precipitation.
     */
    public String getPrecipitationType() {
        return precipitationType;
    } //getPrecpitationType

    /**
     * Gets the relative humidity percentage.
     *
     * @return the relative humidity percentage.
     */
    public int getRelativeHumidity() {
        return relativeHumidity;
    } //getRelativeHumidity

    /**
     * Nested class to represent temperature data object in the weather response.
     */
    public static class TemperatureData {
        @SerializedName("Imperial")
        private Temperature imperial;

        /**
         * Gets the imperial temperature data.
         *
         * @return the imperial temperature data.
         */
        public Temperature getImperial() {
            return imperial;
        } //getImperial
    } //TempData

    /**
     * Nested class to represent detailed temperature information
     * within each temp object.
     */
    public static class Temperature {
        @SerializedName("Value")
        private double value;

        @SerializedName("Unit")
        private String unit;

        @SerializedName("UnitType")
        private int unitType;

        /**
         * Gets the temperature value.
         *
         * @return the temperature value.
         */
        public double getValue() {
            return value;
        } //getValue

        /**
         * Gets the temperature unit.
         *
         * @return the temperature unit.
         */
        public String getUnit() {
            return unit;
        } //getUnit

        /**
         * Gets the unit type for the temperature.
         *
         * @return the unit type.
         */
        public int getUnitType() {
            return unitType;
        } //getUnitTypepe
    } //Temp

    /**
     * Gets the UV index.
     *
     * @return the UV index.
     */
    public int getUvIndex() {
        return uvIndex;
    } //getUvIndex

    /**
     * Gets the UV index text.
     *
     * @return the UV index.
     */
    public String getUvIndexText() {
        return uvIndexText;
    } //getUvIndexText

} //WeatheResponse
