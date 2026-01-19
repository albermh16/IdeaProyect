package es.daw.foodexpressapi.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDetailId implements Serializable {

    private Long dishId;
    private Long orderId;
}
