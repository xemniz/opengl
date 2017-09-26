package ru.xmn.opengl

import android.content.Context
import android.content.res.Resources
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by xmn on 24.09.2017.
 */
class TextResourceReader {
    companion object {
        fun readTextFileFromResource(context: Context, resourceId: Int): String {
            val body = StringBuilder()
            try {
                val inputStream = context.resources.openRawResource(resourceId)

                val inputAsString = inputStream.bufferedReader().use { it.readText() }
                body.append(inputAsString)
            } catch (e: IOException) {
                throw RuntimeException("Could not open resource")
            } catch (nfe: Resources.NotFoundException) {
                throw RuntimeException("Resource not found: $resourceId")
            }
            return body.toString()
        }
    }
}