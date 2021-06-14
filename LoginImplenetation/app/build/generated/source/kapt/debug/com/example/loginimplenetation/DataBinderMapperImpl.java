package com.example.loginimplenetation;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.loginimplenetation.databinding.ActivityCameraBindingImpl;
import com.example.loginimplenetation.databinding.ActivityMainBindingImpl;
import com.example.loginimplenetation.databinding.ActivityManualEntryFormatBindingImpl;
import com.example.loginimplenetation.databinding.ActivityManualEntryRecyclerViewBindingImpl;
import com.example.loginimplenetation.databinding.ActivitySettingsBindingImpl;
import com.example.loginimplenetation.databinding.ActivityUpdateBindingImpl;
import com.example.loginimplenetation.databinding.ConfirmUpdateItemBindingImpl;
import com.example.loginimplenetation.databinding.ContentMainBindingImpl;
import com.example.loginimplenetation.databinding.FooditemLayoutBindingImpl;
import com.example.loginimplenetation.databinding.FragmentCategoriesBindingImpl;
import com.example.loginimplenetation.databinding.FragmentCategoriesBindingV23Impl;
import com.example.loginimplenetation.databinding.FragmentForgottenAccountBindingImpl;
import com.example.loginimplenetation.databinding.FragmentHistoryBindingImpl;
import com.example.loginimplenetation.databinding.FragmentHomeBindingImpl;
import com.example.loginimplenetation.databinding.FragmentLoginBindingImpl;
import com.example.loginimplenetation.databinding.FragmentNewAccountBindingImpl;
import com.example.loginimplenetation.databinding.HistoryLayoutBindingImpl;
import com.example.loginimplenetation.databinding.ItemLayoutBindingImpl;
import com.example.loginimplenetation.databinding.ItemLayoutFulllistBindingImpl;
import com.example.loginimplenetation.databinding.ListItemBindingImpl;
import com.example.loginimplenetation.databinding.ListViewModelBindingImpl;
import com.example.loginimplenetation.databinding.LoggedActivityBindingImpl;
import com.example.loginimplenetation.databinding.NavHeaderBindingImpl;
import com.example.loginimplenetation.databinding.ProfileActivityBindingImpl;
import com.example.loginimplenetation.databinding.SettingsActivityBindingImpl;
import com.example.loginimplenetation.databinding.TextRecognitionActivityBindingImpl;
import com.example.loginimplenetation.databinding.TwoLayoutCombinedBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCAMERA = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_ACTIVITYMANUALENTRYFORMAT = 3;

  private static final int LAYOUT_ACTIVITYMANUALENTRYRECYCLERVIEW = 4;

  private static final int LAYOUT_ACTIVITYSETTINGS = 5;

  private static final int LAYOUT_ACTIVITYUPDATE = 6;

  private static final int LAYOUT_CONFIRMUPDATEITEM = 7;

  private static final int LAYOUT_CONTENTMAIN = 8;

  private static final int LAYOUT_FOODITEMLAYOUT = 9;

  private static final int LAYOUT_FRAGMENTCATEGORIES = 10;

  private static final int LAYOUT_FRAGMENTFORGOTTENACCOUNT = 11;

  private static final int LAYOUT_FRAGMENTHISTORY = 12;

  private static final int LAYOUT_FRAGMENTHOME = 13;

  private static final int LAYOUT_FRAGMENTLOGIN = 14;

  private static final int LAYOUT_FRAGMENTNEWACCOUNT = 15;

  private static final int LAYOUT_HISTORYLAYOUT = 16;

  private static final int LAYOUT_ITEMLAYOUT = 17;

  private static final int LAYOUT_ITEMLAYOUTFULLLIST = 18;

  private static final int LAYOUT_LISTITEM = 19;

  private static final int LAYOUT_LISTVIEWMODEL = 20;

  private static final int LAYOUT_LOGGEDACTIVITY = 21;

  private static final int LAYOUT_NAVHEADER = 22;

  private static final int LAYOUT_PROFILEACTIVITY = 23;

  private static final int LAYOUT_SETTINGSACTIVITY = 24;

  private static final int LAYOUT_TEXTRECOGNITIONACTIVITY = 25;

  private static final int LAYOUT_TWOLAYOUTCOMBINED = 26;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(26);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_camera, LAYOUT_ACTIVITYCAMERA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_manual_entry_format, LAYOUT_ACTIVITYMANUALENTRYFORMAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_manual_entry_recycler_view, LAYOUT_ACTIVITYMANUALENTRYRECYCLERVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_settings, LAYOUT_ACTIVITYSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.activity_update, LAYOUT_ACTIVITYUPDATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.confirm_update_item, LAYOUT_CONFIRMUPDATEITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.content_main, LAYOUT_CONTENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fooditem_layout, LAYOUT_FOODITEMLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_categories, LAYOUT_FRAGMENTCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_forgotten_account, LAYOUT_FRAGMENTFORGOTTENACCOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_history, LAYOUT_FRAGMENTHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.fragment_new_account, LAYOUT_FRAGMENTNEWACCOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.history_layout, LAYOUT_HISTORYLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.item_layout, LAYOUT_ITEMLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.item_layout_fulllist, LAYOUT_ITEMLAYOUTFULLLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.list_item, LAYOUT_LISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.list_view_model, LAYOUT_LISTVIEWMODEL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.logged_activity, LAYOUT_LOGGEDACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.nav_header, LAYOUT_NAVHEADER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.profile_activity, LAYOUT_PROFILEACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.settings_activity, LAYOUT_SETTINGSACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.text_recognition_activity, LAYOUT_TEXTRECOGNITIONACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.loginimplenetation.R.layout.two_layout_combined, LAYOUT_TWOLAYOUTCOMBINED);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCAMERA: {
          if ("layout/activity_camera_0".equals(tag)) {
            return new ActivityCameraBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_camera is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMANUALENTRYFORMAT: {
          if ("layout/activity_manual_entry_format_0".equals(tag)) {
            return new ActivityManualEntryFormatBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_manual_entry_format is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMANUALENTRYRECYCLERVIEW: {
          if ("layout/activity_manual_entry_recycler_view_0".equals(tag)) {
            return new ActivityManualEntryRecyclerViewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_manual_entry_recycler_view is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSETTINGS: {
          if ("layout/activity_settings_0".equals(tag)) {
            return new ActivitySettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUPDATE: {
          if ("layout/activity_update_0".equals(tag)) {
            return new ActivityUpdateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_update is invalid. Received: " + tag);
        }
        case  LAYOUT_CONFIRMUPDATEITEM: {
          if ("layout/confirm_update_item_0".equals(tag)) {
            return new ConfirmUpdateItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for confirm_update_item is invalid. Received: " + tag);
        }
        case  LAYOUT_CONTENTMAIN: {
          if ("layout/content_main_0".equals(tag)) {
            return new ContentMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for content_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FOODITEMLAYOUT: {
          if ("layout/fooditem_layout_0".equals(tag)) {
            return new FooditemLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fooditem_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCATEGORIES: {
          if ("layout/fragment_categories_0".equals(tag)) {
            return new FragmentCategoriesBindingImpl(component, view);
          }
          if ("layout-v23/fragment_categories_0".equals(tag)) {
            return new FragmentCategoriesBindingV23Impl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_categories is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFORGOTTENACCOUNT: {
          if ("layout/fragment_forgotten_account_0".equals(tag)) {
            return new FragmentForgottenAccountBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_forgotten_account is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHISTORY: {
          if ("layout/fragment_history_0".equals(tag)) {
            return new FragmentHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_history is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWACCOUNT: {
          if ("layout/fragment_new_account_0".equals(tag)) {
            return new FragmentNewAccountBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_new_account is invalid. Received: " + tag);
        }
        case  LAYOUT_HISTORYLAYOUT: {
          if ("layout/history_layout_0".equals(tag)) {
            return new HistoryLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for history_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLAYOUT: {
          if ("layout/item_layout_0".equals(tag)) {
            return new ItemLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLAYOUTFULLLIST: {
          if ("layout/item_layout_fulllist_0".equals(tag)) {
            return new ItemLayoutFulllistBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_layout_fulllist is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEM: {
          if ("layout/list_item_0".equals(tag)) {
            return new ListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTVIEWMODEL: {
          if ("layout/list_view_model_0".equals(tag)) {
            return new ListViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_view_model is invalid. Received: " + tag);
        }
        case  LAYOUT_LOGGEDACTIVITY: {
          if ("layout/logged_activity_0".equals(tag)) {
            return new LoggedActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for logged_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_NAVHEADER: {
          if ("layout/nav_header_0".equals(tag)) {
            return new NavHeaderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for nav_header is invalid. Received: " + tag);
        }
        case  LAYOUT_PROFILEACTIVITY: {
          if ("layout/profile_activity_0".equals(tag)) {
            return new ProfileActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for profile_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_SETTINGSACTIVITY: {
          if ("layout/settings_activity_0".equals(tag)) {
            return new SettingsActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for settings_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_TEXTRECOGNITIONACTIVITY: {
          if ("layout/text_recognition_activity_0".equals(tag)) {
            return new TextRecognitionActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for text_recognition_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_TWOLAYOUTCOMBINED: {
          if ("layout/two_layout_combined_0".equals(tag)) {
            return new TwoLayoutCombinedBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for two_layout_combined is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(27);

    static {
      sKeys.put("layout/activity_camera_0", com.example.loginimplenetation.R.layout.activity_camera);
      sKeys.put("layout/activity_main_0", com.example.loginimplenetation.R.layout.activity_main);
      sKeys.put("layout/activity_manual_entry_format_0", com.example.loginimplenetation.R.layout.activity_manual_entry_format);
      sKeys.put("layout/activity_manual_entry_recycler_view_0", com.example.loginimplenetation.R.layout.activity_manual_entry_recycler_view);
      sKeys.put("layout/activity_settings_0", com.example.loginimplenetation.R.layout.activity_settings);
      sKeys.put("layout/activity_update_0", com.example.loginimplenetation.R.layout.activity_update);
      sKeys.put("layout/confirm_update_item_0", com.example.loginimplenetation.R.layout.confirm_update_item);
      sKeys.put("layout/content_main_0", com.example.loginimplenetation.R.layout.content_main);
      sKeys.put("layout/fooditem_layout_0", com.example.loginimplenetation.R.layout.fooditem_layout);
      sKeys.put("layout/fragment_categories_0", com.example.loginimplenetation.R.layout.fragment_categories);
      sKeys.put("layout-v23/fragment_categories_0", com.example.loginimplenetation.R.layout.fragment_categories);
      sKeys.put("layout/fragment_forgotten_account_0", com.example.loginimplenetation.R.layout.fragment_forgotten_account);
      sKeys.put("layout/fragment_history_0", com.example.loginimplenetation.R.layout.fragment_history);
      sKeys.put("layout/fragment_home_0", com.example.loginimplenetation.R.layout.fragment_home);
      sKeys.put("layout/fragment_login_0", com.example.loginimplenetation.R.layout.fragment_login);
      sKeys.put("layout/fragment_new_account_0", com.example.loginimplenetation.R.layout.fragment_new_account);
      sKeys.put("layout/history_layout_0", com.example.loginimplenetation.R.layout.history_layout);
      sKeys.put("layout/item_layout_0", com.example.loginimplenetation.R.layout.item_layout);
      sKeys.put("layout/item_layout_fulllist_0", com.example.loginimplenetation.R.layout.item_layout_fulllist);
      sKeys.put("layout/list_item_0", com.example.loginimplenetation.R.layout.list_item);
      sKeys.put("layout/list_view_model_0", com.example.loginimplenetation.R.layout.list_view_model);
      sKeys.put("layout/logged_activity_0", com.example.loginimplenetation.R.layout.logged_activity);
      sKeys.put("layout/nav_header_0", com.example.loginimplenetation.R.layout.nav_header);
      sKeys.put("layout/profile_activity_0", com.example.loginimplenetation.R.layout.profile_activity);
      sKeys.put("layout/settings_activity_0", com.example.loginimplenetation.R.layout.settings_activity);
      sKeys.put("layout/text_recognition_activity_0", com.example.loginimplenetation.R.layout.text_recognition_activity);
      sKeys.put("layout/two_layout_combined_0", com.example.loginimplenetation.R.layout.two_layout_combined);
    }
  }
}
