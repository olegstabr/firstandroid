package com.example.olegs.firstapp.Rest
import java.sql.Date

/**
 * Created by superadmin on 26.07.17.
 */

/**
 * Created by Disbo on 02.07.2017.
 */


class Note {
    var id: Int = 0
    var userId: Int = 0
    var title: String = String()
    var text: ByteArray
    var createDate: Date

    constructor(userId: Int,  title: String, text: ByteArray, createDate: Date) {
        this.userId = userId
        this.title = title
        this.text = text
        this.createDate = createDate
    }

    constructor(id: Int, userId: Int,  title: String, text: ByteArray, createDate: Date) {
        this.id = id
        this.userId = userId
        this.title = title
        this.text = text
        this.createDate = createDate
    }
}
