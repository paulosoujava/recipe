package receita.com.br.recipe.fragment;
import android.content.Intent;
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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import receita.com.br.recipe.R;
import receita.com.br.recipe.activity.VideoActivity;
import receita.com.br.recipe.adapter.StepsAdapter;
import receita.com.br.recipe.domain.Recipe;
import receita.com.br.recipe.domain.Step;


/**
 * Created by PedroFelipe on 08/10/2015.
 */
public class FragmentSteps extends Fragment implements StepsAdapter.ClickInMyAdpater {

    private Unbinder unbinder;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    Recipe recipe;
    StepsAdapter adapter;
    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_generic_fragments, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setTag("step");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

         bundle = getActivity().getIntent().getExtras();

        if( bundle != null ){
            recipe = bundle.getParcelable(Recipe.PARCELABLE_KEY);
            adapter = new StepsAdapter(getActivity(), recipe.getSteps());
            adapter.setClickInMyAdpater(this);
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
    public void clickMyAdapter(View v, int position, Step step) {
        Intent it = new Intent(getActivity(), VideoActivity.class);
        it.putExtra(Recipe.PARCELABLE_KEY, recipe.getSteps().get(position));
        startActivity(it);
    }


}