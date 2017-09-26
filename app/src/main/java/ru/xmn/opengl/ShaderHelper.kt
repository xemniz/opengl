package ru.xmn.opengl

import android.opengl.GLES20.*
import android.util.Log

private val TAG = "ShaderHelper"

fun compileVertexShader(shaderCode: String): Int {
    return compileShader(GL_VERTEX_SHADER, shaderCode)
}

fun compileFragmentShader(shaderCode: String): Int {
    return compileShader(GL_FRAGMENT_SHADER, shaderCode)
}

private fun compileShader(type: Int, shaderCode: String): Int {
    val shaderObjectId = glCreateShader(type)
    if (shaderObjectId == 0) {
        log(TAG, "Could not create shader")
        return 0
    }

    glShaderSource(type, shaderCode)
    glCompileShader(type)
    val compileStatus = IntArray(1)
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0)
    log(TAG, "Result of compiling source:\n$shaderCode\n${glGetShaderInfoLog(shaderObjectId)}")

    if (compileStatus[0] == 0) {
        glDeleteShader(shaderObjectId)

        log(TAG, "Compilation of shader failed")

        return 0
    }

    return shaderObjectId
}

fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
    val programObjectId = glCreateProgram()

    if (programObjectId == 0) {
        log(TAG, "Could not create program")
        return 0
    }
    glAttachShader(programObjectId, vertexShaderId)
    glAttachShader(programObjectId, fragmentShaderId)

    glLinkProgram(programObjectId)

    val linkStatus = IntArray(1)
    glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)
    log(TAG, "Result of linking program:\n" +
            glGetProgramInfoLog(programObjectId))

    if (linkStatus[0] == 0) {
        glDeleteProgram(programObjectId)

        log(TAG, "Linking of program failed")

        return 0
    }

    return programObjectId
}

fun validateProgram(programObjectId: Int): Boolean{
    glValidateProgram(programObjectId)

    val validateStatus = IntArray(1)
    glGetProgramiv(programObjectId, GL_LINK_STATUS, validateStatus, 0)
    Log.v(TAG, "Result of validating program:\n${validateStatus[0]}\n${glGetProgramInfoLog(programObjectId)}")

    return validateStatus[0] != 0
}