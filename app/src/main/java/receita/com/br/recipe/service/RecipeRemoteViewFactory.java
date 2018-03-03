package receita.com.br.recipe.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;
import java.util.Random;

import receita.com.br.recipe.R;
import receita.com.br.recipe.data.RecipeDB;
import receita.com.br.recipe.domain.WidgetList;
import receita.com.br.recipe.util.AndroidImageAssets;
import receita.com.br.recipe.util.RecipeAppWidget;


/**
 * Created by Paulo on 27/02/2018.
 */

public class RecipeRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<WidgetList> mList;
    private RecipeDB db;
    private Random gerador = new Random();


    public RecipeRemoteViewFactory(Context context, Intent intent) {
        mContext = context;
        db = new RecipeDB(context);
    }

    @Override
    public void onCreate() {
        mList = db.listAll();
    }

    @Override
    public void onDataSetChanged() {
        Log.d("LOG", "onDataSetChanged" + mList.size());
        mList = db.listAll();
    }

    @Override
    public void onDestroy() {
        mList.clear();
    }

    @Override
    public int getCount() {
        Log.d("LOG", "getCount" + mList.size());
        return mList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);
        views.setTextViewText(R.id.tv_model, mList.get(position).getName());


        views.setImageViewResource(R.id.iv_recipe, AndroidImageAssets.image.get(gerador.nextInt(AndroidImageAssets.image.size())));

        Intent itFilter = new Intent();
        itFilter.putExtra(RecipeAppWidget.FILTER_RECIPE_ITEM, String.valueOf(mList.get(position).getId() ) );
        itFilter.putExtra(RecipeAppWidget.FILTER_RECIPE_NAME, String.valueOf(mList.get(position).getName() ) );
        views.setOnClickFillInIntent(R.id.rl_container, itFilter);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
