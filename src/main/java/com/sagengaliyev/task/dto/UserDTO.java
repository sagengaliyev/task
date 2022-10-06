package com.sagengaliyev.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate publishedDate;
    private Long imageId;
    private String imageUrl;
    private String newStatus;

}
