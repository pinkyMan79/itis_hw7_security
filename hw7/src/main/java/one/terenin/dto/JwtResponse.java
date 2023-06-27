package one.terenin.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class JwtResponse {
    String token;
    String type = "Bearer";
    UUID id;
    String username;
    Set<String> roleSet;
}
