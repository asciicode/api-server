package nz.co.logicons.tlp.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API.VERSIONED_PATH + "/distanceLog")
public class ConferenceController
{
  @GetMapping(value = "/about")
  public ResponseEntity<String> about()
  {
    return new ResponseEntity<String>(
        "Successfully Generated Dates for given Tenor ", HttpStatus.OK);
  }

  @GetMapping(value = "/greetings")
  public String greetings(@AuthenticationPrincipal(expression = "username") String username)
  {
    return "Join us " + username;
  }
}
