package net.greatstart.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.User;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static net.greatstart.MapperHelper.getTestDtoUserProfile;
import static net.greatstart.MapperHelper.getTestUser;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.security.Principal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final long ID = 1L;
    private static final String TEST_USER_PROFILE = "/api/user/" + ID;


    @Mock
    private Principal principal;
    @Mock
    private UserService userService;
    @Mock
    private UserProfileMapper userMapper;

    @InjectMocks
    private UserController controller;
    private MockMvc mvc;
    private User user = getTestUser();
    private DtoUserProfile dtoUser = getTestDtoUserProfile();


    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void findById_UserNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(userService.getUserById(ID)).thenReturn(null);
        mvc.perform(get(TEST_USER_PROFILE)).andExpect(status().isNotFound());
        verify(userService, times(1)).getUserById(ID);
        verifyNoMoreInteractions(userMapper);
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void findById_UserFound_ShouldReturnFoundUserEntity() throws Exception {
        when(userService.getUserById(ID)).thenReturn(user);
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(status().isOk());
        verify(userService, times(1)).getUserById(ID);
        verify(userMapper, times(1)).fromUserToDtoProfile(user);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    public void updateUser_UserSuccessfullyUpdatedAndSuccessfullyPassValidation_ShouldReturnUpdatedUser()
            throws Exception {
        dtoUser.setName("TestName");
        when(userService.getUserById(ID)).thenReturn(user);
        when(userMapper.fromDtoProfileToUser(dtoUser)).thenReturn(user);
        when(userMapper.fromUserToDtoProfile(user)).thenReturn(dtoUser);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUser)))
                .andExpect(status().isOk());
        verify(userService, times(1)).getUserById(ID);
        verify(userMapper, times(1)).fromDtoProfileToUser(dtoUser);
        verify(userService, times(1)).updateUser(user);
        verify(userMapper, times(1)).fromUserToDtoProfile(user);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    public void updateUser_withWrongId_ShouldReturnCode404()
            throws Exception {
        user.setId(2L);
        dtoUser.setName("TestName");
        when(userService.getUserById(ID)).thenReturn(user);
        when(userMapper.fromDtoProfileToUser(dtoUser)).thenReturn(user);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUser)))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).getUserById(ID);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    public void updateUser_FailValidation_ShouldReturnCode400() throws Exception {
        user.setId(ID);
        dtoUser.setName("Fail");
        when(userService.getUserById(ID)).thenReturn(user);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUser)))
                .andExpect(status().isBadRequest());
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void user() throws Exception {
        assertEquals(principal, controller.user(principal));

    }

    private byte[] convertObjectToJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}