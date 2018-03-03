package receita.com.br.recipe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.adapter.MyFragmentPagerAdapter;
import receita.com.br.recipe.domain.Recipe;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;


public class DetailActivity extends AppCompatActivity {


    private Recipe recipe;

    @BindView(R.id.tab_layout)
     TabLayout mTabLayout;

    @BindView(R.id.view_pager)
     ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ButterKnife.bind(this);
        mTabLayout.setupWithViewPager(mViewPager);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mViewPager.setAdapter(new MyFragmentPagerAdapter(
                    getSupportFragmentManager(),
                    getResources().getStringArray(R.array.titles_tab),
                    bundle,
                    (getResources().getBoolean(R.bool.isTablet))? true: false

            ));


        } else {
            Toast.makeText(this, R.string.no_has_data_sorry, Toast.LENGTH_SHORT).show();
            back(null);
        }


    }


    public void back(View view) {
        onBackPressed();
        }


}
