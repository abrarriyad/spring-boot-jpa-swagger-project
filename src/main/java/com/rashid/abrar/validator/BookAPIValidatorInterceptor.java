package com.rashid.abrar.validator;


import com.rashid.abrar.exception.IllegalAuthorPropertyException;
import com.rashid.abrar.exception.IllegalBookPropertyException;
import com.rashid.abrar.util.Constants;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookAPIValidatorInterceptor implements HandlerInterceptor {


//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws IllegalBookPropertyException,IllegalAuthorPropertyException
//    {
//        String id = request.getParameter(Constants.ID);
//        System.out.println(id);
//        if(StringUtils.hasLength(id)){
//           throw new IllegalAuthorPropertyException();
//        }
//
//        return true;
//    }
}
