package com.sagengaliyev.task.repositories;

import com.sagengaliyev.task.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    /**
     * Запрос БД №4 задания: Изменение статуса пользователя (Online, Offline).
     * @param id
     * @param newStatus
     */
    @Modifying
    @Transactional
    @Query(value = "update User u set u.newStatus=:newStatus where u.id=:id")
    public void updateById(@Param("id") long id,@Param("newStatus") String newStatus);
}
