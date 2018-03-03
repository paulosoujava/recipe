package receita.com.br.recipe.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paulo on 19/02/2018.
 */

public class Recipe implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("servings")
    private Integer servings;

    @SerializedName("image")
    private String image;

    @SerializedName("ingredients")
    private List<Ingredients> ingredients;

    @SerializedName("steps")
    private List<Step> steps;

    public Recipe() {
    }

    public Recipe(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static  final String PARCELABLE_KEY = "recipe";



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                ", ingrediets=" + ingredients +
                ", steps=" + steps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (servings != null ? !servings.equals(recipe.servings) : recipe.servings != null)
            return false;
        if (image != null ? !image.equals(recipe.image) : recipe.image != null) return false;
        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null)
            return false;
        return steps != null ? steps.equals(recipe.steps) : recipe.steps == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (servings != null ? servings.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.servings);
        dest.writeString(this.image);
        dest.writeTypedList(this.ingredients);
        dest.writeTypedList(this.steps);
    }

    protected Recipe(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.servings = (Integer) in.readValue(Integer.class.getClassLoader());
        this.image = in.readString();
        this.ingredients = in.createTypedArrayList(Ingredients.CREATOR);
        this.steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
