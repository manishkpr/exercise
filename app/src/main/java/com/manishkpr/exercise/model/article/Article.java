package com.manishkpr.exercise.model.article;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;


@AutoValue
public abstract class Article implements Parcelable {

	@SerializedName("short_description")
	public abstract String shortDescription();

	@SerializedName("last_update")
	public abstract int lastUpdate();

	@SerializedName("id")
	public abstract int id();

	@SerializedName("avatar")
	public abstract String avatar();

	@SerializedName("title")
	public abstract String title();

	public static TypeAdapter<Article> typeAdapter(Gson gson) {
		return new AutoValue_Article.GsonTypeAdapter(gson);
	}
}