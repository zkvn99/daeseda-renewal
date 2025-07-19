package com.experiment.daeseda_renewal.domain.category;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryById;

    @Column(name = "category_name")
    private Long categoryName;

//    @OneToMany(mappedBy = "category")
//    private List<ReviewCategoryEntity> reviewCategories;
}
