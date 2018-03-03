package receita.com.br.recipe.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.domain.Step;


/**
 * Created by Paulo on 19/02/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.StepViewHolder> {

    private final Step step;
    private Context context;


    public VideoAdapter(Context context, Step step) {
        this.step = step;
        this.context = context;
    }


    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(context).inflate(R.layout.fragment_video, parent, false);
        StepViewHolder rVh = new StepViewHolder(view);
        view.setTag(rVh);
        return rVh;
    }

    @Override
    public void onBindViewHolder(final StepViewHolder holder, int position) {
        holder.description.setText(step.getDescription());
        holder.shortDescription.setText(step.getShortDescription());
        if( step.getVideURL().isEmpty() ){
            holder.no_video.setVisibility(View.VISIBLE);
            holder.videoView.setVisibility(View.INVISIBLE);
        }else
            holder.videoView.setVideoURI(Uri.parse(step.getVideURL()));


    }


    @Override
    public int getItemCount() {
        return 1;
    }


    public static class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.shortDescription)
        TextView shortDescription;
        @BindView((R.id.video_view))
        VideoView videoView;
        @BindView((R.id.no_video))
        ImageView no_video;


        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
