//
// Created by wei tao on 2022/1/21.
//
#include "egl_engine.h"
#include "draw_triangle.h"
#include "my_log.h"

//GLuint loadShader(GLenum type, const char* shaderSource) {
//
//}
GLint g_program;
int g_width;
int g_height;

const char *triangleVertexShader =
        "#version 300 es                          \n"
        "layout(location = 0) in vec4 vPosition;  \n"
        "void main()                              \n"
        "{                                        \n"
        "   gl_Position = vPosition;              \n"
        "}                                        \n";

const char *fragmentShader =
        "#version 300 es                              \n"
        "precision mediump float;                     \n"
        "out vec4 fragColor;                          \n"
        "void main()                                  \n"
        "{                                            \n"
        "   fragColor = vec4 ( 1.0, 0.0, 0.0, 1.0 );  \n"
        "}                                            \n";

GLfloat vVertices[] = {
        0.0f, 0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f
};


void glesInit() {
    GLint vsh = loadShader(triangleVertexShader, GL_VERTEX_SHADER);
    GLint fsh = loadShader(fragmentShader, GL_FRAGMENT_SHADER);
    GLint program = glCreateProgram();

    if (program == 0) {
        LogE("%s glCreateProgram failed ", __FILE_NAME__);
        return;
    }

    glAttachShader(program, vsh);
    glAttachShader(program, fsh);
    glLinkProgram(program);
    GLint status = 0;
    glGetProgramiv(program, GL_LINK_STATUS, &status);
    if (!status) {//// 即 status == 0, c++中 false 相当于 0
        LogE("%s link program failed ", __FILE_NAME__);
        return;
    }
    g_program = program;
    glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
}

void glesResize(int width, int height) {
    LogD("%s glesResize width = %d, height = %d", __FILE_NAME__, width, height);
    g_height = height;
    g_width = width;
}

void glesRender() {
    ////设置三角形绘制区域大小
    glViewport(0,0,g_width,g_height);
    //// Clear the color buffer
    glClear(GL_COLOR_BUFFER_BIT);
    glUseProgram(g_program);
    //// Load the vertex data
    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,vVertices);
    glEnableVertexAttribArray(0);
    glDrawArrays(GL_TRIANGLES,0,3);
}