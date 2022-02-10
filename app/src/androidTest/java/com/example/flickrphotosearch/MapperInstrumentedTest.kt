package com.example.flickrphotosearch

import android.text.SpannableString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flickrphotosearch.api.model.ContentDetailsResponseApiModel
import com.example.flickrphotosearch.api.model.OwnerDetailsResponseApiModel
import com.example.flickrphotosearch.api.model.PhotoDetailsResponseApiModel
import com.example.flickrphotosearch.api.model.TagDetailsResponseApiModel
import com.example.flickrphotosearch.api.model.TagItemDetailsResponseApiModel
import com.example.flickrphotosearch.common.mapper.toUiModel
import com.example.flickrphotosearch.main.model.PhotoDetailsUiModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapperInstrumentedTest {

    @Test
    fun photoDetailsResponseApiModelMapper_Correct() {
        val apiModel = PhotoDetailsResponseApiModel(
            id = "12345",
            secret = "qwerty",
            server = "6789",
            dateUploadedInSeconds = "1635777338",
            owner = OwnerDetailsResponseApiModel("username"),
            title = ContentDetailsResponseApiModel("title"),
            description = ContentDetailsResponseApiModel("description"),
            views = "89600",
            tags = TagDetailsResponseApiModel(
                listOf(
                    TagItemDetailsResponseApiModel("tag1"),
                    TagItemDetailsResponseApiModel("tag2")
                )
            )
        )

        assertEquals(
            PhotoDetailsUiModel(
                photoUrl = "https://live.staticflickr.com/6789/12345_qwerty.jpg",
                title = "title",
                date = "2021. 11. 01. 15:35:38",
                viewers = "89600",
                username = "username",
                tags = listOf("tag1", "tag2"),
                description = SpannableString.valueOf("description")
            ),
            apiModel.toUiModel()
        )
    }
}
