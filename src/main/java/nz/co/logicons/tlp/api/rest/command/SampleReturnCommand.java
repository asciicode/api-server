package nz.co.logicons.tlp.api.rest.command;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nz.co.logicons.tlp.api.service.SampleMainService;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;

public class SampleReturnCommand
    implements
    Callable<ResponseEntity<?>>
{
  // private MongoDatastore datastore;
  //
  // private TransformOperation transformOperation;

  private SampleMainService mainService;

  public SampleReturnCommand(MongoDatastore datastore, TransformOperation transformOperation, SampleMainService mainService)
  {
    // this.datastore = datastore;
    // this.transformOperation = transformOperation;
    this.mainService = mainService;
  }

  @Override
  public ResponseEntity<?> call()
    throws Exception
  {
    // do stuff here other services already injected to the main service
    String jobJson = mainService.executeMain();

    // String jobJson = datastore.getDocumentInStr("TN_Job", "2", false);
    // return new ResponseEntity<String>(), HttpStatus.OK);

    return StringUtils.isBlank(jobJson) ? new ResponseEntity<>(
        HttpStatus.OK)
        : new ResponseEntity<String>(
            "Unable to mark all as read", HttpStatus.NOT_FOUND);
  }

}
