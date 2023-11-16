package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class StorageInventoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StorageInventoryDTO.class);
        StorageInventoryDTO storageInventoryDTO1 = new StorageInventoryDTO();
        storageInventoryDTO1.setId(1L);
        StorageInventoryDTO storageInventoryDTO2 = new StorageInventoryDTO();
        assertThat(storageInventoryDTO1).isNotEqualTo(storageInventoryDTO2);
        storageInventoryDTO2.setId(storageInventoryDTO1.getId());
        assertThat(storageInventoryDTO1).isEqualTo(storageInventoryDTO2);
        storageInventoryDTO2.setId(2L);
        assertThat(storageInventoryDTO1).isNotEqualTo(storageInventoryDTO2);
        storageInventoryDTO1.setId(null);
        assertThat(storageInventoryDTO1).isNotEqualTo(storageInventoryDTO2);
    }
}
