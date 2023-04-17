package com.spotlight.platform.userprofile.api.model.profile.primitives;

import com.spotlight.platform.helpers.FixtureHelpers;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.entities.Action;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class UserProfileFixtures {
    public static final UserId USER_ID = UserId.valueOf("existing-user-id");
    public static final UserId NON_EXISTING_USER_ID = UserId.valueOf("non-existing-user-id");
    public static final UserId INVALID_USER_ID = UserId.valueOf("invalid-user-id-%");

    public static final Instant LAST_UPDATE_TIMESTAMP = Instant.parse("2021-06-01T09:16:36.123Z");

    public static final UserProfile USER_PROFILE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("property1"), UserProfilePropertyValue.valueOf("property1Value")));

    public static final String SERIALIZED_USER_PROFILE = FixtureHelpers.fixture("/fixtures/model/profile/userProfile.json");

    public static final String SERIALIZED_INVALID_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/invalidUpdateProfile.json");
    public static final String SERIALIZED_REPLACE_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/replaceAction.json");
    public static final String SERIALIZED_COLLECT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/collectAction.json");
    public static final String SERIALIZED_INCREMENT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/incrementAction.json");
    public static final String SERIALIZED_INVALID_INCREMENT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/invalidIncrementAction.json");
    public static final String SERIALIZED_INVALID_COLLECT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/invalidCollectAction.json");
    public static final UserProfile USER_PROFILE_NUMERICAL_PROPERTY = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("battleFought"), UserProfilePropertyValue.valueOf(10), UserProfilePropertyName.valueOf("questsNotCompleted"), UserProfilePropertyValue.valueOf(-1)));
    public static final UserProfile USER_PROFILE_LIST_PROPERTY = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("inventory"), UserProfilePropertyValue.valueOf(Arrays.asList("armor1", "armor2"))));

    public static final String SERIALIZED_USER_PROFILE_REPLACE_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/replaceActionUserProfile.json");
    public static final String SERIALIZED_USER_PROFILE_COLLECT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/collectActionUserProfile.json");
    public static final String SERIALIZED_USER_PROFILE_COLLECT_ACTION_EXISTING_PROPERTIES = FixtureHelpers.fixture("/fixtures/model/updateProfile/collectActionExistingPropertiesUserProfile.json");
    public static final String SERIALIZED_USER_PROFILE_INCREMENT_ACTION = FixtureHelpers.fixture("/fixtures/model/updateProfile/incrementActionUserProfile.json");
    public static final String SERIALIZED_USER_PROFILE_INCREMENT_ACTION_EXISTING_PROPERTIES = FixtureHelpers.fixture("/fixtures/model/updateProfile/incrementActionExistingPropertiesUserProfile.json");
}
