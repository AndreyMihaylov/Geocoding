package RetrofitVersion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Geocoding {


    @GET("/api/geocode/json")
    Call<String> getData(@Query("address") String address, @Query("key") String key);

    enum Lang{
        RU, US
    }
}
