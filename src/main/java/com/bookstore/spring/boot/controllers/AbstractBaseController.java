package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.Constant;
import com.bookstore.spring.boot.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public abstract class AbstractBaseController {

    @Autowired
    public ResponseUtil responseUtil;

//    @Autowired
//    ApplicationValueConfigure applicationValueConfigure;

    public final SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

}
