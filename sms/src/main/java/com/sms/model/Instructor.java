package com.sms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PermanentInstructor.class, name = "Permanent"),
        @JsonSubTypes.Type(value = VisitingInstructor.class, name = "Visiting")
})
public class Instructor extends BaseEntity{

    private String name;

    private String address;

    private String phoneNumber;

    private Double salary;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.MERGE)
    private Set<Course> courses = new HashSet<>();

}
