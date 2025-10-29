package com.edulend.loans.useCases;

import java.util.List;
import java.util.Optional;

import com.edulend.loans.datasource.LoanDatasource;
import com.edulend.loans.models.Loan;

public class LoanUseCase {

    private final LoanDatasource loanDatasource;

    public LoanUseCase(LoanDatasource loanDatasource) {
        this.loanDatasource = loanDatasource;
    }

    public Loan create(Loan loan) {
        return loanDatasource.save(loan);
    }

    public List<Loan> getAll() {
        return loanDatasource.findAll();
    }

    public Optional<Loan> getById(int id) {
        return loanDatasource.findById(id);
    }

    public List<Loan> getByUser(int user_id) {
        return loanDatasource.findByUser(user_id);
    }

    public List<Loan> getByItem(int item_id) {
        return loanDatasource.findByItem(item_id);
    }

    public List<Loan> getByStatus(boolean status) {
        return loanDatasource.findByStatus(status);
    }

    public Loan update(int id, Loan loanData) {
        Loan existing = loanDatasource.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        existing.setUser(loanData.getUser());
        existing.setItem(loanData.getItem());
        existing.setLoan_date(loanData.getLoan_date());
        existing.setDue_date(loanData.getDue_date());
        existing.setReturn_date(loanData.getReturn_date());
        existing.setStatus(loanData.isStatus());

        return loanDatasource.save(existing);
    }

    public void delete(int id) {
        loanDatasource.deleteById(id);
    }
}