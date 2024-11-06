package ie.setu.controllers

import ie.setu.models.Novel

class NovelAPI {

    private var novels = ArrayList<Novel>()

    fun add(novel: Novel): Boolean { //adding a novel to our application
        return novels.add(novel)
    }

    fun listAllNovels(): String { //lists all novel that are stored in the novels list.
        return if (novels.isEmpty()) {
            "No novels stored"
        } else {
            var listOfNovels = ""
            for (i in novels.indices) {
                listOfNovels += "${i}: ${novels[i]} \n"
            }
            listOfNovels
        }
    }
}