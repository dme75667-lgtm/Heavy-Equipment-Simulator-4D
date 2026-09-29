package com.mehdi.heavyequipment;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameEngine extends GLSurfaceView {

    private final EngineRenderer renderer;

    public GameEngine(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        renderer = new EngineRenderer();
        setRenderer(renderer);

        setRenderMode(RENDERMODE_CONTINUOUSLY);

        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    public void pauseGame() {
        onPause();
    }

    public void resumeGame() {
        onResume();
    }

    private static class EngineRenderer implements Renderer {

        private long lastTime;

        @Override
        public void onSurfaceCreated(
                GL10 gl,
                EGLConfig config) {

            lastTime = System.nanoTime();

            gl.glClearColor(
                    0.05f,
                    0.08f,
                    0.12f,
                    1.0f
            );

            gl.glEnable(GL10.GL_DEPTH_TEST);
        }

        @Override
        public void onSurfaceChanged(
                GL10 gl,
                int width,
                int height) {

            gl.glViewport(
                    0,
                    0,
                    width,
                    height
            );
        }

        @Override
        public void onDrawFrame(GL10 gl) {

            long now = System.nanoTime();

            float deltaTime =
                    (now - lastTime)
                            / 1_000_000_000.0f;

            lastTime = now;

            if (deltaTime > 0.1f) {
                deltaTime = 0.1f;
            }

            update(deltaTime);

            gl.glClear(
                    GL10.GL_COLOR_BUFFER_BIT |
                    GL10.GL_DEPTH_BUFFER_BIT
            );

            render(deltaTime);
        }

        private void update(float deltaTime) {
            // نظام اللعبة
        }

        private void render(float deltaTime) {
            // نظام الرسومات ثلاثية الأبعاد
        }
    }
}
