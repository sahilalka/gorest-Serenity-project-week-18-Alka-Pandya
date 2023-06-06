package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating a User with name : {0},gender : {1},email : {2},status : {3}")
    public ValidatableResponse createNewProduct(String name,String gender,String email,String status){
        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USERS)
                .then();
    }
    @Step("Getting all the user information")
    public HashMap<String, Object> getAllUserInfo(int userId) {
        HashMap<String, Object> userMap = SerenityRest.given().log().all()
                .when()
                .pathParam("userID", userId)
                .get(EndPoints.GET_ALL_USERS)
                .then().statusCode(200)
                .extract()
                .path("");
        return userMap;
    }
    @Step("Updating user with userId: {0}, name: {1}, gender: {2}, email: {3}, status: {4}" )
    public ValidatableResponse updatingUser(int userID, String name, String gender,  String email,  String status){
        UserPojo userPojo = UserPojo.getUserPojo(name,gender,email,status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("userID",userID)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_ALL_USERS)
                .then();
    }
    @Step("Deleting user information with userId: {0}")
    public ValidatableResponse deleteAUser(int userId) {
        return SerenityRest.given().log().all()
                .pathParam("userID", userId)
                .when()
                .delete(EndPoints.DELETE_ALL_USERS)
                .then();

    }



    }





