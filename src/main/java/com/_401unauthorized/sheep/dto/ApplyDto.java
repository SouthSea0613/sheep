package com._401unauthorized.sheep.dto;

<<<<<<< HEAD

=======
>>>>>>> jieun
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ApplyDto {
    String user_id;
<<<<<<< HEAD
    String apply_status;
    String wish_number;
=======
    String wish_number;
    String apply_status;
>>>>>>> jieun

}
