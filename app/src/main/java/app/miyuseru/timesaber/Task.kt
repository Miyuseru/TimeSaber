package app.miyuseru.timesaber

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Task(

    @PrimaryKey

    open var id: String = UUID.randomUUID().toString(),
    open var Title: String = "",
    open var content: String = "",
    open var deadline: String = "",
    open var level: String = "",
    open var createdAt: Date = Date(System.currentTimeMillis())

) : RealmObject()