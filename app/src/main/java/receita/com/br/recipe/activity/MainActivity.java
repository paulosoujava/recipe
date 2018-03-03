package receita.com.br.recipe.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import receita.com.br.recipe.R;
import receita.com.br.recipe.domain.WidgetList;
import receita.com.br.recipe.util.RecipeAppWidget;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null && getIntent().getStringExtra(RecipeAppWidget.FILTER_RECIPE_ITEM) != null) {
            WidgetList w = new WidgetList();
            w.setId(Integer.parseInt(getIntent().getStringExtra(RecipeAppWidget.FILTER_RECIPE_ITEM)));
            w.setName(getIntent().getStringExtra(RecipeAppWidget.FILTER_RECIPE_NAME));
            setIntent(null);

            EventBus.getDefault().post(w);
        }
    }
}
