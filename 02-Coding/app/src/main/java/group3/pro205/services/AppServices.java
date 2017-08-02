package group3.pro205.services;

import retrofit.Callback;
import retrofit.http.GET;
import group3.pro205.entry.EnCategory;
import group3.pro205.entry.Enplace;



public interface AppServices {

    @GET("/category")
     void getAllCategory(Callback<EnCategory> enCategoryCallback);
    @GET("/place")
      void getAllplace(Callback<Enplace> enplaceCallback);

}
