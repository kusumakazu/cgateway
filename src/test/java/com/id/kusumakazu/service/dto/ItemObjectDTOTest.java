package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class ItemObjectDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemObjectDTO.class);
        ItemObjectDTO itemObjectDTO1 = new ItemObjectDTO();
        itemObjectDTO1.setId(1L);
        ItemObjectDTO itemObjectDTO2 = new ItemObjectDTO();
        assertThat(itemObjectDTO1).isNotEqualTo(itemObjectDTO2);
        itemObjectDTO2.setId(itemObjectDTO1.getId());
        assertThat(itemObjectDTO1).isEqualTo(itemObjectDTO2);
        itemObjectDTO2.setId(2L);
        assertThat(itemObjectDTO1).isNotEqualTo(itemObjectDTO2);
        itemObjectDTO1.setId(null);
        assertThat(itemObjectDTO1).isNotEqualTo(itemObjectDTO2);
    }
}
