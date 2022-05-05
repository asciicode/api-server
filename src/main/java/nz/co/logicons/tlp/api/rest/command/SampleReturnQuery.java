package nz.co.logicons.tlp.api.rest.command;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nz.co.logicons.tlp.api.service.SampleMainService;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;

public class SampleReturnQuery
    implements
    Callable<ResponseEntity<String>>
{
  private MongoDatastore datastore;

  private TransformOperation transformOperation;

  private SampleMainService mainService;

  public SampleReturnQuery(MongoDatastore datastore, TransformOperation transformOperation, SampleMainService mainService)
  {
    this.datastore = datastore;
    this.transformOperation = transformOperation;
    this.mainService = mainService;
  }

  @Override
  public ResponseEntity<String> call()
    throws Exception
  {
    // do stuff here
    mainService.executeMain();

    return new ResponseEntity<String>(datastore.getDocumentInStr("TN_Job", "2", false), HttpStatus.OK);
  }

}
