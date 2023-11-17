package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class UserSessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSession.class);
        UserSession userSession1 = new UserSession();
        userSession1.setId(1L);
        UserSession userSession2 = new UserSession();
        userSession2.setId(userSession1.getId());
        assertThat(userSession1).isEqualTo(userSession2);
        userSession2.setId(2L);
        assertThat(userSession1).isNotEqualTo(userSession2);
        userSession1.setId(null);
        assertThat(userSession1).isNotEqualTo(userSession2);
    }
}
