// package com.securitascash.controller;

// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.servlet.ModelAndView;

// import com.securitascash.model.ResponseModel;

// import jakarta.servlet.RequestDispatcher;
// import jakarta.servlet.http.HttpServletRequest;

// @Controller
// public class CustomErrorController implements ErrorController{
    
//     @SuppressWarnings({ "rawtypes", "unchecked" })
//     @GetMapping("/error")
//     public ModelAndView handleError(HttpServletRequest request, ModelAndView mav) {
        
//         Object statusObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//         String statusCode = statusObj != null ? statusObj.toString(): "500";

//         mav.setViewName("error");

//         ResponseModel response = new ResponseModel<String>();
//         String path = request.getServletPath();

//         response.setStatus(statusCode);
//         response.setData(path);
//         response.setMessage(switch (statusCode) {
//             case "404" -> "Página não encontrada.";
//             case "500" -> "Erro interno do servidor.";
//             default -> "Erro desconhecido.";
//         });

//         mav.addObject("response", response);

//         return mav;
//     }
// }
