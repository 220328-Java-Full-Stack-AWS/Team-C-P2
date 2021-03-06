/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Spring Rest Controller to process requests from frontend API and call backend OnSale service methods
 */

package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.OnSaleService;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.dtos.OnSaleDto;
import com.revature.TeamCP2.entities.OnSale;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class OnSaleController {

    private final OnSaleService onSaleService;
    private final ProductService productService;

    // constructor
    public OnSaleController(OnSaleService onSaleService, ProductService productService) {
        this.onSaleService = onSaleService;
        this.productService = productService;
    }

    // get all sales
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto getAllSales(HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {

        if (userSession == null) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        List<OnSale> sales = onSaleService.getAll();
        res.setStatus(200);
        return new HttpResponseDto(200, "Success.", sales);

    }

    // get sale by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto getSaleById(@PathVariable Integer id, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if (userSession == null) {
                res.setStatus(400);
                return new HttpResponseDto(400, "Failed. You are not logged in", null);
            }

            if (onSaleService.getById(id).isPresent()) {
                OnSale sale = onSaleService.getById(id).get();
                res.setStatus(200);
                return new HttpResponseDto(200, "Success.", sale);
            }
        } catch (ItemDoesNotExistException e) {
            res.setStatus(404);
        }
        res.setStatus(404);
        return new HttpResponseDto(404, "Failed. Sale not found.", null);

    }

    // get products by sale
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto getProductsBySale(@PathVariable Integer id, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if (userSession == null) {
                res.setStatus(400);
                return new HttpResponseDto(400, "Failed. You are not logged in", null);
            }
            List<Product> saleProducts = new LinkedList<>();

            if (onSaleService.getById(id).isPresent()) {
                OnSale sale = onSaleService.getById(id).get();
                List<Product> products = productService.getAll();
                for (Product p : products) {
                    if (p.getOnSale() != null && p.getOnSale().equals(sale)) {
                        saleProducts.add(p);
                    }
                }
                res.setStatus(200);
                return new HttpResponseDto(200, "Successfully retrieved products.", saleProducts);
            }
        } catch (ItemDoesNotExistException e) {
            res.setStatus(404);
        }
        return new HttpResponseDto(404, "Failed. Sale not found.", null);
    }


    // create sale
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponseDto createSale(@RequestBody OnSaleDto onSaleDto, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if (userSession == null) {
                res.setStatus(400);
                return new HttpResponseDto(400, "Failed. You are not logged in", null);
            }
            OnSale sale = new OnSale();
            sale.setDiscount(onSaleDto.getDiscount());
            sale = onSaleService.createOnSale(sale);
            if (onSaleService.getById(sale.getId()).isPresent()) {
                res.setStatus(200);
                return new HttpResponseDto(200, "Successfully created new sale.", sale);
            }
        } catch (ItemDoesNotExistException e) {
            res.setStatus(400);
        }
        return new HttpResponseDto(400, "Sale creation failed.", null);
    }

    // update sale
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto updateSale(@RequestBody OnSaleDto onSaleDto, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if (userSession == null) {
                res.setStatus(400);
                return new HttpResponseDto(400, "Failed. You are not logged in", null);
            }
            if (onSaleService.getById(onSaleDto.getId()).isPresent()) {
                OnSale sale = onSaleService.getById(onSaleDto.getId()).get();
                sale.setId(onSaleDto.getId());
                sale.setDiscount(onSaleDto.getDiscount());
                sale = onSaleService.update(sale);
                res.setStatus(200);
                return new HttpResponseDto(200, "Successfully updated sale.", sale);
            }
        } catch (ItemDoesNotExistException e) {
            res.setStatus(400);
        }
        res.setStatus(404);
        return new HttpResponseDto(404, "Sale update failed. Sale not found", null);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto deleteSale(@RequestHeader Integer onSaleId, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {

        if (userSession == null) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        try {
            if (onSaleService.getById(onSaleId).isPresent()) {
                OnSale sale = onSaleService.getById(onSaleId).get();
                onSaleService.delete(sale);
                res.setStatus(200);
                return new HttpResponseDto(200, "Successfully deleted sale.", null);
            }
        } catch (ItemDoesNotExistException | ItemHasNoIdException e) {
            res.setStatus(400);
        }

        return new HttpResponseDto(400, "Failed. Sale does not exist.", null);

    }



}
