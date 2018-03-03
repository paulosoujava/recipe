package receita.com.br.recipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.domain.Ingredients;


/**
 * Created by Paulo on 19/02/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.StepViewHolder> {

    private final List<Ingredients> ingredients;
    private Context context;


    public IngredientsAdapter(Context context, List<Ingredients> ingredients) {
        this.ingredients = ingredients;
        this.context = context;
    }


    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.ingredients, parent, false);
        StepViewHolder rVh = new StepViewHolder(view);
        view.setTag(rVh);
        return rVh;


    }

    public interface ClickInMyAdpater {
        void clickMyAdapter(View v, int position, Ingredients ingredients);
    }

    @Override
    public void onBindViewHolder(final StepViewHolder holder, int position) {
        Ingredients ingredients = this.ingredients.get(position);
        Log.d("LOG", "ingredients " + ingredients.getIngredient());
        holder.measure.setText("measure:\n " + ingredients.getMeasure());
        holder.quantity.setText("Quantity: " + ingredients.getQuantity());
        holder.ingredient.setText("Ingedient:\n " + ingredients.getIngredient());


    }


    @Override
    public int getItemCount() {
        return this.ingredients != null ? this.ingredients.size() : 0;
    }


    public static class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient)
        TextView ingredient;
        @BindView(R.id.measure)
        TextView measure;
        @BindView(R.id.quantity)
        TextView quantity;


        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
