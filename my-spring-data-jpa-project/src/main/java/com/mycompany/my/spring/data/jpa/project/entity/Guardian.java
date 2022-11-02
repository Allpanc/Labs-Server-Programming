package com.mycompany.my.spring.data.jpa.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

// Не должно быть отдельной сущности, но данные должен быть в отдельном классе
// Встраиваемый
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Соотвествие полей класса Guardian и столбцов в таблице tbl_student
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;
}
