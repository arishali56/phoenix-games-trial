package com.spotlight.platform.userprofile.api.model.profile.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import java.util.Map;

public record Action(@JsonProperty @JsonFormat(shape = JsonFormat.Shape.STRING) String type,
                     @JsonProperty Map<UserProfilePropertyName, UserProfilePropertyValue> properties) {
}
