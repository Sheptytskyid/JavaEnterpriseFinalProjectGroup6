package net.greatstart.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.greatstart.dto.DtoUser;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.UserProfileMapper;
import net.greatstart.model.User;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.UserValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;

import java.security.Principal;

import static net.greatstart.MapperHelper.getTestDtoUser;
import static net.greatstart.MapperHelper.getTestDtoUserProfile;
import static net.greatstart.MapperHelper.getTestUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final long ID = 1L;
    private static final String TEST_USER_PROFILE = "/api/user/" + ID;
    private static final String EMAIL = "a@example.com";
    private static final String PASS = "password";
    private static final String CREATE_USER = "/api/user/";


    @Mock
    private Principal principal;
    @Mock
    private UserService userService;
    @Mock
    private UserProfileMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private SecurityService securityService;
    @Mock
    private UserValidationService userValidationService;
    @Mock
    private Errors errors;
    @InjectMocks
    private UserController controller;
    private MockMvc mvc;
    private User user = getTestUser();
    private DtoUserProfile dtoUserProfile = getTestDtoUserProfile();
    private DtoUser dtoUser = getTestDtoUser();


    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void findById_UserNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(userService.getDtoUserProfileById(ID)).thenReturn(null);
        mvc.perform(get(TEST_USER_PROFILE)).andExpect(status().isNotFound());
        verify(userService, times(1)).getDtoUserProfileById(ID);
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void findById_UserFound_ShouldReturnFoundUserEntity() throws Exception {
        when(userService.getDtoUserProfileById(ID)).thenReturn(dtoUserProfile);
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(status().isOk());
        verify(userService, times(1)).getDtoUserProfileById(ID);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser_UserSuccessfullyUpdatedAndSuccessfullyPassValidation_ShouldReturnUpdatedUser()
            throws Exception {
        dtoUserProfile.setName("TestName");
        when(userService.updateUser(dtoUserProfile, ID)).thenReturn(dtoUserProfile);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUserProfile)))
                .andExpect(status().isOk());
        verify(userService, times(1)).updateUser(dtoUserProfile, ID);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser_withWrongId_ShouldReturnCode404()
            throws Exception {
        dtoUserProfile.setName("TestName");
        dtoUserProfile.setId(2L);
        when(userService.updateUser(dtoUserProfile, ID)).thenReturn(null);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUserProfile)))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).updateUser(dtoUserProfile, ID);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser_FailValidation_ShouldReturnCode400() throws Exception {
        dtoUserProfile.setName("F");
        when(userService.getDtoUserProfileById(ID)).thenReturn(dtoUserProfile);
        mvc.perform(put(TEST_USER_PROFILE).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUserProfile)))
                .andExpect(status().isBadRequest());
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void user() throws Exception {
        assertEquals(principal, controller.user(principal));

    }


    @Test
    public void UnsuccessfulProcessRegistrationWrongUser() throws Exception {
        dtoUser.setEmail("wrong");
        dtoUser.setPassword("354");
        mvc.perform(post(CREATE_USER).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUser)))
                .andExpect(status().isBadRequest());
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(securityService);
    }

    @Test
    public void UnsuccessfulProcessRegistration_UserAlreadyExist() throws Exception {
        when(userService.getUserByEmail(dtoUser.getEmail())).thenReturn(user);
        mvc.perform(post(CREATE_USER).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dtoUser)))
                .andExpect(status().isConflict());
        verify(userService, times(1)).getUserByEmail(dtoUser.getEmail());
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(securityService);
    }

    @Test
    public void SuccessfulProcessRegistration() throws Exception {
        dtoUser.setEmail(EMAIL);
        dtoUser.setPassword(PASS);
        ResponseEntity<?> responseEntity = controller.processRegistration(dtoUser);
        verify(userService, times(1)).getUserByEmail(dtoUser.getEmail());
        verify(userService, times(1)).createUser(dtoUser.getEmail(), passwordEncoder.encode(dtoUser.getPassword()));
        verify(securityService, times(
                1)).autoLogin(dtoUser.getEmail(), dtoUser.getPassword());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(securityService);
    }

    private byte[] convertObjectToJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}