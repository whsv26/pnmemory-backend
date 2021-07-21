package org.whsv26.pnmemory.application.mapper.security;

import org.mapstruct.Mapper;
import org.whsv26.pnmemory.application.dto.security.output.UserOutput;
import org.whsv26.pnmemory.domain.security.model.User;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserOutputMapper {
  public abstract UserOutput toUserOutput(User user);
  public abstract List<UserOutput> toUserOutput(List<User> user);
}
