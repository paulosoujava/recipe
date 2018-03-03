package receita.com.br.recipe.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo on 19/02/2018.
 */

public class Ingredients implements Parcelable {

    @SerializedName("quantity")
    private  double quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredients that = (Ingredients) o;

        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;
        return ingredient != null ? ingredient.equals(that.ingredient) : that.ingredient == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(quantity);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }



    public Ingredients() {
    }

    protected Ingredients(Parcel in) {
        this.quantity = in.readDouble();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel source) {
            return new Ingredients(source);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
