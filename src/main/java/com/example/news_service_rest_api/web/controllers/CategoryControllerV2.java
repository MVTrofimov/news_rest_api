package com.example.news_service_rest_api.web.controllers;


import com.example.news_service_rest_api.mapper.v2.CategoryMapperV2;
import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.services.impl.DatabaseCategoryOfNewsService;
import com.example.news_service_rest_api.web.models.category.CategoryListResponse;
import com.example.news_service_rest_api.web.models.category.CategoryResponse;
import com.example.news_service_rest_api.web.models.category.UpsertCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryControllerV2 {

    private final DatabaseCategoryOfNewsService databaseCategoryService;

    private final CategoryMapperV2 categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber){
        log.info("Метод findAll вызван в CategoryController");
        return ResponseEntity.ok(categoryMapper.categoryListToCategoryListResponse(databaseCategoryService.findAll(pageSize, pageNumber)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById (@PathVariable Long id){
        log.info("Метод findById вызван в CategoryController");
        return ResponseEntity.ok(categoryMapper.categoryToCategoryResponse(databaseCategoryService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MODERATOR')")
    public ResponseEntity<CategoryResponse> create (@Valid @RequestBody UpsertCategoryRequest request){
        CategoryOfNews newCategory = databaseCategoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.categoryToCategoryResponse(newCategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MODERATOR')")
    public ResponseEntity<CategoryResponse> update (@PathVariable Long id, @Valid @RequestBody UpsertCategoryRequest request){
        CategoryOfNews updatedCategory = databaseCategoryService.update(categoryMapper.requestToCategory(id, request));

        return ResponseEntity.ok(categoryMapper.categoryToCategoryResponse(updatedCategory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        databaseCategoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
