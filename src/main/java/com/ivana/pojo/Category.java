package com.ivana.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Category name cannot be empty.")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Blog> blogs = new ArrayList<>();

}
