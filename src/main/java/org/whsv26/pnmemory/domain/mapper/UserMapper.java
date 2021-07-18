package org.whsv26.pnmemory.domain.mapper;

import org.mapstruct.Mapper;
import org.whsv26.pnmemory.application.dto.output.UserView;
import org.whsv26.pnmemory.domain.entity.User;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
  public abstract UserView toUserView(User user);
  public abstract List<UserView> toUserView(List<User> user);
}
