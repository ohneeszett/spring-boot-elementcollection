package work.ohneeszett.spring_boot_hibernate.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;

import work.ohneeszett.spring_boot_hibernate.TestcontainersConfiguration;
import work.ohneeszett.spring_boot_hibernate.entities.Auftrag;
import work.ohneeszett.spring_boot_hibernate.entities.EmbeddedAuftragInfo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({ TestcontainersConfiguration.class })
@DBRider
@DBUnit(leakHunter = true, caseSensitiveTableNames = false, caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "public")
public class AuftragRepositoryTest {

    @Autowired
    private AuftragRepository repository;

    @AfterEach
    @DataSet("data/empty_auftrag.yaml")
    void teardown() {
    }

    @Test
    @ExpectedDataSet(value = "data/expected_auftrag_without_auftrag_info.yaml")
    void auftragRepository_save_should_save_auftrag_without_auftragInfo() throws SQLException {
        var auftrag = Auftrag.builder()
                .id(UUID.fromString("14a429e0-a983-41cc-ae5c-cdcdcad7356c"))
                .num("A-00001")
                .build();

        Auftrag savedAuftrag = assertDoesNotThrow(() -> repository.save(auftrag));

        assertThat(savedAuftrag).isNotNull();
    }

    @Test
    @ExpectedDataSet(value = "data/expected_auftrag_with_auftrag_info.yaml")
    void auftragRepository_save_should_save_auftrag_with_auftragInfo() {
        var id = UUID.fromString("bb5fdf19-7c1f-458d-88f7-2ec56da23695");
        var auftragInfo = EmbeddedAuftragInfo.builder()
                .id(id)
                .description("some auftrag you are")
                .build();
        var auftrag = Auftrag.builder()
                .id(id)
                .num("A-00001")
                .auftragInfo(auftragInfo)
                .build();

        Auftrag savedAuftrag = assertDoesNotThrow(() -> repository.save(auftrag));

        assertThat(savedAuftrag).isNotNull();
    }

    @Test
    @ExpectedDataSet(value = "data/expected_auftrag_with_auftrag_info_and_texts.yaml")
    void auftragRepository_save_should_save_auftrag_with_auftragInfo_and_texts() {
        var id = UUID.fromString("9edf43e6-5390-465f-ada5-6eefcf1fa3fa");
        var auftragInfo = EmbeddedAuftragInfo.builder()
                .id(id)
                .description("some auftrag you are")
                .text("text 1")
                .text("text 2")
                .build();
        var auftrag = Auftrag.builder()
                .id(id)
                .num("A-00001")
                .auftragInfo(auftragInfo)
                .build();

        Auftrag savedAuftrag = assertDoesNotThrow(() -> repository.save(auftrag));

        assertThat(savedAuftrag).isNotNull();
    }

}
