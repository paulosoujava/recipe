package receita.com.br.recipe.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import receita.com.br.recipe.R;
import receita.com.br.recipe.adapter.IngredientsAdapter;
import receita.com.br.recipe.domain.Ingredients;
import receita.com.br.recipe.domain.Recipe;


public class FragmentIngredientsTablet extends Fragment implements IngredientsAdapter.ClickInMyAdpater {

    private Unbinder unbinder;
    @BindView(R.id.recyclerViewGeneric)
    RecyclerView recyclerView;
    Recipe recipe;
    IngredientsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_generic_fragments, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setTag("ingredients");


        recyclerView.setHasFixedSize(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = getActivity().getIntent().getExtras();

        if( bundle != null ){
            recipe = bundle.getParcelable(Recipe.PARCELABLE_KEY);
            adapter = new IngredientsAdapter(getActivity(), recipe.getIngredients());
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void clickMyAdapter(View v, int position, Ingredients ingredients) { }
}