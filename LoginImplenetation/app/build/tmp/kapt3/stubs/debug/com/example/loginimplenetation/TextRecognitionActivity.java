package com.example.loginimplenetation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001b\u0010\u001d\u001a\u00020\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001fH\u0002\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/loginimplenetation/TextRecognitionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "doTheThing", "Landroid/widget/Button;", "mSelectedImage", "Landroid/graphics/Bitmap;", "manager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "myAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "submit", "getBitmapFromAsset", "context", "Landroid/content/Context;", "filePath", "loadTree", "Lcom/example/loginimplenetation/RBT;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "processTextRecognition", "texts", "Lcom/google/mlkit/vision/text/Text;", "pushToDB", "tc", "", "([Ljava/lang/String;)V", "runTextRecognition", "MyAdapter", "app_debug"})
public final class TextRecognitionActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "TextRecognitionActivity";
    private android.graphics.Bitmap mSelectedImage;
    private android.widget.Button doTheThing;
    private androidx.recyclerview.widget.RecyclerView.LayoutManager manager;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private androidx.recyclerview.widget.RecyclerView.Adapter<?> myAdapter;
    private android.widget.Button submit;
    
    public TextRecognitionActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void runTextRecognition() {
    }
    
    private final void processTextRecognition(com.google.mlkit.vision.text.Text texts) {
    }
    
    private final void pushToDB(java.lang.String[] tc) {
    }
    
    private final android.graphics.Bitmap getBitmapFromAsset(android.content.Context context, java.lang.String filePath) {
        return null;
    }
    
    private final com.example.loginimplenetation.RBT<java.lang.String> loadTree() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007\u00a8\u0006\u0016"}, d2 = {"Lcom/example/loginimplenetation/TextRecognitionActivity$MyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/loginimplenetation/TextRecognitionActivity$MyAdapter$ViewHolder;", "myDataSet", "", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "mydatadet", "getDataSet", "()[Ljava/lang/String;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
    public static final class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.loginimplenetation.TextRecognitionActivity.MyAdapter.ViewHolder> {
        private final java.lang.String[] myDataSet = null;
        private java.lang.String[] mydatadet;
        
        public MyAdapter(@org.jetbrains.annotations.NotNull
        java.lang.String[] myDataSet) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.example.loginimplenetation.TextRecognitionActivity.MyAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.example.loginimplenetation.TextRecognitionActivity.MyAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String[] getDataSet() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/loginimplenetation/TextRecognitionActivity$MyAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "text", "", "app_debug"})
        public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            private final android.view.View view = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull
            android.view.View view) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull
            java.lang.String text) {
            }
        }
    }
}