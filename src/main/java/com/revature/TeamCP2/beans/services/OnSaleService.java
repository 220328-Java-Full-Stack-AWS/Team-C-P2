/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: OnSaleService provides implementations to persist or retrieve OnSale entities.
 *
 */
package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.OnSaleRepository;
import com.revature.TeamCP2.entities.OnSale;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OnSaleService {
    private final OnSaleRepository onSaleRepository;

    @Autowired
    public OnSaleService(OnSaleRepository onSaleRepository) {
        this.onSaleRepository = onSaleRepository;
    }

    public OnSale createOnSale (OnSale saleToBeCreated) {
        return onSaleRepository.create(saleToBeCreated);
    }

    public Optional<OnSale> getById(int id) throws ItemDoesNotExistException {
        return onSaleRepository.getById(id);
    }

    public List<OnSale> getAll() {
        return onSaleRepository.getAll();
    }

    public void delete(OnSale model) throws ItemHasNoIdException, ItemDoesNotExistException {
        onSaleRepository.delete(model);
    }

    public OnSale update(OnSale onSale) {
        return onSaleRepository.update(onSale);
    }
}
