package work.ohneeszett.spring_boot_hibernate.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmbeddedAuftragInfo {

    @Id
    private UUID id;

    private String description;

    @ElementCollection
    @CollectionTable(name = "auftrag_info_texts", joinColumns = @JoinColumn(name = "auftrag_id"))
    @OrderColumn(name = "order_idx")
    @Column(name = "text")
    @Singular
    private List<String> texts;

}
