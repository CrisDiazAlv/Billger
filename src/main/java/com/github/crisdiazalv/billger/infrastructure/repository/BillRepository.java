package com.github.crisdiazalv.billger.infrastructure.repository;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    // Aqui Spring hace la magia
    /*
     * Entiende que extiende de JPARepository y le implementa varios metodos
     * para que haga determinadas acciones como: buscar, guardar, actualizar, borrar ...
     */

    List<Bill> findByCategory(Category category);

}
