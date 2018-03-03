package receita.com.br.recipe.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Paulo on 27/02/2018.
 */

public class RecipeService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewFactory( this.getApplicationContext(), intent);
    }
}
