package br.com.zup.academy.contadigital.contadigital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaDigitalRepository extends JpaRepository<ContaDigital, Long> {
    Optional<ContaDigital> findByIdCliente(Long idCliente);
}
