package receita.com.br.recipe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.data.RecipeDB;
import receita.com.br.recipe.domain.Recipe;
import receita.com.br.recipe.domain.WidgetList;
import receita.com.br.recipe.util.AndroidImageAssets;


/**
 * Created by Paulo on 19/02/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final Recipe[] recipes;
    private Context context;
    private ClickInMyAdpater mClickInMyAdpater;
    private Random gerador = new Random();
    private RecipeDB db;


    public RecipeAdapter(Context context, Recipe[] recipes) {
        this.recipes = recipes;
        this.context = context;
    }


    public void setClickInMyAdpater(ClickInMyAdpater gca) {
        mClickInMyAdpater = gca;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        RecipeViewHolder rVh = new RecipeViewHolder(view);
        view.setTag(rVh);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickInMyAdpater != null) {
                    RecipeViewHolder rVh = (RecipeViewHolder) view.getTag();
                    int position = rVh.getAdapterPosition();
                    mClickInMyAdpater.clickMyAdapter(view, position, recipes[position]);
                }
            }
        });
        db = new RecipeDB(context);


        return rVh;


    }

    public interface ClickInMyAdpater {
        void clickMyAdapter(View v, int position, Recipe recipe);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {


        final Recipe recipe = recipes[position];
        if (recipe.getImage().isEmpty())
            holder.photo.setImageResource(AndroidImageAssets.image.get(gerador.nextInt(AndroidImageAssets.image.size())));
        else {
            //download photo with picasso
            Picasso.with(context).load(recipe.getImage())
                    .fit()
                    .into(holder.photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    });

        }
        if (checkFavorite(recipe.getId())) {
            holder.favorite.setImageResource(R.drawable.ic_full_star);
            holder.favorite.setColorFilter(Color.GREEN);
            holder.title.setTextColor(Color.GREEN);

        } else {
            holder.favorite.setImageResource(R.drawable.ic_empty_star);
            holder.title.setTextColor(Color.BLACK);
        }


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!checkFavorite(recipe.getId())) {

                    if (favorite(recipe)) {
                        holder.favorite.setImageResource(R.drawable.ic_full_star);
                        holder.favorite.setColorFilter(Color.GREEN);
                        holder.title.setTextColor(Color.GREEN);

                    }

                } else {

                    if (remove(recipe)) {
                        holder.favorite.setImageResource(R.drawable.ic_empty_star);
                        holder.favorite.setColorFilter(Color.BLACK);
                        holder.title.setTextColor(Color.BLACK);

                    }

                }


            }
        });
        holder.title.setText(recipe.getName());


        holder.subTitle.setText(recipe.getSteps().get(position).getShortDescription());
        holder.serving.setText(context.getString(R.string.serving) + " " + recipe.getServings());


    }


    @Override
    public int getItemCount() {
        return this.recipes != null ? this.recipes.length : 0;
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView photo;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subTitle)
        TextView subTitle;
        @BindView(R.id.serving)
        TextView serving;
        @BindView(R.id.progress)
        ProgressBar progressBar;
        @BindView(R.id.favorite)
        ImageView favorite;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public boolean favorite(Recipe r) {

        WidgetList w = new WidgetList();
        w.setId(r.getId());
        w.setImage(r.getImage());
        w.setName(r.getName());

        if (db.insert(w) > 0) {
            Toast.makeText(context, r.getName() + " in your widget", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Not add in your widget", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean checkFavorite(int idRecipe) {

        if (db.listById(idRecipe).size() > 0)
            return true;
        else
            return false;
    }

    private boolean remove(Recipe r) {

        WidgetList w = new WidgetList();
        w.setId(r.getId());
        w.setImage(r.getImage());
        w.setName(r.getName());

        if (db.delete(w) > 0) {
            Toast.makeText(context, r.getName() + "Removed!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Opss fail, try latter...", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
