package com.example.loginimplenetation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u000f\u0010\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002J\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J \u0010\u000e\u001a\u00020\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/loginimplenetation/ManualEntry;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "Binding", "Lcom/example/loginimplenetation/databinding/ActivityManualEntryRecyclerViewBinding;", "alertDialog", "", "list", "Ljava/util/ArrayList;", "Lcom/example/loginimplenetation/ManualEntry$Item;", "Lkotlin/collections/ArrayList;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "submitItems", "CustomViewHolder", "Item", "MyAdapter", "app_debug"})
public final class ManualEntry extends androidx.appcompat.app.AppCompatActivity {
    private com.example.loginimplenetation.databinding.ActivityManualEntryRecyclerViewBinding Binding;
    
    public ManualEntry() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Warns the User about how the act of submitting while final, creates more work for them in the long run if they do make a mistake
     * Other than that, gets the users permission to submit the data.
     */
    private final void alertDialog(java.util.ArrayList<com.example.loginimplenetation.ManualEntry.Item> list) {
    }
    
    /**
     * Submits the receipt to the database and then returns the user to the main screen!
     */
    private final void submitItems(java.util.ArrayList<com.example.loginimplenetation.ManualEntry.Item> list) {
    }
    
    /**
     * @author Andrew Gregersen
     * Adapter class for the recycler view.
     * Implements methods to update, delete, and add more elements, starting with a single one.
     * @param mData: A list of "Items" that would appear on a receipt
     * @param iAmount: Pass a pointer to EditText for Updating the item
     * @param iPrice: Pass a pointer to EditText for Updating the item
     * @param iName: Pass a pointer to EditText for Updating the item
     * @param iCat: Pass a pointer to TextView for Updating the item
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u0000 \'2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\'B3\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005J\u0006\u0010\u001e\u001a\u00020\u001cJ\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0012H\u0016J\u0018\u0010%\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0012H\u0016R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/example/loginimplenetation/ManualEntry$MyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/loginimplenetation/ManualEntry$CustomViewHolder;", "mData", "", "Lcom/example/loginimplenetation/ManualEntry$Item;", "iName", "Landroid/widget/EditText;", "iPrice", "iAmount", "iCat", "Landroid/widget/TextView;", "(Ljava/util/List;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/widget/TextView;)V", "getICat", "()Landroid/widget/TextView;", "getIName", "()Landroid/widget/EditText;", "lastPos", "", "getLastPos", "()I", "setLastPos", "(I)V", "getMData", "()Ljava/util/List;", "parent", "Landroid/view/ViewGroup;", "addItem", "", "item", "deleteFirst", "deleteItem", "index", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "viewType", "Companion", "app_debug"})
    public static final class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.loginimplenetation.ManualEntry.CustomViewHolder> {
        @org.jetbrains.annotations.NotNull
        private final java.util.List<com.example.loginimplenetation.ManualEntry.Item> mData = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.EditText iName = null;
        private final android.widget.EditText iPrice = null;
        private final android.widget.EditText iAmount = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView iCat = null;
        private int lastPos = 0;
        private android.view.ViewGroup parent;
        @org.jetbrains.annotations.NotNull
        public static final com.example.loginimplenetation.ManualEntry.MyAdapter.Companion Companion = null;
        
        public MyAdapter(@org.jetbrains.annotations.NotNull
        java.util.List<com.example.loginimplenetation.ManualEntry.Item> mData, @org.jetbrains.annotations.NotNull
        android.widget.EditText iName, @org.jetbrains.annotations.NotNull
        android.widget.EditText iPrice, @org.jetbrains.annotations.NotNull
        android.widget.EditText iAmount, @org.jetbrains.annotations.NotNull
        android.widget.TextView iCat) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<com.example.loginimplenetation.ManualEntry.Item> getMData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.EditText getIName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getICat() {
            return null;
        }
        
        public final int getLastPos() {
            return 0;
        }
        
        public final void setLastPos(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.example.loginimplenetation.ManualEntry.CustomViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        /**
         * Called by RecyclerView to display the data at the specified position. This method should
         * update the contents of the [ViewHolder.itemView] to reflect the item at the given
         * position.
         *
         *
         * Note that unlike [android.widget.ListView], RecyclerView will not call this method
         * again if the position of the item changes in the data set unless the item itself is
         * invalidated or the new position cannot be determined. For this reason, you should only
         * use the `position` parameter while acquiring the related data item inside
         * this method and should not keep a copy of it. If you need the position of an item later
         * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
         * have the updated adapter position.
         *
         * Override [.onBindViewHolder] instead if Adapter can
         * handle efficient partial bind.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         * item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.example.loginimplenetation.ManualEntry.CustomViewHolder holder, int position) {
        }
        
        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @java.lang.Override
        public int getItemCount() {
            return 0;
        }
        
        /**
         * Removes an item from the recycler view
         */
        private final void deleteItem(int index) {
        }
        
        public final void deleteFirst() {
        }
        
        /**
         * Adds an Item to the data list, and informs the Recycler View that a new item has been updated
         */
        public final void addItem(@org.jetbrains.annotations.NotNull
        com.example.loginimplenetation.ManualEntry.Item item) {
        }
        
        @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/loginimplenetation/ManualEntry$MyAdapter$Companion;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/loginimplenetation/ManualEntry$Item;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
        public static final class Companion extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.loginimplenetation.ManualEntry.Item> {
            
            private Companion() {
                super();
            }
            
            @java.lang.Override
            public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
            com.example.loginimplenetation.ManualEntry.Item oldItem, @org.jetbrains.annotations.NotNull
            com.example.loginimplenetation.ManualEntry.Item newItem) {
                return false;
            }
            
            @java.lang.Override
            public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
            com.example.loginimplenetation.ManualEntry.Item oldItem, @org.jetbrains.annotations.NotNull
            com.example.loginimplenetation.ManualEntry.Item newItem) {
                return false;
            }
        }
    }
    
    /**
     * Small class with basic constructor to represent a single item in a list of many
     * @param itemName Holds the items name
     * @param itemPrice Holds the items cost
     * @param itemAmount Holds how many of said item there is
     * @param itemCategory Holds the value for the items category
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/example/loginimplenetation/ManualEntry$Item;", "", "itemName", "", "itemPrice", "", "itemAmount", "", "itemCategory", "(Ljava/lang/String;DILjava/lang/String;)V", "getItemAmount", "()I", "setItemAmount", "(I)V", "getItemCategory", "()Ljava/lang/String;", "setItemCategory", "(Ljava/lang/String;)V", "getItemName", "setItemName", "getItemPrice", "()D", "setItemPrice", "(D)V", "app_debug"})
    public static final class Item {
        @org.jetbrains.annotations.NotNull
        private java.lang.String itemName;
        private double itemPrice;
        private int itemAmount;
        @org.jetbrains.annotations.NotNull
        private java.lang.String itemCategory;
        
        public Item() {
            super();
        }
        
        public Item(@org.jetbrains.annotations.NotNull
        java.lang.String itemName, double itemPrice, int itemAmount, @org.jetbrains.annotations.NotNull
        java.lang.String itemCategory) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getItemName() {
            return null;
        }
        
        public final void setItemName(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        public final double getItemPrice() {
            return 0.0;
        }
        
        public final void setItemPrice(double p0) {
        }
        
        public final int getItemAmount() {
            return 0;
        }
        
        public final void setItemAmount(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getItemCategory() {
            return null;
        }
        
        public final void setItemCategory(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/loginimplenetation/ManualEntry$CustomViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "(Landroidx/databinding/ViewDataBinding;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "app_debug"})
    public static class CustomViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final androidx.databinding.ViewDataBinding binding = null;
        
        public CustomViewHolder(@org.jetbrains.annotations.NotNull
        androidx.databinding.ViewDataBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.databinding.ViewDataBinding getBinding() {
            return null;
        }
    }
}