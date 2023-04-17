package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.exceptions.EntityNotFoundException;
import com.spotlight.platform.userprofile.api.core.exceptions.InvalidParameterTypeException;
import com.spotlight.platform.userprofile.api.core.exceptions.UnsupportedActionException;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.entities.Action;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import com.spotlight.platform.userprofile.api.model.profile.supportedActions.ProfileActions;

import javax.inject.Inject;
import java.time.Instant;
import java.util.*;

public class UserProfileService {
    private final UserProfileDao userProfileDao;

    @Inject
    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public UserProfile get(UserId userId) {
        return userProfileDao.get(userId).orElseThrow(EntityNotFoundException::new);
    }

    public UserProfile performAction(UserId userId, Action action) {
        UserProfile userProfile;
        if (userProfileDao.get(userId).isEmpty()) {
            userProfile = new UserProfile(userId, Instant.now(), new HashMap<>());
        } else {
            userProfile = new UserProfile(userId, Instant.now(), userProfileDao.get(userId).get().userProfileProperties());

        }
        switch (action.type()) {
            case ProfileActions.REPLACE ->
                    action.properties().forEach((userProfilePropertyName, userProfilePropertyValue) -> {
                        userProfile.userProfileProperties().put(userProfilePropertyName, userProfilePropertyValue);
                    });
            case ProfileActions.INCREMENT ->
                    action.properties().forEach((userProfilePropertyName, userProfilePropertyValue) -> {
                        if (!userProfilePropertyValue.getValue().getClass().equals(Integer.class)) {
                            throw new InvalidParameterTypeException();
                        }
                        if (userProfile.userProfileProperties().containsKey(userProfilePropertyName)) {
                            UserProfilePropertyValue incrementedValue = UserProfilePropertyValue.valueOf(Integer.parseInt(userProfile.userProfileProperties().get(userProfilePropertyName).getValue().toString()) + Integer.parseInt(userProfilePropertyValue.getValue().toString()));
                            userProfile.userProfileProperties().put(userProfilePropertyName, incrementedValue);
                        } else {
                            userProfile.userProfileProperties().put(userProfilePropertyName, userProfilePropertyValue);
                        }
                    });
            case ProfileActions.COLLECT -> {
                action.properties().forEach((userProfilePropertyName, userProfilePropertyValue) -> {
                    if (userProfilePropertyValue.getValue().equals(ArrayList.class)) {
                        throw new InvalidParameterTypeException();
                    }
                    if (userProfile.userProfileProperties().containsKey(userProfilePropertyName)) {
                        UserProfilePropertyValue value = userProfile.userProfileProperties().get(userProfilePropertyName);

                        List<Object> valueAsList = new ArrayList<>((Collection<?>) value.getValue());
                        List<Object> argumentAsList = new ArrayList<>((Collection<?>) userProfilePropertyValue.getValue());
                        argumentAsList.forEach(o -> {
                            if (valueAsList.stream().noneMatch(o::equals)) {
                                valueAsList.add(o);
                            }
                        });
                        userProfile.userProfileProperties().put(userProfilePropertyName, UserProfilePropertyValue.valueOf(valueAsList));
                    } else {
                        userProfile.userProfileProperties().put(userProfilePropertyName, userProfilePropertyValue);
                    }
                });
            }

            default -> throw new UnsupportedActionException();
        }
        userProfileDao.put(userProfile);
        return userProfile;
    }
}
