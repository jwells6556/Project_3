
package shuvalov.nikita.restaurantroulette.YelpResources.YelpObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    public Location(String address1, String address2, String address3, String city, String country, String state, String zipCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
    }

    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("address3")
    @Expose
    private String address3;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;



    /**
     * 
     * @return
     *     The address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * 
     * @param address1
     *     The address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * 
     * @param address2
     *     The address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * 
     * @param address3
     *     The address3
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * 
     * @return
     *     The zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 
     * @param zipCode
     *     The zip_code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
