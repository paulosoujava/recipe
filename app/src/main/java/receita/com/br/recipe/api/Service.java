package receita.com.br.recipe.api;



import receita.com.br.recipe.domain.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Paulo on 03/02/2018.
 */

public interface Service {

    @GET("baking.json")
    Call<Recipe[]> getAll();



}
