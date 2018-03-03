package receita.com.br.recipe.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
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
import receita.com.br.recipe.adapter.StepsAdapter;
import receita.com.br.recipe.adapter.VideoAdapter;
import receita.com.br.recipe.domain.Recipe;
import receita.com.br.recipe.domain.Step;


/**
 * Created by PedroFelipe on 08/10/2015.
 */
public class FragmentStepsTablet extends Fragment implements StepsAdapter.ClickInMyAdpater {

    private Unbinder unbinder;
    @BindView(R.id.recyclerViewMenu)
    RecyclerView recyclerViewMenu;
    @BindView(R.id.recyclerViewGeneric)
    RecyclerView recyclerViewGeneric;
    Recipe recipe;
    StepsAdapter adapter;
    VideoAdapter videoAdapter;

    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_generic_fragments, container, false);
        unbinder = ButterKnife.bind(this, view);

        //menu
        recyclerViewMenu.setTag("menu");
        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());


        //video
        recyclerViewGeneric.setTag("video");
        recyclerViewGeneric.setHasFixedSize(true);
        recyclerViewGeneric.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewGeneric.setItemAnimator(new DefaultItemAnimator());

        bundle = getActivity().getIntent().getExtras();

        if( bundle != null ){
            recipe = bundle.getParcelable(Recipe.PARCELABLE_KEY);
            adapter = new StepsAdapter(getActivity(), recipe.getSteps());
            adapter.setClickInMyAdpater(this);
            recyclerViewMenu.setAdapter(adapter);

            videoAdapter = new VideoAdapter(getActivity(), recipe.getSteps().get(0) );

            recyclerViewGeneric.setAdapter(videoAdapter);
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
        videoAdapter = new VideoAdapter(getActivity(), recipe.getSteps().get(position) );
        recyclerViewGeneric.setAdapter(videoAdapter);

    }

}