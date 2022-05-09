package com.ivana.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//in ManyToOne: Many end will be the maintaining end
//Blog class will be the maintaining end to add or remove tag, category
//the maintained end: OneToMany(mappedBy = "tag")

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Title cannot be empty.")
    private String title;

    @NotEmpty(message = "Content cannot be empty.")
    private String content;
    private String firstPic;
    private String flag;
    private Integer views;
    private boolean donateEnabled;
    private boolean commentEnabled;
    private boolean published;
    private boolean recommended;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne //many blogs -- one category
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

}
