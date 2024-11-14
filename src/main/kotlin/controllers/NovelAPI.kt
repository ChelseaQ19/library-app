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

    fun numberOfNovels(): Int {
        return novels.size
    }

    fun findNovel(index: Int): Novel? {
        return if (isValidListIndex(index, novels)) {
            novels[index]
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}