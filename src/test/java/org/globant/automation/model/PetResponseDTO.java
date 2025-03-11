package org.globant.automation.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDTO {
    private BigInteger id;
    private CategoryDTO category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<CategoryDTO> tags;
    private String status;
}
