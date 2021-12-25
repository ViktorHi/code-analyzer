package com.vicras.codeanalyzerserver.controller.mvc;

import com.vicras.codeanalyzerserver.dto.auth.CredentialsDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;
import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;
import com.vicras.codeanalyzerserver.exception.handler.custom.CustomExceptionsHandler;
import com.vicras.codeanalyzerserver.exception.handler.system.ExceptionsHandler;
import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import com.vicras.codeanalyzerserver.service.AuthService;
import com.vicras.codeanalyzerserver.service.DocumentService;
import com.vicras.codeanalyzerserver.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private final AuthService authService;
    private final UploadService uploadService;
    private final DocumentService documentService;

    private final ExceptionsHandler handlerFactory;
    private final CustomExceptionsHandler customExceptionsFactory;

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/upload")
    public String getUpload() {
        return "upload";
    }

    @RequestMapping("/signin")
    public String getSignIn() {
        return "signin";
    }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/document/{id}")
    public String getDocument(@PathVariable Long id, Principal user, Model model) {
        var doc = documentService.getDocumentById(id, user);
        model.addAttribute("document", doc);
        return "document";
    }

    @RequestMapping("/history")
    public String getHistory(Model model, Principal user) {
        var docs = documentService.getAllUserDocuments(user);
        model.addAttribute("documents", docs);
        return "history";
    }

    @PostMapping("/upload/archive")
    public RedirectView fromArchive(@RequestParam("file") MultipartFile file, Principal user) {
        var doc = uploadService.fromArchive(file, user);
        return new RedirectView("/document/" + doc.getId());
    }

    @PostMapping("/upload/url")
    public RedirectView fromUrl(@RequestParam String link, Principal user) {
        var doc = uploadService.fromUrl(link, user);
        return new RedirectView("/document/" + doc.getId());
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String login,
                         @RequestParam String pass) {
        var cred = new CredentialsDto(pass, login);
        var signIn = new SignInDto(name, surname, cred);
        authService.signIn(signIn);
        return "index";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleError(Exception e, Model model) {
        setErrorToModel(model, handlerFactory.handle(e), e.getClass());
        return "error";
    }

    @ExceptionHandler(AnalyzerException.class)
    public String handleException(AnalyzerException e, Model model) {
        setErrorToModel(model, customExceptionsFactory.handle(e), e.getClass());
        return "error";
    }

    public void setErrorToModel(Model model, ResponseEntity<ResponseError> error, Class<? extends Exception> errorClass) {
        final ResponseError body = error.getBody();
        model.addAttribute("message", body.getErrorMessage());
        model.addAttribute("error", errorClass.toString());
        model.addAttribute("code", body.getErrorCode());
        model.addAttribute("time", body.getTimeStamp());
    }
}
