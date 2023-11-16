package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class MasterAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterAccountDTO.class);
        MasterAccountDTO masterAccountDTO1 = new MasterAccountDTO();
        masterAccountDTO1.setId(1L);
        MasterAccountDTO masterAccountDTO2 = new MasterAccountDTO();
        assertThat(masterAccountDTO1).isNotEqualTo(masterAccountDTO2);
        masterAccountDTO2.setId(masterAccountDTO1.getId());
        assertThat(masterAccountDTO1).isEqualTo(masterAccountDTO2);
        masterAccountDTO2.setId(2L);
        assertThat(masterAccountDTO1).isNotEqualTo(masterAccountDTO2);
        masterAccountDTO1.setId(null);
        assertThat(masterAccountDTO1).isNotEqualTo(masterAccountDTO2);
    }
}
