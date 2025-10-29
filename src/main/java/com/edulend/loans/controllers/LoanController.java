package com.edulend.loans.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edulend.loans.datasource.LoanDatasource;
import com.edulend.loans.datasource.LoanRepository;
import com.edulend.loans.models.Loan;
import com.edulend.loans.useCases.LoanUseCase;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanUseCase loanUseCase;

    @Autowired
    public LoanController(LoanRepository loanRepository) {
        LoanDatasource datasource = new LoanDatasource(loanRepository);
        this.loanUseCase = new LoanUseCase(datasource);
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan) {
        return loanUseCase.create(loan);
    }

    @GetMapping
    public List<Loan> getAll() {
        return loanUseCase.getAll();
    }

    @GetMapping("/{id}")
    public Loan getById(@PathVariable int id) {
        return loanUseCase.getById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    @GetMapping("/user/{user_id}")
    public List<Loan> getByUser(@PathVariable int user_id) {
        return loanUseCase.getByUser(user_id);
    }

    @GetMapping("/item/{item_id}")
    public List<Loan> getByItem(@PathVariable int item_id) {
        return loanUseCase.getByItem(item_id);
    }

    @GetMapping("/status/{status}")
    public List<Loan> getByStatus(@PathVariable boolean status) {
        return loanUseCase.getByStatus(status);
    }

    @PutMapping("/{id}")
    public Loan update(@PathVariable int id, @RequestBody Loan loan) {
        return loanUseCase.update(id, loan);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        loanUseCase.delete(id);
        return "Préstamo eliminado correctamente";
    }
}