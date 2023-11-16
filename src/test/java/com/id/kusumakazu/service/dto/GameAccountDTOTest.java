package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class GameAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GameAccountDTO.class);
        GameAccountDTO gameAccountDTO1 = new GameAccountDTO();
        gameAccountDTO1.setId(1L);
        GameAccountDTO gameAccountDTO2 = new GameAccountDTO();
        assertThat(gameAccountDTO1).isNotEqualTo(gameAccountDTO2);
        gameAccountDTO2.setId(gameAccountDTO1.getId());
        assertThat(gameAccountDTO1).isEqualTo(gameAccountDTO2);
        gameAccountDTO2.setId(2L);
        assertThat(gameAccountDTO1).isNotEqualTo(gameAccountDTO2);
        gameAccountDTO1.setId(null);
        assertThat(gameAccountDTO1).isNotEqualTo(gameAccountDTO2);
    }
}
