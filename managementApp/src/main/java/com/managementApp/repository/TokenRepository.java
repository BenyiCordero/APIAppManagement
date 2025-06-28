package com.managementApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.managementApp.domain.Token;
import com.managementApp.domain.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
      select t from Token t
      where t.empleado.id = :id and (t.isExpired = false or t.isRevoked = false)
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    List<Token> findByEmpleado(Empleado empleado);
}
