package com.github.crisdiazalv.billger.infrastructure.repository;

import com.github.crisdiazalv.billger.domain.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    // Aqui Spring hace la magia
    /*
     * Entiende que extiende de JPARepository y le implementa varios metodos
     * para que haga determinadas acciones como: buscar, guardar, actualizar, borrar ...
     */
}
