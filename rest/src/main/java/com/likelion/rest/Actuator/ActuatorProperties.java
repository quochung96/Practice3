package com.likelion.rest.Actuator;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ActuatorProperties {
    @Value("${management.server.port}")
    private String port;
}
