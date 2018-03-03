package receita.com.br.recipe.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Paulo on 03/02/2018.
 */

public class Client {

    public static final String WEBSERVICE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public  static Retrofit retrofit = null;

    public  static Retrofit getClient(){
        if( retrofit == null ){
            retrofit = new Retrofit.Builder().baseUrl(WEBSERVICE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }
}
