package receita.com.br.recipe.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Paulo on 23/02/2018.
 */

public class Prefs {

    public static final String PREF_ID = "receita.com.br.recipe.util.recipe_pref";

    public  static  void setInt(Context context, String key, int value ){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0 );
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt( key, value);
        editor.commit();
    }

    public  static  int getInt(Context context, String key  ){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0 );

        return pref.getInt(key, 0);
    }

    public  static  void setString(Context context, String key, String valor ){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0 );
        SharedPreferences.Editor editor = pref.edit();
        editor.putString( key, " ");
        editor.commit();
    }

    public  static  String getString(Context context, String key  ){
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0 );

        return pref.getString(key, "");
    }
}
