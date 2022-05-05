package nz.co.logicons.tlp.api.rest.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nz.co.logicons.tlp.api.rest.command.SampleReturnCommand;
import nz.co.logicons.tlp.api.rest.command.SampleReturnQuery;
import nz.co.logicons.tlp.api.service.SampleMainService;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;

@RestController
@RequestMapping(API.VERSIONED_PATH + "/sample")
public class SampleController
{
  @Autowired
  private MongoDatastore datastore;

  @Autowired
  private TransformOperation transformOperation;

  @Autowired
  private SampleMainService mainService;

  @RequestMapping(method = RequestMethod.POST, value = "/single")
  public Callable<ResponseEntity<?>> postSingle()
  {
    return new SampleReturnCommand(datastore, transformOperation, mainService);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/single")
  public Callable<ResponseEntity<String>> getSingle()
  {
    return new SampleReturnQuery(datastore, transformOperation, mainService);
  }
}
