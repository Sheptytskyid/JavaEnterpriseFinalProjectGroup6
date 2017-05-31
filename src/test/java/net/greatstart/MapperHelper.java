package net.greatstart;

import net.greatstart.dto.DtoCategory;
import net.greatstart.dto.DtoInvestment;
import net.greatstart.dto.DtoProject;
import net.greatstart.dto.DtoProjectDescription;
import net.greatstart.dto.DtoUser;
import net.greatstart.dto.DtoUserProfile;
import net.greatstart.mappers.CycleAvoidingMappingContext;
import net.greatstart.model.Category;
import net.greatstart.model.Contact;
import net.greatstart.model.Project;
import net.greatstart.model.ProjectDescription;
import net.greatstart.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MapperHelper {
    public static final Long TEST_ID = 1L;
    public static final String TEST_PROJECT_NAME = "Test project";
    public static final String TEST_LOGOTYPE = "";
    public static final String TEST_OTHER = "";
    public static final String TEST_GOAL = "";
    public static final boolean TEST_IS_VERIFIED = false;
    public static final byte[] TEST_IMAGE = {0, 1, 1, 5, 1};
    public static final BigDecimal TEST_COST = new BigDecimal(10000);
    public static final BigDecimal TEST_MIN_INVEST = new BigDecimal(100);
    public static final User TEST_USER = new User();
    public static final Category TEST_CATEGORY = new Category();
    public static final DtoCategory TEST_DTO_CATEGORY = new DtoCategory();
    public static final LocalDate DATE_START = LocalDate.now();
    public static final LocalDate DATE_ADDED = LocalDate.now();
    public static final String TEST_USER_NAME = "Test";
    public static final String TEST_ADDRESS = "ul. Lennona 1";
    public static final String TEST_PHONE = "+38 044 123 45 67";
    public static final String TEST_USER_LAST_NAME = "User";
    public static final String TEST_EMAIL = "email@example.com";
    public static final CycleAvoidingMappingContext CONTEXT = new CycleAvoidingMappingContext();

    public static String TEST_PASSWORD = "password";

    public static Project getTestProject() {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(TEST_PROJECT_NAME);
        desc.setLogotype(TEST_LOGOTYPE);
        desc.setOther(TEST_OTHER);
        desc.setIsVerified(TEST_IS_VERIFIED);
        desc.setGoal(TEST_GOAL);
        desc.setDateStart(DATE_START);
        desc.setDateAdded(DATE_ADDED);
        desc.setImage(TEST_IMAGE);
        desc.setCost(TEST_COST);
        desc.setMinInvestment(TEST_MIN_INVEST);
        project.setId(TEST_ID);
        project.setDesc(desc);
        project.setOwner(TEST_USER);
        project.setCategory(TEST_CATEGORY);
        return project;
    }

    public static DtoProject getTestDtoProject() {
        DtoProject dtoProject = new DtoProject();
        DtoProjectDescription dtoDesc = new DtoProjectDescription();
        dtoDesc.setName(TEST_PROJECT_NAME);
        dtoDesc.setGoal(TEST_GOAL);
        dtoDesc.setDateStart(DATE_START);
        dtoDesc.setDateAdded(DATE_ADDED);
        dtoDesc.setImage(TEST_IMAGE);
        dtoDesc.setCost(TEST_COST);
        dtoDesc.setMinInvestment(TEST_MIN_INVEST);
        dtoProject.setId(TEST_ID);
        dtoProject.setDesc(dtoDesc);
        dtoProject.setCategory(TEST_DTO_CATEGORY);
        return dtoProject;
    }

    public static User getTestUser() {
        User user = new User();
        user.setId(TEST_ID);
        user.setName(TEST_USER_NAME);
        user.setLastName(TEST_USER_LAST_NAME);
        Contact contact = new Contact(TEST_ADDRESS, TEST_PHONE);
        user.setContact(contact);
        user.setEmail(TEST_EMAIL);
        user.setPhoto(TEST_IMAGE);
        return user;
    }

    public static DtoUserProfile getTestDtoUserProfile() {
        DtoUserProfile dtoUser = new DtoUserProfile();
        dtoUser.setId(TEST_ID);
        dtoUser.setName(TEST_USER_NAME);
        dtoUser.setLastName(TEST_USER_LAST_NAME);
        dtoUser.setAddress(TEST_ADDRESS);
        dtoUser.setPhoneNumber(TEST_PHONE);
        dtoUser.setEmail(TEST_EMAIL);
        dtoUser.setPhoto(TEST_IMAGE);
        return dtoUser;
    }

    public static DtoUser getTestDtoUser() {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setEmail(TEST_EMAIL);
        dtoUser.setPassword(TEST_PASSWORD);
        dtoUser.setConfirmPassword(TEST_PASSWORD);
        return dtoUser;
    }

    public static DtoInvestment getTestDtoInvestment(BigDecimal sum) {
        DtoInvestment dtoInvestment = new DtoInvestment();
        dtoInvestment.setDateOfInvestment(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        dtoInvestment.setInvestor(getTestDtoUserProfile());
        dtoInvestment.setProject(getTestDtoProject());
        dtoInvestment.setPaid(false);
        dtoInvestment.setVerified(false);
        dtoInvestment.setSum(sum);
        return dtoInvestment;
    }

}
