package org.whsv26.pnmemory.application.dto.security.input;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class RefreshFcmTokenInput {
  @NotNull
  String fcmToken;
}
