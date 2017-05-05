//package net.greatstart.services;
//
//import net.greatstart.Main;
//import net.greatstart.dao.RoleDao;
//import net.greatstart.model.Role;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.test.context.ContextConfiguration;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = Main.class)
//public class RoleServiceTest {
//
//    private static final String ROLE_USER = "ROLE_USER";
//    @Mock
//    private RoleDao roleDao;
//    @InjectMocks
//    private RoleService roleService;
//
//    @Test
//    public void addRole() throws Exception {
//        Role role = new Role();
//        roleService.addRole(role);
//        verify(roleDao).create(role);
//    }
//
//    @Test
//    public void findNonExistingRole() throws Exception {
//        roleService.findOrCreateRole(ROLE_USER);
//        verify(roleDao).getByName(ROLE_USER);
//        verify(roleDao).create(any());
//    }
//
//    @Test
//    public void findExistingRole() throws Exception {
//        Role role = new Role();
//        when(roleDao.getByName(ROLE_USER)).thenReturn(role);
//        Role returnedRole = roleService.findOrCreateRole(ROLE_USER);
//        assertEquals(role, returnedRole);
//    }
//
//}