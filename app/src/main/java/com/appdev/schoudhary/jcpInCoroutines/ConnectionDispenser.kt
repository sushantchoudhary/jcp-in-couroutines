package com.appdev.schoudhary.jcpInCoroutines

import java.sql.Connection
import java.sql.DriverManager

class ConnectionDispenser {
    private val connectionHolder = object: ThreadLocal<Connection>() {
        override fun initialValue(): Connection? {
            return DriverManager.getConnection(DB_URL)
        }
    }

    fun getConnection()= connectionHolder.get()

   companion object {
       const val DB_URL = "jdbc:mysql://localhost/mydatabase"
   }
}