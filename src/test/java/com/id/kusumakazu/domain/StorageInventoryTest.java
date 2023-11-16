package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class StorageInventoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StorageInventory.class);
        StorageInventory storageInventory1 = new StorageInventory();
        storageInventory1.setId(1L);
        StorageInventory storageInventory2 = new StorageInventory();
        storageInventory2.setId(storageInventory1.getId());
        assertThat(storageInventory1).isEqualTo(storageInventory2);
        storageInventory2.setId(2L);
        assertThat(storageInventory1).isNotEqualTo(storageInventory2);
        storageInventory1.setId(null);
        assertThat(storageInventory1).isNotEqualTo(storageInventory2);
    }
}
