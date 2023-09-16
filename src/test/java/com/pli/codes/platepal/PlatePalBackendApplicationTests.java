package com.pli.codes.platepal;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = DatabaseProvider.ZONKY)
class PlatePalBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
