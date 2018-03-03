package receita.com.br.recipe.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.domain.Step;


/**
 * Created by Paulo on 19/02/2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final List<Step> steps;
    private Context context;
    private ClickInMyAdpater mClickInMyAdpater;


    public StepsAdapter(Context context, List<Step> steps) {
        this.steps = steps;
        this.context = context;
    }

    public void setClickInMyAdpater(ClickInMyAdpater gca) {
        mClickInMyAdpater = gca;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view;


        if (context.getResources().getBoolean(R.bool.isTablet))
            view = LayoutInflater.from(context).inflate(R.layout.fragment_menu, parent, false);
        else
            view = LayoutInflater.from(context).inflate(R.layout.steps, parent, false);

        StepViewHolder rVh = new StepViewHolder(view);

        view.setTag(rVh);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickInMyAdpater != null) {
                    StepViewHolder rVh = (StepViewHolder) view.getTag();
                    int position = rVh.getAdapterPosition();
                    mClickInMyAdpater.clickMyAdapter(view, position, steps.get(position));
                }
            }
        });
        return rVh;


    }

    public interface ClickInMyAdpater {
        void clickMyAdapter(View v, int position, Step step);
    }

    @Override
    public void onBindViewHolder(final StepViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.description.setText(step.getShortDescription());


    }


    @Override
    public int getItemCount() {
        return this.steps != null ? this.steps.size() : 0;
    }


    public static class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.description)
        TextView description;


        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
