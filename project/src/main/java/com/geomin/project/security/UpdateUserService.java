package com.geomin.project.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomin.project.command.UserVO;

@Service
public class UpdateUserService {

    @Autowired
    private HttpServletRequest request; // 세션 주입을 위한 HttpServletRequest

    public void updateUserInformation(UserVO vo) {
        // 업데이트된 사용자 정보를 세션에 저장합니다.
        HttpSession session = request.getSession();
        session.setAttribute("vo", vo);
    }
}