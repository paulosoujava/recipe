package receita.com.br.recipe.util;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import receita.com.br.recipe.R;
import receita.com.br.recipe.activity.MainActivity;
import receita.com.br.recipe.service.RecipeService;

public class RecipeAppWidget extends AppWidgetProvider {

    public static final String LOAD_RECIPE = "receita.com.br.recipe.util.LOAD_RECIPE";
    public static final String FILTER_RECIPE = "receita.com.br.recipe.util.FILTER_RECIPE";
    public static final String FILTER_RECIPE_ITEM = "receita.com.br.recipe.util.FILTER_RECIPE_ITEM";
    public static final String FILTER_RECIPE_NAME = "receita.com.br.recipe.util.FILTER_RECIPE_NAME";



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LOG", "onReceive");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        if( intent != null ){
            if( intent.getAction().equalsIgnoreCase( LOAD_RECIPE ) ){
                int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

                if( appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID ){
                    //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.lv_collection);
                    onUpdate(context, appWidgetManager, new int[]{appWidgetId});
                }
            }
            if( intent.getAction().equalsIgnoreCase( FILTER_RECIPE ) ){
                String id = intent.getStringExtra(FILTER_RECIPE_ITEM);
                String name = intent.getStringExtra(FILTER_RECIPE_NAME);

                Intent it = new Intent(context, MainActivity.class);
                it.putExtra(FILTER_RECIPE_ITEM, id);
                it.putExtra(FILTER_RECIPE_NAME, name);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            }
        }
                super.onReceive(context, intent);
    }

    @Override
    public  void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; ++i) {
            Intent itService = new Intent(context, RecipeService.class);
            itService.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i] );

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                views.setRemoteAdapter(R.id.lv_collection, itService);
            }else{
                views.setRemoteAdapter(appWidgetIds[i],R.id.lv_collection, itService);
            }



            views.setEmptyView(R.id.lv_collection, R.id.tv_loading);

            Intent itLoad = new Intent(context, RecipeAppWidget.class);
            itLoad.setAction( LOAD_RECIPE );
            itLoad.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i] );
            PendingIntent piLoadCars = PendingIntent.getBroadcast(context, 0, itLoad, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent( R.id.iv_update_collection, piLoadCars);

            Intent itOpen = new Intent(context, MainActivity.class);
            PendingIntent piOpen = PendingIntent.getActivity(context, 0, itOpen, 0);
            views.setOnClickPendingIntent( R.id.iv_open_app, piOpen);

            Intent itFilter = new Intent(context, RecipeAppWidget.class);
            itFilter.setAction( FILTER_RECIPE );
            PendingIntent piFilter = PendingIntent.getBroadcast(context, 0, itFilter, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.lv_collection, piFilter);

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.lv_collection);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

}

