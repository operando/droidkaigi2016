package io.github.droidkaigi.confsched.model;

import com.google.gson.annotations.SerializedName;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

import org.parceler.Parcel;

import android.support.annotation.Nullable;

import java.util.Date;

import io.github.droidkaigi.confsched.R;

@Parcel
@Table
public class Session {

    @Column(indexed = true)
    @SerializedName("id")
    public int id;

    @Column(indexed = true)
    @SerializedName("title")
    public String title;

    @Column
    @SerializedName("description")
    public String description;

    @Column
    public int speakerId;

    @SerializedName("speaker")
    public Speaker speaker;

    @Column
    @SerializedName("stime")
    public Date stime;

    @Column
    @SerializedName("etime")
    public Date etime;

    @Column(indexed = true)
    public int categoryId;

    @Nullable
    @SerializedName("category")
    public Category category;

    @Column(indexed = true)
    public int placeId;

    @SerializedName("place")
    public Place place;

    @Column
    @SerializedName("language_id")
    public String languageId;

    @Column
    @Nullable
    @SerializedName("slide_url")
    public String slideUrl;

    @Column(indexed = true)
    public boolean checked;

    public Session() {
    }

    public void prepareSave() {
        speakerId = speaker.id;
        if (category != null) categoryId = category.id;
        placeId = place.id;
    }

    public Session initAssociations(OrmaDatabase orma) {
        if (category == null) category = orma.selectFromCategory().idEq(categoryId).value();
        if (place == null) place = orma.selectFromPlace().idEq(placeId).value();
        if (speaker == null) speaker = orma.selectFromSpeaker().idEq(speakerId).value();

        return this;
    }

    public int getLanguageResId() {
        switch (languageId) {
            case "en":
                return R.string.lang_en;
            case "ja":
                return R.string.lang_ja;
            default:
                return R.string.lang_en;
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Session && ((Session) o).id == id || super.equals(o);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
