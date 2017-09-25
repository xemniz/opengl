package ru.xmn.opengl
import android.opengl.GLES20.*
import android.util.Log

/**
 * Created by xmn on 24.09.2017.
 */
object ShaderHelper{
    fun compileVertexShader(shaderCode: String): Int{
        return compileShader(GL_VERTEX_SHADER, shaderCode)
    }

    fun compileFragmentShader(shaderCode: String): Int {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode)
    }

    private fun compileShader(type: Int, shaderCode: String): Int {
        val shaderObjectId = glCreateShader(type)
        if (shaderObjectId == 0) {
            if (LoggerConfig.ON){
                Log.w("ShaderHelper", "Could not create shader")
            }
            return 0
        }

        glShaderSource(type, shaderCode)
        glCompileShader(type)
        var compileStatus = IntArray(1)
        return 0
    }
}