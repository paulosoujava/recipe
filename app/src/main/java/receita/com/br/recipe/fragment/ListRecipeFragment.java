package receita.com.br.recipe.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import receita.com.br.recipe.R;
import receita.com.br.recipe.activity.DetailActivity;
import receita.com.br.recipe.adapter.RecipeAdapter;
import receita.com.br.recipe.api.Client;
import receita.com.br.recipe.api.Service;
import receita.com.br.recipe.domain.Recipe;
import receita.com.br.recipe.domain.WidgetList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Paulo on 19/02/2018.
 */

public class ListRecipeFragment extends Fragment implements RecipeAdapter.ClickInMyAdpater {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;

    Recipe[] mRecipes;
    RecipeDownloadTask mTask;
    Context mContext;

    private Unbinder unbinder;

    Service apiService = Client.getClient().create(Service.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recipe, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTask = new RecipeDownloadTask();
                mTask.execute();
            }
        });

        recyclerView.setTag("web");
        recyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mRecipes == null) {
            if (mTask == null) {
                mTask = new RecipeDownloadTask();
                mTask.execute();
            } else if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
                exibirProgresso();
            }
        } else {
            atualizarLista();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        mContext = context;
    }


    // used by EventBus
    public void onEvent(WidgetList w){
        Toast.makeText(mContext, "Recipe: "+ w.getName(), Toast.LENGTH_SHORT ).show();
        for( int i = 0; i < mRecipes.length; i++ ){
            if( mRecipes[i].getId() == w.getId()){
                Intent it = new Intent( mContext, DetailActivity.class);
                it.putExtra(Recipe.PARCELABLE_KEY,mRecipes[i]);
                mContext.startActivity(it);
                break;
            }
        }
    }


    @Override
    public void clickMyAdapter(View v, int position, Recipe recipe) {
        Intent it = new Intent(getActivity(), DetailActivity.class);
        it.putExtra(Recipe.PARCELABLE_KEY, recipe);
        startActivity(it);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void atualizarLista() {
        RecipeAdapter adapter = new RecipeAdapter(getActivity(), mRecipes);
        adapter.setClickInMyAdpater(this);
        recyclerView.setAdapter(adapter);
    }

    private void exibirProgresso() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }
    private void fecharProgresso() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    class RecipeDownloadTask extends AsyncTask<Void, Void, Recipe[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgresso();
        }

        @Override
        protected Recipe[] doInBackground(Void... voids) {
            Call<Recipe[]> call = apiService.getAll();
            call.enqueue(new Callback<Recipe[]>() {
                @Override
                public void onResponse(Call<Recipe[]> call, Response<Recipe[]> response) {
                    mRecipes =  new Recipe[response.body().length - 1  ];
                    if (response.body() != null) {
                        mRecipes = response.body();
                        atualizarLista();
                        fecharProgresso();
                    }
                }

                @Override
                public void onFailure(Call<Recipe[]> call, Throwable t) {
                    Log.d("LOG", "doInBackground "+ t.getCause()+" "+t.getMessage() );
                }
            });
            return mRecipes;
        }

        @Override
        protected void onPostExecute(Recipe[] recipes) {
            super.onPostExecute(recipes);
            fecharProgresso();
            mRecipes = recipes;
            atualizarLista();
        }
    }




}
