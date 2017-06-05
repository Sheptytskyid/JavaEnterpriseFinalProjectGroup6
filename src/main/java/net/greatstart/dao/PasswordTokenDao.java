/**
 * A Spring Data supported interface to handle security token related operations with database.
 */
package net.greatstart.dao;

import net.greatstart.model.PasswordResetToken;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PasswordTokenDao extends PagingAndSortingRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findFirstByUserIdOrderByIdDesc(Long userId);


}
