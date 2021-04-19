package com.tacs.tacs.api;
import java.util.Random;
import java.util.UUID;
import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;
import com.tacs.tacs.model.requestModel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.subst.Token;
import util.Session;

@RequestMapping("api/users")
@RestController
public class User {

    @PostMapping
    public int signUp(@Validated @NonNull @RequestBody UserModel user){
        return 1;
    }

    @PostMapping("/login")
    public Session logIn(@Validated @NonNull @RequestBody UserModel user){
        Session session = new Session();
        session.token = UUID.randomUUID().toString();
        session.tokenId = new Random().nextInt();
        return session;
    }

    @PostMapping("/logout")
    public int logOut(@Validated @NonNull @RequestBody Session session){
        return 1;
    }

}