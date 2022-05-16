/**
 * Author(s): Steven Dowd
 * Contributor(s):
 * Purpose: Category Controller
 */

package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.CategoriesService;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.entities.ProductCategory;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping()
    public HttpResponseDto getAll(HttpServletResponse res) {
        List<ProductCategory> categoryList = categoriesService.getAllUsers();

        if (categoryList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all categories.", categoryList);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all categories", categoryList);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponseDto create(@ModelAttribute ProductCategory category, HttpServletResponse res) throws ItemDoesNotExistException {
        ProductCategory newCategory = categoriesService.create(category);

        if (categoriesService.getById(newCategory.getId()).isPresent()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to create category", newCategory);
        } else {
            res.setStatus(200);
            return  new HttpResponseDto(200, "Successfully created category", newCategory);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpResponseDto deleteCategory(@PathVariable("id") Integer id, HttpServletResponse res) throws ItemDoesNotExistException {
        categoriesService.deleteById(id);

        if(categoriesService.getById(id).isPresent()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to delete category", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully deleted category", null);

        }
    }

}
