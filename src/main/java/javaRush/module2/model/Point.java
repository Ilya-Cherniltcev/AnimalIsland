package javaRush.module2.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Point {
    private volatile int x;
    private volatile int y;

}

