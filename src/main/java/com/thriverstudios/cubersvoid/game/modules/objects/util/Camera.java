package com.thriverstudios.cubersvoid.game.modules.objects.util;

import org.joml.*;
import org.joml.Math;

enum CameraMovement {
    CAM_FORWARD,
    CAM_BACKWARD,
    CAM_LEFT,
    CAM_RIGHT
}

public class Camera {
    Vector3f position;
    Vector3f front;
    Vector3f up;
    Vector3f right;
    Vector3f worldUp;

    float yaw;
    float pitch;

    float movementSpeed;
    float mouseSensitivity;
    float zoom;

    public Camera() {
        this.front = new Vector3f(0.0f, 0.0f, -1.0f);
        this.movementSpeed = 2.2f;
        this.mouseSensitivity = 0.1f;
        this.zoom = 45.0f;

        this.position = new Vector3f(0.0f, 0.0f, 0.0f);
        this.worldUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.yaw = -90.0f;
        this.pitch = 0.0f;
        UpdateCameraVectors();
    }

    public Camera(Vector3f position, Vector3f up, float yaw, float pitch) {
        this.front = new Vector3f(0.0f, 0.0f, -1.0f);
        this.movementSpeed = 2.2f;
        this.mouseSensitivity = 0.1f;
        this.zoom = 45.0f;

        this.position = position;
        this.worldUp = up;
        this.yaw = yaw;
        this.pitch = pitch;
        UpdateCameraVectors();
    }

    public Matrix4f GetViewMatrix() {
        return new Matrix4f().lookAt(position, position.add(front), up);
    }

    public void ProcessKeyboard(CameraMovement direction, float deltaTime) {
        float velocity = movementSpeed * deltaTime;
        switch (direction) {
            case CAM_FORWARD:
                position.add(front.mul(velocity));
                break;
            case CAM_BACKWARD:
                position.sub(front.mul(velocity));
                break;
            case CAM_LEFT:
                position.sub(right.mul(velocity));
                break;
            case CAM_RIGHT:
                position.add(right.mul(velocity));
                break;
        }
    }

    public void ProcessMouseMovement(float xoffset, float yoffset, boolean constrainPitch) {
        xoffset *= mouseSensitivity;
        yoffset *= mouseSensitivity;

        yaw += xoffset;
        pitch += yoffset;

        if (constrainPitch) {
            if (pitch > 89.0f) {
                pitch = 89.0f;
            }
            if (pitch < -89.0f) {
                pitch = -89.0f;
            }
        }

        UpdateCameraVectors();
    }

    void ProcessMouseScroll(float yoffset) {
        zoom -= yoffset;
        if (zoom <= 1.0f) {
            zoom = 1.0f;
        }
        if (zoom >= 45.0f) {
            zoom = 45.0f;
        }
    }

    private void UpdateCameraVectors() {
        Vector3f Front = new Vector3f();
        Front.x = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        Front.y = Math.sin(Math.toRadians(pitch));
        Front.z = Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        front = Front.normalize();
        right = new Vector3f(front.cross(worldUp)).normalize();
        up = new Vector3f(right.cross(front)).normalize();
    }
}
