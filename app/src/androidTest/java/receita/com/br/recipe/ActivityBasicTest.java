package receita.com.br.recipe;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.AllOf;
import org.hamcrest.object.HasToString;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import receita.com.br.recipe.activity.SplashScreenActivity;
import receita.com.br.recipe.adapter.RecipeAdapter;

import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by Paulo on 28/02/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityBasicTest {



    @Rule public ActivityTestRule<SplashScreenActivity> mVideoActivityActivityTestRule
            = new ActivityTestRule<SplashScreenActivity>(SplashScreenActivity.class);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState(){

        Espresso.onView(withId(R.id.splash)).check(ViewAssertions.matches(isDisplayed()));

    }
    //toast widget
    @Test
    public void ensureListViewIsPresent() throws Exception {
        Espresso.onData(HasToString.hasToString(CoreMatchers.containsString("Nutella Pie"))).perform(ViewActions.click());
        Espresso.onView(withText(CoreMatchers.startsWith("Recipe:"))).
                inRoot(RootMatchers.withDecorView(
                        CoreMatchers.not(CoreMatchers.is(mVideoActivityActivityTestRule.getActivity().
                                getWindow().getDecorView())))).
                check(ViewAssertions.matches(isDisplayed()));
    }


}
