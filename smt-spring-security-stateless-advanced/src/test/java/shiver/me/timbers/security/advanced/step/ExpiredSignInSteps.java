/*
 * Copyright 2015 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shiver.me.timbers.security.advanced.step;

import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.security.advanced.token.JwtTokenFactory;
import shiver.me.timbers.security.step.BackgroundUserSteps;
import shiver.me.timbers.security.step.SignInSteps;
import shiver.me.timbers.security.step.SpringBootIntegrationSteps;

public class ExpiredSignInSteps extends SpringBootIntegrationSteps {

    @Autowired
    private BackgroundUserSteps backgroundUserSteps;

    @Autowired
    private SignInSteps signInSteps;

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Given("^the token expiry is \"(\\d+)\" milliseconds$")
    public void the_token_expiry_is_milliseconds(Long expiry) throws IllegalAccessException {
        Hooks.setExpiry(jwtTokenFactory, expiry);
    }

    @Given("^the the user is signed in with the with the username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void the_the_user_is_signed_in_with_the_with_the_username_and_password(String username, String password) {
        backgroundUserSteps.a_user_with_the_username_and_password_exists(username, password);
        signInSteps.the_user_goes_to_the_home_page();
        signInSteps.the_user_enter_a_username_of(username);
        signInSteps.the_user_enter_a_password_of(password);
        signInSteps.the_user_signs_in();
    }

    @Given("^the sign in has expired$")
    public void the_sign_in_has_expired() throws InterruptedException {
    }

    @Given("^the \"(\\d+)\" milliseconds has passed$")
    public void the_milliseconds_has_passed(Long expiry) throws InterruptedException {
        Thread.sleep(expiry);
    }
}
