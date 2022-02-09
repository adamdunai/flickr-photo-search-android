package com.example.flickrphotosearch.common.mapper

import android.os.Build
import android.text.Html
import com.example.flickrphotosearch.BuildConfig
import com.example.flickrphotosearch.api.model.PhotoDetailsResponseApiModel
import com.example.flickrphotosearch.main.model.PhotoDetailsUiModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val DATE_TIME_PATTERN = "yyyy. MM. dd. HH:mm:ss"

fun PhotoDetailsResponseApiModel.toUiModel(): PhotoDetailsUiModel =
    PhotoDetailsUiModel(
        photoUrl = "${BuildConfig.BASE_PHOTO_URL}$server/${id}_$secret.jpg",
        title = title.content,
        date = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(dateUploadedInSeconds.toLong()),
            ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)),
        viewers = views,
        username = owner.username,
        tags = tags.tag.map { it.tag },
        description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(description.content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(description.content)
        }
    )
