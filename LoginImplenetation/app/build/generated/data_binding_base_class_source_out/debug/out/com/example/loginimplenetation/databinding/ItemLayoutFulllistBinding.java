// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginimplenetation.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemLayoutFulllistBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView rvRecycleView;

  protected ItemLayoutFulllistBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RecyclerView rvRecycleView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.rvRecycleView = rvRecycleView;
  }

  @NonNull
  public static ItemLayoutFulllistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_layout_fulllist, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemLayoutFulllistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemLayoutFulllistBinding>inflateInternal(inflater, R.layout.item_layout_fulllist, root, attachToRoot, component);
  }

  @NonNull
  public static ItemLayoutFulllistBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_layout_fulllist, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemLayoutFulllistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemLayoutFulllistBinding>inflateInternal(inflater, R.layout.item_layout_fulllist, null, false, component);
  }

  public static ItemLayoutFulllistBinding bind(@NonNull View view) {
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
  public static ItemLayoutFulllistBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemLayoutFulllistBinding)bind(component, view, R.layout.item_layout_fulllist);
  }
}
