package com.example.webapi.Retrofit

import com.google.gson.annotations.SerializedName

data class TablePOJO(
    @SerializedName("Nomer")
    val number:String,
    @SerializedName("Date")
    val date:String,
    @SerializedName("Comment")
    val comment:String)
/*@Root(name = "ValueTable", strict = false)
@NamespaceList(
    Namespace(reference = "http://v8.1c.ru/8.1/data/core"),
    Namespace(prefix = "xs", reference = "http://www.w3.org/2001/XMLSchema"),
    Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance")
)
data class TablePOJO( @ElementList(name="row") var rows:List<Row>)
@Root(name="row")
data class Row constructor(
@ElementList(name="Value") var value:List<Value>
)

@Root(name = "Value")
data class Value constructor(
    @Attribute(name="type") var type:String,
    @Text var value:String
)*/