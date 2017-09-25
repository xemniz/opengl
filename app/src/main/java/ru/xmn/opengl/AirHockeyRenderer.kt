package ru.xmn.opengl

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class AirHockeyRenderer(val context: Context) : GLSurfaceView.Renderer {
    companion object {
        const val POSITION_COMPONENT_COUNT = 2
        const val BYTES_PER_FLOAT = 4
    }

    val vertexData: FloatBuffer

    init {
        val tableVertices: FloatArray = floatArrayOf(
                //triangle 1
                0f, 0f,
                9f, 14f,
                0f, 14f,

                //triangle 2
                0f, 0f,
                9f, 0f,
                9f, 14f,

                //line 1
                0f, 7f,
                9f, 7f,

                //Mallets
                4.5f, 2f,
                4.5f, 12f
        )

        vertexData = ByteBuffer
                .allocateDirect(tableVertices.size * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()

        vertexData.put(tableVertices)
    }

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f)
        val vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader)
        val fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader)
    }

    override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {
        glViewport(0, 0, p1, p2)
    }

    override fun onDrawFrame(p0: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
    }

}