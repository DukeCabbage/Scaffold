package com.cabbage.scaffold.ui.gallery


private const val fullUrl = "https://firebasestorage.googleapis.com/" +
                            "v0/b/project-nephele.appspot.com/o/terran_vic_full.jpg" +
                            "?alt=media&token=07c59def-f08a-4596-95a6-d03615d5c0c2"

private const val smallUrl = "https://firebasestorage.googleapis.com/" +
                             "v0/b/project-nephele.appspot.com/o/terran_vic_small.jpg" +
                             "?alt=media&token=51bdd2d6-48ef-4f96-926d-9dfb984cc47e"

private const val thumbUrl = "https://firebasestorage.googleapis.com/" +
                             "v0/b/project-nephele.appspot.com/o/terran_vic_thumb.jpg" +
                             "?alt=media&token=813ac709-1b61-4a48-96c2-1feaa156a27c"

private const val gifThumbUrl = "https://firebasestorage.googleapis.com/" +
                                "v0/b/project-nephele.appspot.com/o/kitty_thumb.jpg" +
                                "?alt=media&token=61293990-fa22-43a6-a185-d069930b29fe"

private const val gifFullUrl = "https://firebasestorage.googleapis.com/" +
                               "v0/b/project-nephele.appspot.com/o/kitty_full.gif" +
                               "?alt=media&token=65403e9f-9d7f-4f87-927b-0f5f85aef781"

data class Post(
        val media: Media? = null,
        val thumb: Media? = null,
        val resolutions: Resolution? = null) {

    companion object {
        fun complete(): Post {

            val full = Media(fullUrl, 1280, 720)
            val thumb = Media(thumbUrl, 320, 180)

            val res = Resolution(
                    low = thumb.copy(),
                    medium = Media(smallUrl, 640, 360),
                    high = full.copy()
            )

            return Post(media = full, thumb = thumb, resolutions = res)
        }

        fun unprocessed(): Post {
            val full = Media(fullUrl)

            return Post(media = full, thumb = Media(), resolutions = Resolution())
        }

        fun kittyAnim(): Post {
            val full = Media(gifFullUrl, 1356, 1017)
            val thumb = Media(gifThumbUrl, 1060, 795)

            return Post(media = full, thumb = thumb)
        }
    }
}

data class Media(
        val url: String? = null,
        val width: Int = 0,
        val height: Int = 0)

data class Resolution(
        val low: Media? = Media(),
        val medium: Media? = Media(),
        val high: Media? = Media())

