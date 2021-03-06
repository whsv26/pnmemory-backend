package org.whsv26.pnmemory.application.mapper.security;

import org.mapstruct.Mapper;
import java.util.UUID;

@Mapper(componentModel = "spring")
public class UuidMapper {
  public String uuidToString(UUID uuid) {
    return uuid.toString();
  }

  public UUID stringToUuid(String uuid) {
    return UUID.fromString(uuid);
  }
}
