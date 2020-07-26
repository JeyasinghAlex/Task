package jersey.model;

import java.lang.annotation.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@JsonPropertyOrder({"id", "name", "designation", "attendance", "salary", "phone", "experience", "dateOfjoining"})
public @interface CustomAnnotation {

}