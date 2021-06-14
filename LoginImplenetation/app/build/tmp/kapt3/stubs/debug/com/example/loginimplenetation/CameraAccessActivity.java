package com.example.loginimplenetation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\u0006\u0010\u0018\u001a\u00020\u0017J\"\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0015R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/loginimplenetation/CameraAccessActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "MY_CAMERA_REQUEST_CODE", "", "getMY_CAMERA_REQUEST_CODE", "()I", "SELECT_PICTURE", "getSELECT_PICTURE", "TAKE_PICTURE", "getTAKE_PICTURE", "binding", "Lcom/example/loginimplenetation/databinding/ActivityCameraBinding;", "cameraRequestId", "currentPath", "", "getCurrentPath", "()Ljava/lang/String;", "setCurrentPath", "(Ljava/lang/String;)V", "imageView", "Landroid/widget/ImageView;", "dispatchCameraIntent", "", "dispatchGalleryIntent", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class CameraAccessActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable
    private java.lang.String currentPath;
    private final int TAKE_PICTURE = 1;
    private final int SELECT_PICTURE = 2;
    private final int cameraRequestId = 1222;
    private final int MY_CAMERA_REQUEST_CODE = 100;
    private com.example.loginimplenetation.databinding.ActivityCameraBinding binding;
    private android.widget.ImageView imageView;
    
    public CameraAccessActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCurrentPath() {
        return null;
    }
    
    public final void setCurrentPath(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int getTAKE_PICTURE() {
        return 0;
    }
    
    public final int getSELECT_PICTURE() {
        return 0;
    }
    
    public final int getMY_CAMERA_REQUEST_CODE() {
        return 0;
    }
    
    @android.annotation.SuppressLint(value = {"NewApi"})
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    public final void dispatchGalleryIntent() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void dispatchCameraIntent() {
    }
}