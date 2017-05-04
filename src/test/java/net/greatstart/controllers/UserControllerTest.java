package net.greatstart.controllers;

import net.greatstart.Main;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.model.User;
import net.greatstart.services.UserConverterService;
import net.greatstart.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Main.class)
public class UserControllerTest {
    private static final String SHOW_PROFILE = "user/showProfile";
    private static final String EDIT_PROFILE = "user/editProfile";
    private static final String ERROR_PROFILE = "redirect:/";
    private static final long TEST_ID = 1L;
    private static final String TEST_USER_PROFILE = "/user/" + TEST_ID;
    private static final String TEST_EMAIL = "";
    @Mock
    private UserService userService;
    @Mock
    private UserConverterService userConverter;
    @InjectMocks
    private UserController controller;
    private MockMvc mvc;
    @Mock
    private Principal principal;
    @Mock
    private BindingResult bindingResult;

    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void showProfileAndCheckView() throws Exception {
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(view().name(SHOW_PROFILE));
    }

    @Test
    public void showProfileAndVerifyInteractions() throws Exception {
        User user = new User();
        user.setId(TEST_ID);
        when(userService.getUserById(TEST_ID)).thenReturn(user);
        when(userConverter.fromUserToDtoProfile(user)).thenReturn(new DtoUserProfile());
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(view().name(SHOW_PROFILE));
        verify(userService).getUserById(TEST_ID);
        verify(userConverter).fromUserToDtoProfile(user);
    }

    @Test
    public void getEditProfileFormByWrongUserExpectErrorPage() throws Exception {
        mvc.perform(get(TEST_USER_PROFILE + "/edit"))
                .andExpect(view().name(ERROR_PROFILE));
    }

    @Test
    public void getEditProfile() throws Exception {
        User user = new User();
        user.setEmail(TEST_EMAIL);
        when(userService.getUserById(TEST_ID)).thenReturn(user);
        when(userConverter.fromUserToDtoProfile(user)).thenReturn(new DtoUserProfile());
        when(principal.getName()).thenReturn(TEST_EMAIL);
        mvc.perform(get(TEST_USER_PROFILE + "/edit").principal(principal))
                .andExpect(view().name(EDIT_PROFILE));
        verify(userService).getUserById(TEST_ID);
        verify(userConverter).fromUserToDtoProfile(user);
    }

    @Test
    public void saveInvalidEditedProfileExpectReturningToEditPage() throws Exception {
        mvc.perform(post(TEST_USER_PROFILE + "/edit"))
                .andExpect(view().name(EDIT_PROFILE));
    }

    @Test
    public void saveEditedProfileWrongUser() throws Exception {
        ModelAndView modelAndView = controller
                .saveEditedProfile(TEST_ID, new DtoUserProfile(), bindingResult, principal);

        assertEquals("redirect:" + TEST_USER_PROFILE, modelAndView.getViewName());
        verify(userService).getUserByEmail(null);
    }

    @Test
    public void saveEditedProfileCorrectUser() throws Exception {
        DtoUserProfile dtoUser = new DtoUserProfile();
        User user = new User();
        user.setId(TEST_ID);
        when(userService.getUserByEmail(null)).thenReturn(user);
        when(userService.getUserById(TEST_ID)).thenReturn(user);
        ModelAndView modelAndView = controller
                .saveEditedProfile(TEST_ID, dtoUser, bindingResult, principal);
        verify(userConverter).updateUserFromDto(user, dtoUser);
        verify(userService).updateUser(user);
        assertEquals("redirect:" + TEST_USER_PROFILE, modelAndView.getViewName());
    }

}