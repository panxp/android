package basketball.com.sports.util;



import java.util.ArrayList;

import basketball.com.sports.model.Video;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by 南尘 on 16-7-15.
 */
public interface Service {
    @GET("/")//网址下面的子目录
    Call<String> getBaidu();

    @GET("scenics/video")//网址下面的子目录   category表示分类，因为子目录只有一点不一样
    Call<ArrayList<Video>> getList();

    @POST("/api/{category}/list")//使用Post实现
    @FormUrlEncoded  //使用Field属性必须添加这个
    Call<Video> getListForPost(@Path("category") String path, @Field("id") int id, @Field("page") int page, @Field("rows") int rows);


}
