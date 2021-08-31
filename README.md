# Using Pagination in Jetpack Compose with Room, Hilt, MVVM

This is an example to demonstrate how we can use pagination with `Jetpack Compose` when using `Room` Database with `Hilt` and `MVVM` Architecture.

Major Components Highlight
- **Jetpack Compose**
- **Android Paging for compose**
- **Hilt**
- **MVVM**

# Screenshot/Gif

<img alt="Compose Paging Demo" height="600px" src="https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/demo.gif" />  <img alt="Compose Paging Demo" height="600px" src="https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/demo_ss1.jpg" />  <img alt="Compose Paging Demo" height="600px" src="https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/demo_ss2.jpg" />


# Compose Paging

- **PagingSource** ( [NotesDao.kt](https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/app/src/main/java/com/nishantpardamwar/composepagingwithroom/db/daos/NoteDao.kt) )

    ```
    @Query("SELECT * FROM NoteEntity")
    fun getAllNotesPaginated(): PagingSource<Int, NoteEntity>
    ```
    
- **Pager** ( [MainActivityViewModel.kt](https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/app/src/main/java/com/nishantpardamwar/composepagingwithroom/viewmodels/MainActivityViewModel.kt) )
  
   ```
   val notePager = Pager(config = PagingConfig(10)) {
        repo.getNotePagingSource()
   }
   ```
   
- **Lazy Paging Items** ( [NoteListScreen.kt](https://raw.githubusercontent.com/nishantpardamwar/composepaging/master/app/src/main/java/com/nishantpardamwar/composepagingwithroom/ui/NoteListScreen.kt) )

    ```
    val pager = remember { notePager }
    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    ```
