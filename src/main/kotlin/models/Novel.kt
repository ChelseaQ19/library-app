package ie.setu.models

data class Novel (val novelTitle:String,
                  val novelAuthor: String,
                  val novelGenre: String,
                  val novelPages: Int,
                  val novelPrice: Double,
                  val novelLanguage: String,
                  val isNovelArchived :Boolean){
}