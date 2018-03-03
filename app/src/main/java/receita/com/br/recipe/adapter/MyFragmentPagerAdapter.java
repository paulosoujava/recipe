package receita.com.br.recipe.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import receita.com.br.recipe.R;
import receita.com.br.recipe.fragment.FragmentIngredients;
import receita.com.br.recipe.fragment.FragmentIngredientsTablet;
import receita.com.br.recipe.fragment.FragmentSteps;
import receita.com.br.recipe.fragment.FragmentStepsTablet;


/**
 * Created by PedroFelipe on 08/10/2015.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabTitles;
    private Bundle bundle;
    private static final int TAB_STEP = 0;
    private static final int TAB_INGREDIENT = 1;
    private   boolean isTablet;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTabTitles, Bundle b, boolean isTablet) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.bundle = b;
        this.isTablet = isTablet;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position) {
            case TAB_STEP:

                if (isTablet)
                    f = new FragmentStepsTablet();
                else
                    f = new FragmentSteps();

                f.setArguments(bundle);

                return f;
            case TAB_INGREDIENT:

                if (isTablet)
                    f = new FragmentIngredientsTablet();
                else
                    f = new FragmentIngredients();

                f.setArguments(bundle);

                return f;
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}