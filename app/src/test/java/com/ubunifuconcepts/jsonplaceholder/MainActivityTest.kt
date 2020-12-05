package com.ubunifuconcepts.jsonplaceholder

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ubunifuconcepts.jsonplaceholder.model.Post
import com.ubunifuconcepts.jsonplaceholder.repository.PostsRepository
import io.mockk.coEvery
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@Config(sdk= [28], manifest = Config.NONE)
class MainActivityTest {
    private val data = getDummyPostData()

    @Before
    fun setUp() {
        mockkObject(PostsRepository)
        coEvery { PostsRepository.loadPosts() } returns data
    }

    @Test
    @LooperMode(LooperMode.Mode.PAUSED)
    fun testPostDataDisplayed() {
        val scenario = launchActivity<MainActivity>()
        scenario.onActivity { activity ->
            assertEquals(activity.postsRecyclerView.adapter, activity.postAdapter)
            assertEquals(activity.postAdapter.posts, data)
        }
    }

    private fun getDummyPostData(): List<Post> {
        return listOf(
            Post(1, "Title 1", "Body 1", 12),
            Post(2, "Title 2", "Body 2", 12),
            Post(3, "Title 3", "Body 3", 11),
            Post(4, "Title 4", "Body 4", 19)
        )
    }

    @After
    fun tearDown() {
        unmockkObject(PostsRepository)
    }
}