package com.example.olegs.firstapp.Rest

/**
 * Created by superadmin on 02.07.17.
 */
class User {
    var id: Int = 0
    var login: String = String()
    var password: String = String()

    constructor()

    constructor(login: String, password: String) {
        this.login = login
        this.password = password
    }

    constructor(id: Int, login: String, password: String) {
        this.id = id
        this.login = login
        this.password = password
    }
}