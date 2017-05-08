package net.greatstart.services;

import net.greatstart.Main;
import net.greatstart.dao.RoleDao;
import net.greatstart.model.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
public class RoleServiceTest {

    private static final String ROLE_USER = "ROLE_USER";
    @Mock
    private RoleDao roleDao;
    @InjectMocks
    private RoleService roleService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        roleService = new RoleService(roleDao);
    }

    @Test
    public void shouldInvokeRoleDaoWhenAddRole() throws Exception {
        Role role = new Role();
        roleService.addRole(role);
        verify(roleDao, times(1)).save(role);
    }

    @Test
    public void shouldFindRoleByNameWhenFindOrCreateRole() throws Exception {
        roleService.findOrCreateRole(ROLE_USER);
        verify(roleDao, times(1)).getByName(ROLE_USER);
    }

    @Test
    public void shouldReturnExistingRoleWhenFindOrCreateRole() throws Exception {
        Role role = new Role();
        when(roleDao.getByName(ROLE_USER)).thenReturn(role);
        Role returnedRole = roleService.findOrCreateRole(ROLE_USER);
        assertEquals(role, returnedRole);
    }

    @Test
    public void shouldNotSaveExistingRoleWhenFindOrCreateRole() throws Exception {
        Role role = new Role();
        when(roleDao.getByName(ROLE_USER)).thenReturn(role);
        roleService.findOrCreateRole(ROLE_USER);
        verify(roleDao, times(0)).save(role);
    }

    @Test
    public void shouldSaveNonExistingRoleWhenFindOrCreateRole() throws Exception {
        Role role = new Role();
        roleService.findOrCreateRole(ROLE_USER);
        verify(roleDao, times(1)).save(role);
    }

}