package com.example.olegs.firstapp.Rest

/**
 * Created by superadmin on 02.07.17.
 */
class User{
    var id: Int = 0
    var name: String = String()
    var secondName: String = String()

    constructor()

    constructor(name: String, secondName: String) {
        this.name = name
        this.secondName = secondName
    }

    constructor(id: Int, name: String, secondName: String) {
        this.id = id
        this.name = name
        this.secondName = secondName
    }
}