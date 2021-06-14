// Generated by data binding compiler. Do not edit!
package com.example.loginimplenetation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.example.loginimplenetation.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LoggedActivityBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final TabLayout bar2;

  @NonNull
  public final NavigationView navView;

  @NonNull
  public final DrawerLayout settingsDrawer;

  @NonNull
  public final ViewPager viewPager;

  protected LoggedActivityBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appBarLayout, TabLayout bar2, NavigationView navView,
      DrawerLayout settingsDrawer, ViewPager viewPager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appBarLayout = appBarLayout;
    this.bar2 = bar2;
    this.navView = navView;
    this.settingsDrawer = settingsDrawer;
    this.viewPager = viewPager;
  }

  @NonNull
  public static LoggedActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.logged_activity, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LoggedActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LoggedActivityBinding>inflateInternal(inflater, R.layout.logged_activity, root, attachToRoot, component);
  }

  @NonNull
  public static LoggedActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.logged_activity, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LoggedActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LoggedActivityBinding>inflateInternal(inflater, R.layout.logged_activity, null, false, component);
  }

  public static LoggedActivityBinding bind(@NonNull View view) {
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
  public static LoggedActivityBinding bind(@NonNull View view, @Nullable Object component) {
    return (LoggedActivityBinding)bind(component, view, R.layout.logged_activity);
  }
}