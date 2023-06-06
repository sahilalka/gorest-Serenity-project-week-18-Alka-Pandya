package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class UserCRUDTestWithSteps extends TestBase {
    static int userId;
    static String name = "testprime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = TestUtils.getRandomValue() + "testprime@gmail.com";
    static String status = "active";
    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createNewProduct(name, gender, email, status);
        response.log().all().statusCode(201);
        userId = response.log().all().extract().path("id");
        System.out.println(userId);

    }
    @Title("Verify if the user was added to the application ")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getAllUserInfo(userId);
        Assert.assertThat(userMap, hasValue(userId));
        System.out.println(userMap);

    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        userSteps.updatingUser(userId, name, gender,email,status).statusCode(200);
        HashMap<String, Object> userMap = userSteps.getAllUserInfo(userId);
        Assert.assertThat(userMap, hasValue(userId));
    }
    @Title("Deleting user information and verify if the user is deleted")
    @Test
    public void test004() {
        userSteps.deleteAUser(userId)
                .statusCode(200);

    }





    }





