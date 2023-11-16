package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class ItemObjectTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemObject.class);
        ItemObject itemObject1 = new ItemObject();
        itemObject1.setId(1L);
        ItemObject itemObject2 = new ItemObject();
        itemObject2.setId(itemObject1.getId());
        assertThat(itemObject1).isEqualTo(itemObject2);
        itemObject2.setId(2L);
        assertThat(itemObject1).isNotEqualTo(itemObject2);
        itemObject1.setId(null);
        assertThat(itemObject1).isNotEqualTo(itemObject2);
    }
}
