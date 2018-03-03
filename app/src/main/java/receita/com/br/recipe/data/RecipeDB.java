package receita.com.br.recipe.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import receita.com.br.recipe.domain.WidgetList;

/**
 * Created by Paulo on 26/02/2018.
 */

public class RecipeDB extends SQLiteOpenHelper {

    private  static final String BD_NAME = "recipe.db";
    public  static final int VERSION_DB = 1;
    private Context context;

    public RecipeDB(Context context) {
        super(context, BD_NAME, null, VERSION_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists recipe (_id integer, name text, image text );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(WidgetList widgetList ){

        SQLiteDatabase database = getWritableDatabase();
        Log.d("LOG", "INSERT "+ widgetList.getId() );
        ContentValues values = new ContentValues();
        values.put("_id", widgetList.getId());
        values.put("name", widgetList.getName());
        values.put("image", widgetList.getName());
        long count =  database.insert("recipe", "" ,values );
        database.close();


        return  count;
    }

    public long delete(WidgetList widgetList ){
        SQLiteDatabase database = getWritableDatabase();
        long count =  database.delete("recipe", "_id=?" ,
                new String[]{String.valueOf(widgetList.getId()) } );
        database.close();
        return  count;

    }
    public List<WidgetList> listAll(){
        SQLiteDatabase database = getWritableDatabase();

        List<WidgetList> widgetList = new ArrayList<>();
        Cursor c = database.query("recipe", null, null,null,null,null,null);

        if( c.moveToFirst()){
            do{

                WidgetList w = new WidgetList();
                w.setName(c.getString( c.getColumnIndex("name")));
                w.setImage(c.getString( c.getColumnIndex("image")));
                w.setId(c.getInt( c.getColumnIndex("_id")));
                widgetList.add( w );

            }while (c.moveToNext());
        }

        database.close();

        return  widgetList;
    }

    public List<WidgetList> listById( int id ){
        SQLiteDatabase database = getWritableDatabase();

        List<WidgetList> widgetList = new ArrayList<>();
        Cursor c = database.query("recipe", null, "_id="+id,null,null,null,null);

        if( c.moveToFirst()){
            do{

                WidgetList w = new WidgetList();
                w.setName(c.getString( c.getColumnIndex("name")));
                w.setImage(c.getString( c.getColumnIndex("image")));
                w.setId(c.getInt( c.getColumnIndex("_id")));

                widgetList.add( w );

            }while (c.moveToNext());
        }

        database.close();

        return  widgetList;
    }


}
