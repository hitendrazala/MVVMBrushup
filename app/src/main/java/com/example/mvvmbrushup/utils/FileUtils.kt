package com.example.mvvmbrushup.utils

import android.content.Context
import java.io.IOException

object FileUtils {
    /**
     * Reads a JSON file from the assets folder and returns its content as a String.
     *
     * @param context The context to access the assets.
     * @param fileName The name of the JSON file in the assets folder.
     * @return The content of the JSON file as a String.
     */
    fun loadJsonFromAssets(context: Context, fileName: String): String {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}