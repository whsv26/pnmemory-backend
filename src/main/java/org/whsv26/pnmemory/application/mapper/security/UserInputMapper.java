package org.whsv26.pnmemory.application.mapper.security;

import org.mapstruct.*;
import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.domain.security.model.User;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = UuidMapper.class)
public abstract class UserInputMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fcmToken", ignore = true)
  public abstract User create(CreateUserInput input);

  @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fcmToken", ignore = true)
  public abstract User update(UpdateUserInput input, @MappingTarget User user);
}
