package receita.com.br.recipe.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo on 19/02/2018.
 */

public class Step implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videURL;

    @SerializedName("thumbnailURL")
    private String thumbnail;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videURL);
        dest.writeString(this.thumbnail);
    }

    public Step() {
    }

    protected Step(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videURL = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideURL() {
        return videURL;
    }

    public void setVideURL(String videURL) {
        this.videURL = videURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static Creator<Step> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videURL='" + videURL + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (id != null ? !id.equals(step.id) : step.id != null) return false;
        if (shortDescription != null ? !shortDescription.equals(step.shortDescription) : step.shortDescription != null)
            return false;
        if (description != null ? !description.equals(step.description) : step.description != null)
            return false;
        if (videURL != null ? !videURL.equals(step.videURL) : step.videURL != null) return false;
        return thumbnail != null ? thumbnail.equals(step.thumbnail) : step.thumbnail == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (videURL != null ? videURL.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        return result;
    }
}
