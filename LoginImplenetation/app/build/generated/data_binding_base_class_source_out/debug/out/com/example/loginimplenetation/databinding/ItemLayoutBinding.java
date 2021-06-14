// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.loginimplenetation.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemLayoutBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout centre;

  @NonNull
  public final CardView cvCardView;

  @NonNull
  public final ImageView deleteItem;

  @NonNull
  public final ImageView editItem;

  @NonNull
  public final TextView historyDescription;

  @NonNull
  public final ImageView historyImage;

  @NonNull
  public final TextView historyTitle;

  @NonNull
  public final LinearLayout rest;

  @NonNull
  public final RelativeLayout rlWrapper;

  protected ItemLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout centre, CardView cvCardView, ImageView deleteItem, ImageView editItem,
      TextView historyDescription, ImageView historyImage, TextView historyTitle, LinearLayout rest,
      RelativeLayout rlWrapper) {
    super(_bindingComponent, _root, _localFieldCount);
    this.centre = centre;
    this.cvCardView = cvCardView;
    this.deleteItem = deleteItem;
    this.editItem = editItem;
    this.historyDescription = historyDescription;
    this.historyImage = historyImage;
    this.historyTitle = historyTitle;
    this.rest = rest;
    this.rlWrapper = rlWrapper;
  }

  @NonNull
  public static ItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_layout, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemLayoutBinding>inflateInternal(inflater, R.layout.item_layout, root, attachToRoot, component);
  }

  @NonNull
  public static ItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_layout, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemLayoutBinding>inflateInternal(inflater, R.layout.item_layout, null, false, component);
  }

  public static ItemLayoutBinding bind(@NonNull View view) {
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
  public static ItemLayoutBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemLayoutBinding)bind(component, view, R.layout.item_layout);
  }
}