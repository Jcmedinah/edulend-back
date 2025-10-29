package com.edulend.loans.datasource;

import java.util.List;
import java.util.Optional;

import com.edulend.loans.models.Loan;

public class LoanDatasource {

    private final LoanRepository loanRepository;

    // Constructor con inyección de dependencia
    public LoanDatasource(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Crear o actualizar un préstamo
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    // Obtener todos los préstamos
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    // Buscar préstamo por ID
    public Optional<Loan> findById(int id) {
        return loanRepository.findById(id);
    }

    // Buscar préstamos por ID de usuario
    public List<Loan> findByUser(int userId) {
        return loanRepository.findByUser_UserId(userId);
    }

    // Buscar préstamos por ID del artículo
    public List<Loan> findByItem(int itemId) {
        return loanRepository.findByItem_ItemId(itemId);
    }

    // Buscar préstamos por estado (activo o devuelto)
    public List<Loan> findByStatus(boolean status) {
        return loanRepository.findByStatus(status);
    }

    // Eliminar préstamo por ID
    public void deleteById(int id) {
        loanRepository.deleteById(id);
    }
}