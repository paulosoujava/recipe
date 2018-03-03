package receita.com.br.recipe.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import receita.com.br.recipe.R;
import receita.com.br.recipe.domain.Recipe;
import receita.com.br.recipe.domain.Step;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.shortDescription)
    TextView shortDescription;
    @BindView(R.id.description)
    TextView description;
    @BindView((R.id.video_view))
    SimpleExoPlayerView playerView;


    private Step step;
    boolean prontoPlay;
    int janelaAtual = 0;
    long posicaoPlayback= 0;
    SimpleExoPlayer player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {


            step = bundle.getParcelable(Recipe.PARCELABLE_KEY);
            description.setText(step.getDescription());
            shortDescription.setText(step.getShortDescription());

            if (step.getVideURL() != null && !step.getVideURL().isEmpty()) {
                initializePlayer();
            } else {
                showMessage();
                finish();
            }
        } else {
            showMessage();
            finish();
        }


    }


    private void initializePlayer() {

        player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        player.setPlayWhenReady(prontoPlay);
        player.seekTo(janelaAtual, posicaoPlayback);

        MediaSource mediaSource = myMediaSource(Uri.parse(step.getVideURL()));

        player.prepare(mediaSource);

    }



    private MediaSource myMediaSource(Uri uri) {
        return new ExtractorMediaSource
                .Factory(new DefaultHttpDataSourceFactory("receita"))
                .createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            posicaoPlayback = player.getCurrentPosition();
            janelaAtual= player.getCurrentWindowIndex();
            prontoPlay= player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releasePlayer();
    }

    private void showMessage() {
        Toast.makeText(this, R.string.no_has_data_sorry, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        finish();
    }

}
