//package com.vicras.codeanalyzerserver.swagger;
//
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
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.NOT_FOUND_CODE;
//import static com.vicras.codeanalyzerserver.constant.HttpConstants.NOT_FOUND_MESSAGE;
//
//@Target(value = {ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//@ApiResponses(value = {
//        @ApiResponse(
//                code = NOT_FOUND_CODE,
//                message = NOT_FOUND_MESSAGE)
//})
//public @interface NotFoundApiResponse {
//}