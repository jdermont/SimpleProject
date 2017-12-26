package pl.derjack.simpleproject

import pl.derjack.simpleproject.ws.model.*

fun createShowList(count: Int = 1): List<WsTVShow> {
    val tvShows = (0..count-1).map {
        WsTVShow(0.0, createShow(id = it.toLong(), name = "Show $it"))
    }
    return tvShows
}

fun createShow(id: Long = 0, name: String = "Show"): WsShow {
    return WsShow(id = id,
            url = "http://www.tvmaze.com/shows/66/the-big-bang-theory",
            name = name,
            type = "Scripted",
            language = "English",
            genres = listOf("Comedy"),
            status = "Running",
            runtime = 30,
            premiered = "2007-09-24",
            schedule = WsSchedule("20:00", listOf("Thursday")),
            rating = WsRating(8.3),
            weight = 100,
            network = WsNetwork(
                    id = 2,
                    name = "CBS",
                    country = WsCountry("United States", "US", "America/New_York")
            ),
            externals = WsExternals(8511, 80379, "tt0898266"),
            image = WsImage(
                    medium = "http://static.tvmaze.com/uploads/images/medium_portrait/58/145601.jpg",
                    original = "http://static.tvmaze.com/uploads/images/original_untouched/58/145601.jpg"
            ),
            summary = "Something about The Big Bang Theory",
            updated = 1494671152L,
            embedded = null
    )
}