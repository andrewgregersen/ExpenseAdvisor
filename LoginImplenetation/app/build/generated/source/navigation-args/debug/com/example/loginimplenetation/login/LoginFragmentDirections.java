package com.example.loginimplenetation.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.loginimplenetation.R;

public class LoginFragmentDirections {
  private LoginFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionLoginFragmentToForgottenAccountFragment() {
    return new ActionOnlyNavDirections(R.id.action_loginFragment_to_forgottenAccountFragment);
  }

  @NonNull
  public static NavDirections actionLoginFragmentToNewAccountFragment() {
    return new ActionOnlyNavDirections(R.id.action_loginFragment_to_newAccountFragment);
  }
}
