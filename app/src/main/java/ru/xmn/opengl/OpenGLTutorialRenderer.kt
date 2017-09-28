package ru.xmn.opengl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLTutorialRenderer(val context: Context) : GLSurfaceView.Renderer {
    companion object {
        const val POSITION_COMPONENT_COUNT = 2
        const val BYTES_PER_FLOAT = 4
        const val U_COLOR = "u_Color"
        const val A_POSITION = "a_Position"

        const val vertexShaderSource =
                "attribute vec4 a_Position;\n" +
                        "void main()\n" +
                        "{\n" +
                        "gl_Position = a_Position;\n" +
                        "}"

        const val fragmentShaderSource =
                "precision mediump float;\n" +
                        "uniform vec4 u_Color;\n" +
                        "void main()\n" +
                        "{\n" +
                        "gl_FragColor = u_Color;\n" +
                        "}"
    }

    private val vertexData: FloatBuffer
    private var program: Int = 0
    private var uColorLocation: Int = 0
    private var aPositionLocation: Int = 0

    init {
        val squareVerts: FloatArray = floatArrayOf(
                -0.5f, -0.5f,
                0.5f, -0.5f,
                -0.5f, 0.5f,
                -0.5f, 0.5f,
                0.5f, -0.5f,
                0.5f, 0.5f
        )

        vertexData = ByteBuffer.allocateDirect(squareVerts.size * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()

        vertexData.put(squareVerts)
    }

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(0.0f, 0.0f, 1.0f, 0.0f)

        val vertexShader = ShaderHelper.compileShader(GL_VERTEX_SHADER, vertexShaderSource)
        val fragmentShader = ShaderHelper.compileShader(GL_FRAGMENT_SHADER, fragmentShaderSource)

        program = linkProgram(vertexShader, fragmentShader)

        if (LoggerConfig.ON)
            validateProgram(program)

        glUseProgram(program)

        uColorLocation = glGetUniformLocation(program, AirHockeyRenderer.U_COLOR)
        aPositionLocation = glGetAttribLocation(program, A_POSITION)

        vertexData.position(0)
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0, vertexData)
        glEnableVertexAttribArray(aPositionLocation)
    }

    override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {
        glViewport(0, 0, p1, p2)
    }

    override fun onDrawFrame(p0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)

        glUniform4f(uColorLocation, 1f, 0f, 1f, 1f)
        glDrawArrays(GL_TRIANGLES, 0, 6)
    }

}