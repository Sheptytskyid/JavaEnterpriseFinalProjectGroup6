package net.greatstart;

/**
 * Created object structure:
 * sum - investment sum.
 * var - byte variable for creating projects with different id, name and image.
 * cost - project cost.
 * minInv - project minimal investment.
 *
 * getFullTestUser() {
 *     user = getTestUser()                    --> creates user without fields, that contain nested objects
 *     user.investments = getTestListOfInvestments(sum, val, cost, minInv) {
 *         investment1 = getTestInvestment (sum, val, cost, minInv) {
 *             investment.sum = sum;
 *             investment.investor = getTestUser();
 *             investment.project = getTestProject(val, cost, minInv)       --> creates project without fields,
 *                                                                                that contain nested objects
 *         }
 *         investment2 = {...} --> duplicate of investment1;
 *     }
 * }
 * getFullTestDtoUserProfile() {
 *     user = getTestDtoUserProfile()          --> creates dtoUserProfile without fields, that contain nested objects
 *     user.dtoInvestments = getTestListOfDtoInvestments(sum, val, cost, minInv) {
 *         dtoInvestment1 = getTestDtoInvestment (sum, val, cost, minInv) {
 *             dtoInvestment.sum = sum;
 *             dtoInvestment.investor = getTestDtoUserProfile();
 *             dtoInvestment.project = getTestDtoProject(val, cost, minInv)      --> creates dtoProject without fields,
 *                                                                                    that contain nested objects
 *         }
 *         dtoInvestment2 = {...} --> duplicate of dtoInvestment1;
 *     }
 * }
 */

import net.greatstart.dto.*;
import net.greatstart.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MapperHelper {

    public static final Long TEST_ID = 1L;
    public static final String TEST_PROJECT_NAME = "Test project %s";
    public static final String TEST_LOGOTYPE = "";
    public static final String TEST_OTHER = "";
    public static final String TEST_GOAL = "";
    public static final boolean TEST_IS_VERIFIED = false;
    public static final byte[] TEST_IMAGE = {0, 1, 1, 5, 1};
    public static final byte TEST_VALUE_1 = 1;
    public static final byte TEST_VALUE_2 = 2;
    public static final BigDecimal TEST_COST_1 = new BigDecimal(10000);
    public static final BigDecimal TEST_COST_2 = new BigDecimal(20000);
    public static final BigDecimal TEST_MIN_INVEST_1 = new BigDecimal(1000);
    public static final BigDecimal TEST_MIN_INVEST_2 = new BigDecimal(2000);
    public static final BigDecimal TEST_INVEST_1 = new BigDecimal(2000);
    public static final BigDecimal TEST_INVEST_2 = new BigDecimal(4000);
    public static final User TEST_USER = new User();
    public static final Category TEST_CATEGORY = new Category();
    public static final DtoCategory TEST_DTO_CATEGORY = new DtoCategory();
    public static final LocalDate DATE_START = LocalDate.now();
    public static final LocalDate DATE_ADDED = LocalDate.now();
    public static final LocalDateTime DATE_INVESTMENT = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    public static final String TEST_USER_NAME = "Test";
    public static final String TEST_ADDRESS = "ul. Lennona 1";
    public static final String TEST_PHONE = "+38 044 123 45 67";
    public static final String TEST_USER_LAST_NAME = "User";
    public static final String TEST_EMAIL = "email@example.com";
    public static String TEST_PASSWORD = "password";

    public static User getFullTestUser() {
        User user = getTestUser();
        Project project1 = getFullTestProject(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        Project project2 = getFullTestProject(TEST_INVEST_2, TEST_VALUE_2, TEST_COST_2, TEST_MIN_INVEST_2);
        List<Investment> investments = project1.getInvestments();
        investments.addAll(project2.getInvestments());
        user.setInvestments(investments);
//        todo user.setOwnedProjects(getTestListOfProjects());
        return user;
    }

    public static DtoUserProfile getFullTestDtoUserProfile() {
        DtoUserProfile dtoUser = getTestDtoUserProfile();
        DtoProject dtoProject1 = getFullTestDtoProject(TEST_INVEST_1, TEST_VALUE_1, TEST_COST_1, TEST_MIN_INVEST_1);
        DtoProject dtoProject2 = getFullTestDtoProject(TEST_INVEST_2, TEST_VALUE_2, TEST_COST_2, TEST_MIN_INVEST_2);
        List<DtoInvestment> dtoInvestments = dtoProject1.getDtoInvestments();
        dtoInvestments.addAll(dtoProject2.getDtoInvestments());
        dtoUser.setDtoInvestments(dtoInvestments);
//        todo dtoUser.setOwnedProjects(getTestListOfProjects());
        return dtoUser;
    }


    public static Project getFullTestProject(BigDecimal investmentSum,
                                             byte value,
                                             BigDecimal projectCost,
                                             BigDecimal projectMinInvestment) {
        Project project = getTestProject(value, projectCost, projectMinInvestment);
        project.setInvestments(getTestListOfInvestments(investmentSum, value, projectCost, projectMinInvestment));
        project.setOwner(getTestUser());
        return project;
    }

    public static DtoProject getFullTestDtoProject(BigDecimal investmentSum,
                                                   byte value,
                                                   BigDecimal projectCost,
                                                   BigDecimal projectMinInvestment) {
        DtoProject dtoProject = getTestDtoProject(value, projectCost, projectMinInvestment);
        dtoProject.setDtoInvestments(getTestListOfDtoInvestments(investmentSum
                , value, projectCost, projectMinInvestment));
        dtoProject.setOwner(getTestDtoUserProfile());
        return dtoProject;
    }

    public static List<Investment> getTestListOfInvestments(BigDecimal investmentSum,
                                                            byte value,
                                                            BigDecimal projectCost,
                                                            BigDecimal projectMinInvestment) {
        Investment investment1 = getTestInvestment(investmentSum, value, projectCost, projectMinInvestment);
        List<Investment> investments = new ArrayList<>();
        investments.add(investment1);
        investments.add(investment1);
        return investments;
    }

    public static List<DtoInvestment> getTestListOfDtoInvestments(BigDecimal investmentSum,
                                                                  byte value,
                                                                  BigDecimal projectCost,
                                                                  BigDecimal projectMinInvestment) {
        DtoInvestment dtoInvestment1 = getTestDtoInvestment(investmentSum, value, projectCost, projectMinInvestment);
        List<DtoInvestment> dtoInvestments = new ArrayList<>();
        dtoInvestments.add(dtoInvestment1);
        dtoInvestments.add(dtoInvestment1);
        return dtoInvestments;
    }

    public static DtoInvestment getTestDtoInvestment(BigDecimal investmentSum,
                                                     byte value,
                                                     BigDecimal projectCost,
                                                     BigDecimal projectMinInvestment) {
        DtoInvestment dtoInvestment = new DtoInvestment();
        dtoInvestment.setDateOfInvestment(DATE_INVESTMENT);
        dtoInvestment.setInvestor(getTestDtoUserProfile());
        dtoInvestment.setProject(getTestDtoProject(value, projectCost, projectMinInvestment));
        dtoInvestment.setPaid(false);
        dtoInvestment.setVerified(false);
        dtoInvestment.setSum(investmentSum);
        return dtoInvestment;
    }

    public static Investment getTestInvestment(BigDecimal investmentSum,
                                               byte value,
                                               BigDecimal projectCost,
                                               BigDecimal projectMinInvestment) {
        Investment investment = new Investment();
        investment.setDateOfInvestment(DATE_INVESTMENT);
        investment.setPaid(false);
        investment.setVerified(false);
        investment.setSum(investmentSum);
        investment.setInvestor(getTestUser());
        investment.setProject(getTestProject(value, projectCost, projectMinInvestment));
        return investment;
    }

    public static Project getTestProject(byte value,
                                         BigDecimal cost,
                                         BigDecimal minInv) {
        Project project = new Project();
        ProjectDescription desc = new ProjectDescription();
        desc.setName(String.format(TEST_PROJECT_NAME, value));
        desc.setLogotype(TEST_LOGOTYPE);
        desc.setOther(TEST_OTHER);
        desc.setIsVerified(TEST_IS_VERIFIED);
        desc.setGoal(TEST_GOAL);
        desc.setDateStart(DATE_START);
        desc.setDateAdded(DATE_ADDED);
        byte[] testImage = {value, 0, value, value, value, 0};
        desc.setImage(testImage);
        desc.setCost(cost);
        desc.setMinInvestment(minInv);
        project.setId(value);
        project.setDesc(desc);
        project.setOwner(TEST_USER);
        project.setCategory(TEST_CATEGORY);
        return project;
    }

    public static DtoProject getTestDtoProject(byte value,
                                               BigDecimal cost,
                                               BigDecimal minInv) {
        DtoProject dtoProject = new DtoProject();
        DtoProjectDescription dtoDesc = new DtoProjectDescription();
        dtoDesc.setName(String.format(TEST_PROJECT_NAME, value));
        dtoDesc.setGoal(TEST_GOAL);
        dtoDesc.setDateStart(DATE_START);
        dtoDesc.setDateAdded(DATE_ADDED);
        byte[] testImage = {value, 0, value, value, value, 0};
        dtoDesc.setImage(testImage);
        dtoDesc.setCost(cost);
        dtoDesc.setMinInvestment(minInv);
        dtoProject.setId((long) value);
        dtoProject.setDesc(dtoDesc);
        dtoProject.setCategory(TEST_DTO_CATEGORY);
        dtoProject.setOwner(getTestDtoUserProfile());
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

}
