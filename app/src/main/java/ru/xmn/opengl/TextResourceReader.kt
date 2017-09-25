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
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var nextLine: String?
                while (bufferedReader.readLine() != null) {
                    nextLine = bufferedReader.readLine()
                    body.append(nextLine)
                    body.append("\n")
                }
            } catch (e: IOException) {
                throw RuntimeException("Could not open resource")
            } catch (nfe: Resources.NotFoundException) {
                throw RuntimeException("Resource not found: $resourceId")
            }
            return body.toString()
        }
    }
}