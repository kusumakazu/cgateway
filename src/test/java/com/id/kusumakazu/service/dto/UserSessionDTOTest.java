package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class UserSessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSessionDTO.class);
        UserSessionDTO userSessionDTO1 = new UserSessionDTO();
        userSessionDTO1.setId(1L);
        UserSessionDTO userSessionDTO2 = new UserSessionDTO();
        assertThat(userSessionDTO1).isNotEqualTo(userSessionDTO2);
        userSessionDTO2.setId(userSessionDTO1.getId());
        assertThat(userSessionDTO1).isEqualTo(userSessionDTO2);
        userSessionDTO2.setId(2L);
        assertThat(userSessionDTO1).isNotEqualTo(userSessionDTO2);
        userSessionDTO1.setId(null);
        assertThat(userSessionDTO1).isNotEqualTo(userSessionDTO2);
    }
}
