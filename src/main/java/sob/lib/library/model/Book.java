package sob.lib.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int year;

    @ElementCollection
    private List<Float> ratings = new ArrayList<>();

    // Getter for averageRating that calculates the average dynamically
    public double getAverageRating() {
        return calculateAverageRating();
    }

    // Method to calculate average rating
    public double calculateAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }
        double total = ratings.stream().mapToDouble(Float::doubleValue).sum();
        return total / ratings.size();
    }
}
