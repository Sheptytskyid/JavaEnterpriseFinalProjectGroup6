package net.greatstart.controllers;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String SHOW_PROFILE = "user/UserPage";
    private static final String EDIT_PROFILE = "user/EditUserPage";
    private static final String ERROR_PROFILE = "redirect:/";
    private static final long ID = 1L;
    private static final String TEST_USER_PROFILE = "/user/" + ID;
    private static final String TEST_EMAIL = "";

    @Mock
    private UserService userService;
    @Mock
    private UserConverterService userConverter;
    @Mock
    private Principal principal;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private MultipartFile file;
    @InjectMocks
    private UserController controller;
    private MockMvc mvc;
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Before
    public void init() {
        mvc = standaloneSetup(controller).build();
    }

    @Test(timeout = 2000)
    public void showProfileAndCheckView() throws Exception {
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(view().name(SHOW_PROFILE));
    }

    @Test
    public void showProfileAndVerifyInteractions() throws Exception {
        user.setId(ID);
        when(userService.getUserById(ID)).thenReturn(user);
        when(userConverter.fromUserToDtoProfile(user)).thenReturn(new DtoUserProfile());
        mvc.perform(get(TEST_USER_PROFILE))
                .andExpect(view().name(SHOW_PROFILE));
        verify(userService).getUserById(ID);
        verify(userConverter).fromUserToDtoProfile(user);
    }

    @Test(timeout = 2000)
    public void getEditProfileFormByWrongUserExpectErrorPage() throws Exception {
        mvc.perform(get(TEST_USER_PROFILE + "/edit"))
                .andExpect(view().name(ERROR_PROFILE));
    }

    @Test(timeout = 2000)
    public void getEditProfile() throws Exception {
        user.setEmail(TEST_EMAIL);
        when(userService.getUserById(ID)).thenReturn(user);
        when(userConverter.fromUserToDtoProfile(user)).thenReturn(new DtoUserProfile());
        when(principal.getName()).thenReturn(TEST_EMAIL);
        mvc.perform(get(TEST_USER_PROFILE + "/edit").principal(principal))
                .andExpect(view().name(EDIT_PROFILE));
        verify(userService).getUserById(ID);
        verify(userConverter).fromUserToDtoProfile(user);
    }

    @Test(timeout = 2000)
    public void saveInvalidEditedProfileExpectReturningToEditPage() throws Exception {
        mvc.perform(post(TEST_USER_PROFILE + "/edit"))
                .andExpect(view().name(EDIT_PROFILE));
    }

    @Test(timeout = 2000)
    public void saveEditedProfileWrongUser() throws Exception {
        ModelAndView modelAndView = controller
                .saveEditedProfile(ID, new DtoUserProfile(), bindingResult, principal, file);

        assertEquals("redirect:" + TEST_USER_PROFILE, modelAndView.getViewName());
        verify(userService).getUserByEmail(null);
    }

    @Test(timeout = 2000)
    public void saveEditedProfileCorrectUser() throws Exception {
        DtoUserProfile dtoUser = new DtoUserProfile();
        User user = new User();
        user.setId(ID);
        when(userService.getUserByEmail(null)).thenReturn(user);
        when(userService.getUserById(ID)).thenReturn(user);
        ModelAndView modelAndView = controller
                .saveEditedProfile(ID, dtoUser, bindingResult, principal, file);
        verify(userConverter).updateUserFromDto(user, dtoUser);
        verify(userService).updateUser(user);
        assertEquals("redirect:" + TEST_USER_PROFILE, modelAndView.getViewName());
    }

    @Test
    public void downloadPhoto() throws Exception {
        byte[] bytes = new byte[] {0, 1, 0};
        DtoUserProfile dtoUserProfile = new DtoUserProfile();
        dtoUserProfile.setPhoto(bytes);
        when(userService.getUserById(ID)).thenReturn(user);
        when(userConverter.fromUserToDtoProfile(user)).thenReturn(dtoUserProfile);
        mvc.perform(get("/user/photo/1")).andExpect(content().bytes(bytes));
    }
}
