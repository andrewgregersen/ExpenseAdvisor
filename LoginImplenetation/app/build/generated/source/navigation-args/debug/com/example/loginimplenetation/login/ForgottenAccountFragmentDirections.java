package com.example.loginimplenetation.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.loginimplenetation.R;

public class ForgottenAccountFragmentDirections {
  private ForgottenAccountFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionForgottenAccountFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_forgottenAccountFragment_to_loginFragment);
  }
}
