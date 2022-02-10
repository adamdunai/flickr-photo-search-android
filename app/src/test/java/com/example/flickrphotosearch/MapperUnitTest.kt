package com.example.flickrphotosearch

import com.example.flickrphotosearch.api.model.PhotoItemApiModel
import com.example.flickrphotosearch.common.mapper.toDataModel
import com.example.flickrphotosearch.common.mapper.toUiModel
import com.example.flickrphotosearch.database.model.PhotoDataModel
import com.example.flickrphotosearch.main.model.SearchItemUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperUnitTest {

    @Test
    fun photoDataModelMapper_Correct() {
        val dataModel = PhotoDataModel(
            id = 0,
            photoId = "12345",
            secret = "qwerty",
            server = "6789"
        )
        assertEquals(
            SearchItemUiModel(
                photoId = "12345",
                photoUrl = "https://live.staticflickr.com/6789/12345_qwerty.jpg"
            ),
            dataModel.toUiModel()
        )
    }

    @Test
    fun photoItemApiModel_Correct() {
        val apiModel = PhotoItemApiModel(
            photoId = "12345",
            secret = "qwerty",
            server = "6789"
        )
        assertEquals(
            PhotoDataModel(
                id = 0,
                photoId = "12345",
                secret = "qwerty",
                server = "6789"
            ),
            apiModel.toDataModel()
        )
    }
}
