package com.example.user.service.impl;

import com.example.user.controller.UserController;
import com.example.user.convertor.UserConvertor;
import com.example.user.convertor.UserConvertorES;
import com.example.user.domain.User;
import com.example.user.domain.UserES;
import com.example.user.dtos.UserDto;
import com.example.user.repository.UserRepositoryES;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;
    @Autowired
    private UserConvertorES userConvertorES;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserES userES;
    @Autowired
    private UserRepositoryES userRepositoryES;
    private UserController userController;
    @Override
    public UserDto create(UserDto userDto){
        User user=userConvertor.convert(userDto);
        User afterSave=userRepository.save(user);
        return userConvertor.convert(afterSave);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users= userRepository.getByName(name);
        if(Objects.isNull(users))return null;
        List<UserDto> ans=new ArrayList<>();
        for(User user:users){
            ans.add(userConvertor.convert(user));
        }
        return ans;
    }

    @Override
    public List<UserDto> getUserByCode(String code) {
        List<UserES> usersES= userRepositoryES.getByCode(code);
        if(Objects.isNull(usersES))return null;
        List<UserDto> ans=new ArrayList<>();
        for(UserES user:usersES){
            ans.add(userConvertorES.convert(user));
        }
        return ans;
    }

    @Scheduled(cron = "0 */1 * * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncUsers();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    public void syncUsers() {

        Specification<User> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
                (javax.persistence.criteria.Predicate) getModificationDatePredicate(criteriaBuilder, root);
        List<User> userList;

        userList = userRepository.findAll();
        for(User user: userList) {
            LOG.info("Syncing User - {}", user.getId());
            userRepositoryES.save(userConvertorES.convert(user));
        }
    }
    public static final Integer INTERVAL_IN_MILLISECOND = 180_000;
    public static final String MODIFICATION_DATE = "modificationDate";

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
        Expression<Timestamp> currentTime;
        currentTime = cb.currentTimestamp();
        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
                (INTERVAL_IN_MILLISECOND)));
        return (Predicate) cb.between(root.<Date>get(MODIFICATION_DATE),
                currentTimeMinus,
                currentTime
        );
    }
}
