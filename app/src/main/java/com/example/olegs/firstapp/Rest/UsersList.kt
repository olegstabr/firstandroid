package com.example.olegs.firstapp.Rest

/**
 * Created by superadmin on 27.07.17.
 */

class NotesList {
    var userList: List<Note> = emptyList()

    constructor() {}

    constructor(userList: List<Note>) {
        this.userList = userList
    }
}