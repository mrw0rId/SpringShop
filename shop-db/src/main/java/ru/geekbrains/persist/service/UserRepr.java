package ru.geekbrains.persist.service;

import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;


import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.*;

public class UserRepr {

    private Long id;

    private String login;

    private int age;

//    @Email
//    private String email;

    private String password;

//    @JsonIgnore
    private String matchingPassword;

    @ManyToMany
    @JoinTable(
            name = "products_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private Set<Role> roles;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.age = user.getAge();
        this.password = user.getPassword();
//        this.email = user.getEmail();
        this.roles = new HashSet<>(user.getRoles());
    }

    public UserRepr(String login, Integer age, String password, String matchingPassword, String email) {
        this.login = login;
        this.age = age;
        this.password = password;
        this.matchingPassword = matchingPassword;
//        this.email = email;
    }

    public UserRepr(String login, Integer age, String password, String email, List<Product> products) {
        this.login = login;
        this.age = age;
        this.password = password;
//        this.email = email;
        this.products = products;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String post) {
        this.password = post;
    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String age) {
//        this.email = age;
//    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public String getMatchingPassword() {
        return matchingPassword;
    }
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepr userRepr = (UserRepr) o;
        return id.equals(userRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        List<String> names = new ArrayList<>();
        getProducts().forEach(p -> names.add(p.getProductName()));
        return "User{" +
                "id=" + id +
                ", userName='" + login + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
//                ", email='" + email + '\'' +
                ", products=" + names +
                '}';
    }
}
