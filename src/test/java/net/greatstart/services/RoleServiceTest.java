package net.greatstart.services;

import net.greatstart.dao.RoleDao;
import net.greatstart.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    private static final String ROLE_USER = "ROLE_USER";
    @Mock
    private RoleDao roleDao;
    @InjectMocks
    private RoleService roleService;

    @Test
    public void invokeRoleDaoWhenAddRole() throws Exception {
        Role role = new Role();
        roleService.addRole(role);
        verify(roleDao, times(1)).save(role);
    }

    @Test
    public void findRoleByNameWhenFindOrCreateRole() throws Exception {
        roleService.findOrCreateRole(ROLE_USER);
        verify(roleDao, times(1)).getByName(ROLE_USER);
    }

    @Test
    public void returnExistingRoleWhenFindOrCreateRole() throws Exception {
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
    public void saveNonExistingRoleWhenFindOrCreateRole() throws Exception {
        Role role = new Role();
        roleService.findOrCreateRole(ROLE_USER);
        verify(roleDao, times(1)).save(role);
    }
}
