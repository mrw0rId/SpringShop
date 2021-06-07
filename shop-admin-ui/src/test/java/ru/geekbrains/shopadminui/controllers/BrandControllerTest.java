package ru.geekbrains.shopadminui.controllers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.BrandRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BrandRepository brandRepository;

    @Order(1)
    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandCreation() throws Exception {
        mvc.perform(post("/brand")
                .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id", "-1")
                .param("name", "New Brand")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        Optional<Brand> optional = brandRepository.findOne(Example.of(new Brand("New Brand")));

        assertTrue(optional.isPresent());
        assertEquals("New Brand", optional.get().getName());
    }

    @Order(2)
    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandFindAll() throws Exception {
        mvc.perform(post("/brand")
                .content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("id", "-2")
                .param("name", "Old Brand")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        List<Brand> brandList = brandRepository.findAll();

        assertFalse(brandList.isEmpty());
        assertEquals(2, brandList.size());
    }

    @Order(3)
    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandDeleting() throws Exception {
        Optional<Brand> optional = brandRepository.findOne(Example.of(new Brand("New Brand")));
        assertTrue(optional.isPresent());

        mvc.perform(delete("/brand/" + optional.get().getId() + "/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        Optional<Brand> optional2 = brandRepository.findOne(Example.of(new Brand("New Brand")));
        assertTrue(optional2.isEmpty());
    }

    @Order(4)
    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandFindById() throws Exception {
        Optional<Brand> optional = brandRepository.findOne(Example.of(new Brand("Old Brand")));
        assertTrue(optional.isPresent());

        Brand expectedBrand = optional.get();

        mvc.perform(get("/brand/" + expectedBrand.getId() + "/edit"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("brand_form"))
                .andExpect(model().attributeExists("brand"))
                .andExpect(model().attribute("brand", new BaseMatcher<Product>() {
                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof Brand) {
                            Brand brand = (Brand) o;
                            return brand.getId().equals(expectedBrand.getId());
                        }
                        return false;
                    }

                    @Override
                    public void describeTo(Description description) {

                    }
                }));
    }

}
