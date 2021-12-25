//package com.vicras.codeanalyzerserver.swagger;
//
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Inherited;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.BAD_REQUEST_CODE;
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.BAD_REQUEST_MESSAGE;
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.OK_CODE;
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.OK_MESSAGE;
//
//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//@ApiResponses(value = {
//        @ApiResponse(
//                code = OK_CODE,
//                message = OK_MESSAGE),
//        @ApiResponse(
//                code = BAD_REQUEST_CODE,
//                message = BAD_REQUEST_MESSAGE)
//})
//public @interface BaseApiResponse {
//}