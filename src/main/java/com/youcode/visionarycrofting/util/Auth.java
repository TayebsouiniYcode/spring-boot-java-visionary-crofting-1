package com.youcode.visionarycrofting.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Auth {
    private String username;
    private String password;
}
