package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSessionMapperTest {

    private UserSessionMapper userSessionMapper;

    @BeforeEach
    public void setUp() {
        userSessionMapper = new UserSessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userSessionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userSessionMapper.fromId(null)).isNull();
    }
}
