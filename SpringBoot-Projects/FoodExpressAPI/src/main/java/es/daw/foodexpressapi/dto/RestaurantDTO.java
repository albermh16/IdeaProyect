package es.daw.foodexpressapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDTO {
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String address;
    private String phone;

}
