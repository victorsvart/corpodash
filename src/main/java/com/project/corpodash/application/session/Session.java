package com.project.corpodash.application.session;

import com.project.corpodash.domain.user.User;
import com.project.corpodash.infrastructure.security.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Session {
  public static Long getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null
        && authentication.getPrincipal() instanceof AppUserDetails userDetails) {
      User user = userDetails.getDomainUser();
      return user.getId();
    }

    throw new IllegalStateException("No authenticated user found");
  }

  public static User getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null
        && authentication.getPrincipal() instanceof AppUserDetails userDetails) {
      return userDetails.getDomainUser();
    }

    throw new IllegalStateException("No authenticated user found");
  }

  public static boolean isAdmin() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null
        && authentication.getPrincipal() instanceof AppUserDetails userDetails) {
      return userDetails.isAdmin();
    }

    throw new IllegalStateException("No authenticated user found");
  }
}
