package com.ronggle.blog.service.impl;

import com.jfinal.kit.HashKit;
import com.ronggle.blog.model.Account;
import com.ronggle.blog.service.AccountService;

/**
 * Created by soi on 15-11-4.
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public Account token(Account account) {
        return Account.dao.findFirst("select ac.* from account as ac where 1=1 and ac.username = ? and ac.password = ?", account.getStr("username"), HashKit.md5(account.getStr("password")));
    }
}
