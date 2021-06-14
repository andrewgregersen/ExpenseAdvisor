package com.example.loginimplenetation.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.loginimplenetation.R;

public class NewAccountFragmentDirections {
  private NewAccountFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionNewAccountFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_newAccountFragment_to_loginFragment);
  }
}
