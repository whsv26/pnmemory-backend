package org.whsv26.pnmemory.domain.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.whsv26.pnmemory.domain.security.model.FcmToken;
import org.whsv26.pnmemory.domain.security.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FcmTokenRepository extends CrudRepository<FcmToken, UUID> {
}
