package com.manishkpr.exercise.model.article;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;


@AutoValue
public abstract class ArticleDetail {

	@SerializedName("id")
	public abstract String id();

	@SerializedName("text")
	public abstract String text();

	public static TypeAdapter<ArticleDetail> typeAdapter(Gson gson) {
		return new AutoValue_ArticleDetail.GsonTypeAdapter(gson);
	}
}