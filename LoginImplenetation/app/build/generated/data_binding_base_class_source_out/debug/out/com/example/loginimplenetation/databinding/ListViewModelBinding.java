// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.loginimplenetation.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ListViewModelBinding extends ViewDataBinding {
  @NonNull
  public final TextView Iname;

  @NonNull
  public final ImageView imgView;

  protected ListViewModelBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView Iname, ImageView imgView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Iname = Iname;
    this.imgView = imgView;
  }

  @NonNull
  public static ListViewModelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_view_model, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ListViewModelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ListViewModelBinding>inflateInternal(inflater, R.layout.list_view_model, root, attachToRoot, component);
  }

  @NonNull
  public static ListViewModelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_view_model, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ListViewModelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ListViewModelBinding>inflateInternal(inflater, R.layout.list_view_model, null, false, component);
  }

  public static ListViewModelBinding bind(@NonNull View view) {
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
  public static ListViewModelBinding bind(@NonNull View view, @Nullable Object component) {
    return (ListViewModelBinding)bind(component, view, R.layout.list_view_model);
  }
}
