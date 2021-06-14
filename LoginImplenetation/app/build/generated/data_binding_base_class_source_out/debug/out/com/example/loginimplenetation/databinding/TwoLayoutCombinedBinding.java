// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.loginimplenetation.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class TwoLayoutCombinedBinding extends ViewDataBinding {
  @NonNull
  public final ItemLayoutBinding a;

  @NonNull
  public final ConfirmUpdateItemBinding b;

  protected TwoLayoutCombinedBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ItemLayoutBinding a, ConfirmUpdateItemBinding b) {
    super(_bindingComponent, _root, _localFieldCount);
    this.a = a;
    this.b = b;
  }

  @NonNull
  public static TwoLayoutCombinedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.two_layout_combined, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static TwoLayoutCombinedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<TwoLayoutCombinedBinding>inflateInternal(inflater, R.layout.two_layout_combined, root, attachToRoot, component);
  }

  @NonNull
  public static TwoLayoutCombinedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.two_layout_combined, null, false, component)
   */
  @NonNull
  @Deprecated
  public static TwoLayoutCombinedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<TwoLayoutCombinedBinding>inflateInternal(inflater, R.layout.two_layout_combined, null, false, component);
  }

  public static TwoLayoutCombinedBinding bind(@NonNull View view) {
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
  public static TwoLayoutCombinedBinding bind(@NonNull View view, @Nullable Object component) {
    return (TwoLayoutCombinedBinding)bind(component, view, R.layout.two_layout_combined);
  }
}