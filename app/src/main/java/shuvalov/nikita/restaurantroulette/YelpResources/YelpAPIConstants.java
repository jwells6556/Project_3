package shuvalov.nikita.restaurantroulette.YelpResources;

import java.util.ArrayList;

/**
 * Created by NikitaShuvalov on 11/30/16.
 */

public class YelpAPIConstants {
    public static final String YELP_GRANT_TYPE = "client_credentials";
    public static final String YELP_BEARER_TOKEN = "NA8-lx7QOQKK1e8u6CZqNXqli320QXYVoXMELvv5BVwdoUDPWY7HrpeRb7EHWwygPrBVRxESrRGxtr1PxGOY0N2i24vI6_ASJ7NQsLyu37ARsT3X3W02HGmi8xQ2WHYx";
    public static final String YELP_CLIENT_ID = "9YI_a1fQDy1CtSBiZsq6Yw";
    public static final String YELP_APP_SECRET = "edmHqYlFEY4SxJWqkgM76RmkVaXnRyr6UUdc9jXzQglM8TCELkWRGN37CBG3Ztpy";
    public static final String YELP_TOKEN_BASE_URL = "https://api.yelp.com/oauth2/";
    public static final String YELP_SEARCH_BASE_URL = "https://api.yelp.com/v3/businesses/";

    //NOTIFICATION VALUES TO PASS THROUGH INTENT
    public static final String NOTIF_PRICE = "price";
    public static final String NOTIF_IMAGE_URL = "image_url";
    public static final String NOTIF_PHONE_NUMBER = "phone";
    public static final String NOTIF_IS_CLOSED = "is_closed";
    public static final String NOTIF_BUSINESS_URL = "url";
    public static final String NOTIF_BUSINESS_ID = "id";
    public static final String NOTIF_REVIEW_COUNT = "review_count";
    public static final String NOTIF_RATING = "rating";
    public static final String NOTIF_DISTANCE = "distance";
    public static final String NOTIF_BUSINESS_NAME = "name";
    public static final String NOTIF_ADDRESS_1 = "address1";
    public static final String NOTIF_CITY = "city";
    public static final String NOTIF_LATITUTE = "lat";
    public static final String NOTIF_LONGITUDE = "lon";


    //Query Categories
    public static final String CATEGORY_RESTAURANTS = "restaurants";
    public static final String CATEGORY_AMUSEMENT_PARK = "amusementparks";
    public static final String CATEGORY_AQUARIUMS = "aquariums";
    public static final String CATEGORY_BOWLING = "bowling";
    public static final String CATEGORY_PARKS = "parks";
    public static final String CATEGORY_ARTS = "arts";
    public static final String CATEGORY_BARS= "bars";
    public static final String CATEGORY_DANCECLUBS = "danceclubs";
    public static final String CATEGORY_JAZZANDBLUES = "jazzandblues";
    public static final String CATEGORY_KARAOKE = "karaoke";
    public static final String CATEGORY_PIANOBARS = "pianobars";
    public static final String CATEGORY_POOLHALLS = "poolhalls";

    public static ArrayList<String> getCategoryList(){
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add(CATEGORY_RESTAURANTS);
        categoryList.add(CATEGORY_BARS);
        categoryList.add(CATEGORY_ARTS);
        categoryList.add(CATEGORY_PARKS);
        categoryList.add(CATEGORY_JAZZANDBLUES);
        categoryList.add(CATEGORY_KARAOKE);
        categoryList.add(CATEGORY_PIANOBARS);
        categoryList.add(CATEGORY_DANCECLUBS);
        categoryList.add(CATEGORY_BOWLING);
        categoryList.add(CATEGORY_POOLHALLS);
        categoryList.add(CATEGORY_AQUARIUMS);
        categoryList.add(CATEGORY_AMUSEMENT_PARK);
        return categoryList;
    }


}
