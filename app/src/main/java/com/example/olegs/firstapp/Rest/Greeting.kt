package com.example.olegs.firstapp.Rest

/**
 * Created by superadmin on 02.07.17.
 */

public class Greeting {
    var id = 0
    var content = String()

    constructor()

    constructor(id: Int, content: String) {
        this.id = id
        this.content = content
    }

}