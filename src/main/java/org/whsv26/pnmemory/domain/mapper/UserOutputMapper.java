package org.whsv26.pnmemory.domain.mapper;

import org.mapstruct.Mapper;
import org.whsv26.pnmemory.application.dto.output.UserOutput;
import org.whsv26.pnmemory.domain.model.User;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserOutputMapper {
  public abstract UserOutput toUserOutput(User user);
  public abstract List<UserOutput> toUserOutput(List<User> user);
}
