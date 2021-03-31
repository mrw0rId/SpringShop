package ru.geekbrains.persist.model;

import ru.geekbrains.persist.service.UserRepr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String login;

    @Column(nullable = false, columnDefinition = "INT default '25'")
    private Integer age;

    @Column(length = 512, nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String email;

    @ManyToMany
    @JoinTable(
            name = "products_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(UserRepr user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.age = user.getAge();
        this.password = user.getPassword();
//        this.email = user.getEmail();
        this.products = user.getProducts();
        this.roles = user.getRoles();
    }

    public User(String login, Integer age, String password) {
        this.login = login;
        this.age = age;
        this.password = password;
//        this.email = email;
    }

    public User(String login, Integer age, String password, List<Product> products) {
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
    public int getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
                ", products=" + names +
                '}';
    }
}
