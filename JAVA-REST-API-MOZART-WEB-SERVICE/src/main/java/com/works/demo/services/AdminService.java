package com.works.demo.services;

import com.works.demo.models.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AdminService {
    final HttpServletRequest req;

    public Boolean login(Admin admin) {
        if (admin.getEmail().equals("cz@cz.com") && admin.getPassword().equals("123")) {
            req.getSession().setAttribute("user", admin.getEmail());
            return true;

        }
        return false;
    }
}
