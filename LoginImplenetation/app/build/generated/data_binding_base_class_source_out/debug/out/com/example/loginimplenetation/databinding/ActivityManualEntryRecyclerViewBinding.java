// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginimplenetation.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityManualEntryRecyclerViewBinding extends ViewDataBinding {
  @NonNull
  public final Button CategoryBtn;

  @NonNull
  public final Button Return;

  @NonNull
  public final Button SubmitMan;

  @NonNull
  public final Button addMore;

  @NonNull
  public final TextView catChoice;

  @NonNull
  public final Button clear;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final EditText itemAmount;

  @NonNull
  public final EditText itemCost;

  @NonNull
  public final EditText itemname;

  @NonNull
  public final RecyclerView manEntryRec;

  @NonNull
  public final EditText storeName;

  @NonNull
  public final EditText taxPaid;

  @NonNull
  public final EditText totalPrice;

  protected ActivityManualEntryRecyclerViewBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button CategoryBtn, Button Return, Button SubmitMan, Button addMore,
      TextView catChoice, Button clear, ConstraintLayout constraintLayout, EditText itemAmount,
      EditText itemCost, EditText itemname, RecyclerView manEntryRec, EditText storeName,
      EditText taxPaid, EditText totalPrice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.CategoryBtn = CategoryBtn;
    this.Return = Return;
    this.SubmitMan = SubmitMan;
    this.addMore = addMore;
    this.catChoice = catChoice;
    this.clear = clear;
    this.constraintLayout = constraintLayout;
    this.itemAmount = itemAmount;
    this.itemCost = itemCost;
    this.itemname = itemname;
    this.manEntryRec = manEntryRec;
    this.storeName = storeName;
    this.taxPaid = taxPaid;
    this.totalPrice = totalPrice;
  }

  @NonNull
  public static ActivityManualEntryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_manual_entry_recycler_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityManualEntryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityManualEntryRecyclerViewBinding>inflateInternal(inflater, R.layout.activity_manual_entry_recycler_view, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityManualEntryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_manual_entry_recycler_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityManualEntryRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityManualEntryRecyclerViewBinding>inflateInternal(inflater, R.layout.activity_manual_entry_recycler_view, null, false, component);
  }

  public static ActivityManualEntryRecyclerViewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityManualEntryRecyclerViewBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityManualEntryRecyclerViewBinding)bind(component, view, R.layout.activity_manual_entry_recycler_view);
  }
}
